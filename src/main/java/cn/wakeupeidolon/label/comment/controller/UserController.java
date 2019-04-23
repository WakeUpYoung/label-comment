package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.config.RedisConfig;
import cn.wakeupeidolon.label.comment.controller.vo.request.*;
import cn.wakeupeidolon.label.comment.controller.vo.response.QQUserInfoVO;
import cn.wakeupeidolon.label.comment.controller.vo.response.UserVO;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.domain.enums.ErrorCode;
import cn.wakeupeidolon.label.comment.dto.UserDTO;
import cn.wakeupeidolon.label.comment.entity.QQUser;
import cn.wakeupeidolon.label.comment.entity.User;
import cn.wakeupeidolon.label.comment.service.QQUserService;
import cn.wakeupeidolon.label.comment.service.UserService;
import cn.wakeupeidolon.label.comment.utils.BeanMapper;
import cn.wakeupeidolon.label.comment.utils.EmailContent;
import cn.wakeupeidolon.label.comment.service.async.EmailAsync;
import cn.wakeupeidolon.label.comment.utils.EncryptUtils;
import cn.wakeupeidolon.label.comment.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.net.ConnectException;
import java.util.Date;

/**
 * @author Wang Yu
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户"})
public class UserController {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    
    private final QQUserService qqService;
    
    private final EmailAsync emailAsync;
    
    private final RedisConfig redis;
    
    @Autowired
    public UserController(UserService userService,QQUserService qqService, EmailAsync emailAsync, RedisConfig redis) {
        this.userService = userService;
        this.emailAsync = emailAsync;
        this.redis = redis;
        this.qqService = qqService;
    }
    
    @PostMapping("loginWithQQ")
    @ApiOperation("使用QQ登录")
    public Result<UserDTO> loginWithQQ(@RequestBody @Validated QQLoginVO loginVO){
        String url = "https://graph.qq.com/user/get_user_info?" +
                "access_token=" + loginVO.getAccessToken() + "&" +
                "oauth_consumer_key=" + loginVO.getOauthConsumerKey() +"&" +
                "openid=" + loginVO.getOpenId() + "&" +
                "format=json";
        // 从QQ API 获取用户信息
        String result = HttpUtils.get(url);
        QQUserInfoVO qqUserInfoVO = JSON.parseObject(result, QQUserInfoVO.class);
        if (qqUserInfoVO == null){
            return Result.error("QQ登录请求失败");
        }
        UserDTO dto;
        String openId = loginVO.getOpenId();
        // 如果该用户已注册,则获取用户信息，并缓存到Redis
        if (qqService.hasUser(openId)){
            User user;
            // 查询QQ表
            QQUser qqUser = qqService.findByOpenId(openId);
            // 查询缓存
            UserVO userInfoFromCache = null;
            userInfoFromCache = getUserInfoFromCache(qqUser.getUserId());
            if (userInfoFromCache == null){
                // 查询用户表
                user = userService.findById(qqUser.getUserId());
            }else {
                user = BeanMapper.map(userInfoFromCache, User.class);
            }
            dto = BeanMapper.map(user, UserDTO.class);
            dto.setFigureurlQqBig(qqUser.getFigureurlQqBig());
            dto.setFigureurlQqSmall(qqUser.getFigureurlQqSmall());
            dto.setNickname(qqUser.getNickname());
            UserVO userVO = BeanMapper.map(user, UserVO.class);
            // 缓存用户
            cacheUser(userVO);
        }else {
        //    如果用户尚未注册，则向用户表和QQ表新增数据
            QQUser qqUser = new QQUser();
            qqUser.setFigureurlQqBig(qqUserInfoVO.getFigureurl_qq_2());
            qqUser.setFigureurlQqSmall(qqUserInfoVO.getFigureurl_qq_1());
            qqUser.setOpenId(openId);
            qqUser.setNickname(qqUserInfoVO.getNickname());
            User user = new User();
            int gender = qqUserInfoVO.getGender().equals("男") ? 1 : 0;
            user.setGender(gender);
            user.setPassword("default");
            user.setLastLoginTime(new Date());
            user.setEmail("not_set_" + RandomStringUtils.randomAlphanumeric(10));
            user.setUsername(qqUserInfoVO.getNickname());
            dto = qqService.saveQQAndUser(user, qqUser);
            UserVO userVO = BeanMapper.map(dto, UserVO.class);
            // 缓存用户
            cacheUser(userVO);
        }
        return Result.success(dto);
    
    }
    
    @PostMapping("/sendEmail")
    @ApiOperation("发送注册验证邮件")
    public Result<Boolean> sendEmail(@RequestBody @Validated EmailVO vo){
        // 1. 验证邮箱是否存在
        if (!userService.checkEmail(vo.getEmail())){
            return Result.error(ErrorCode.USER_REPEAT);
        }
        // 2. 若该用户尚未注册则发送验证邮件
        String code = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        sendEmailWithCode(vo.getEmail(), "[Wake Up Eidolon]邮箱验证", EmailContent.validation(code), code);
        return Result.success(Boolean.TRUE);
    }
    
    @PostMapping("/passwordEmail")
    @ApiOperation("发送修改密码验证邮件")
    public Result<Boolean> passwordEmail(@RequestBody @Validated EmailVO vo){
        // 1. 验证邮箱是否存在
        if (userService.checkEmail(vo.getEmail())){
            return Result.error(ErrorCode.NOT_REGISTER);
        }
        String code = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        sendEmailWithCode(vo.getEmail(), "[Wake Up Eidolon]尝试修改密码", EmailContent.forgetPassword(code), code);
        return Result.success(Boolean.TRUE);
    }
    @PostMapping("/register")
    @ApiOperation("注册")
    @Transactional
    public Result<UserVO> register(@RequestBody @Validated RegisterVO vo){
        // 验证输入的验证码是否正确匹配
        ShardedJedis jedis = redis.redisPoolFactory().getResource();
        String emailCode = jedis.get(vo.getEmail());
        if (!checkEmailCode(jedis, vo.getEmail(), emailCode)){
            return Result.error(ErrorCode.INVALID_CODE);
        }
        jedis.del(vo.getEmail());
        // 保存用户信息到数据库
        User user = BeanMapper.map(vo, User.class);
        user.setLastLoginTime(new Date());
        user.setPassword(EncryptUtils.md5(user.getPassword()));
        User save = userService.save(user);
        UserVO userVO = BeanMapper.map(save, UserVO.class);
        cacheUser(userVO);
        return Result.success(userVO);
    }
    
    @PostMapping("/login")
    @ApiOperation("登录")
    @Transactional
    public Result<UserVO> login(@RequestBody @Validated LoginVO loginVO){
        User user = userService.findByEmail(loginVO.getUsername());
        // 如果密码匹配
        if (user.getPassword().equals(EncryptUtils.md5(loginVO.getPassword()))){
            // 更新用户最新登录时间
            user.setLastLoginTime(new Date());
            User update = userService.update(user);
            UserVO userVO = BeanMapper.map(update, UserVO.class);
            cacheUser(userVO);
        }
        return Result.error("未知原因,登录失败");
    }
    
    @PostMapping("/validEmail")
    @ApiOperation("校验邮箱")
    public Result<Boolean> validEmail(@RequestBody @Validated EmailValidCodeVO validCodeVO){
        ShardedJedis jedis = redis.redisPoolFactory().getResource();
        String emailCode = jedis.get(validCodeVO.getEmail());
        if (!checkEmailCode(jedis, validCodeVO.getEmail(), emailCode)){
            return Result.error(ErrorCode.INVALID_CODE);
        }
        // 不删除缓存，交给校验方删除
        return Result.success(Boolean.TRUE);
    }
    
    @PostMapping("/forgetPassword")
    @Transactional
    @ApiOperation("忘记密码 - 对密码进行修改")
    public Result<UserVO> forgetPassword(@RequestBody @Validated PasswordVO passwordVO){
        // 用户忘记密码，应先向邮箱发送一封验证邮件
        // 邮件发送后，验证用户输入的验证码是否匹配
        // 匹配后即可更改密码
        ShardedJedis jedis = redis.redisPoolFactory().getResource();
        if (!jedis.exists(passwordVO.getEmail())){
            return Result.error(ErrorCode.CANNOT_ACCESS);
        }
        User user;
        // 尝试从缓存中读取数据
        if (jedis.exists(passwordVO.getId())){
            String userVOStr = jedis.get(passwordVO.getId());
            UserVO userVO = JSON.parseObject(userVOStr, UserVO.class);
            user = BeanMapper.map(userVO, User.class);
            
        }else {
            user = userService.findById(passwordVO.getId());
        }
        user.setPassword(EncryptUtils.md5(passwordVO.getPassword()));
        // 更新数据库
        User updateUser = userService.update(user);
        UserVO userVO = BeanMapper.map(updateUser, UserVO.class);
        // 更新用户缓存信息
        cacheUser(userVO);
        jedis.del(passwordVO.getEmail());
        // 通过邮件通知用户更改了密码
        emailAsync.sendEmail(passwordVO.getEmail(), "[Wake Up Eidolon]密码已修改", EmailContent.modifyPassword());
        return Result.success(userVO);
    }
    
    /**
     * 保存用户到缓存
     * @param userVO 用户信息
     */
    private void cacheUser(UserVO userVO){
        try{
            ShardedJedis jedis = redis.redisPoolFactory().getResource();
            // 保存用户信息到缓存
            String key = String.valueOf(userVO.getId());
            jedis.set(key, JSON.toJSONString(userVO));
            // 一天
            jedis.expire(key, 60 * 60 * 24);
    
        }catch (Exception e){
            LOG.error("连接Redis失败");
        }
    }
    
    /**
     * 从redis读取缓存
     */
    private UserVO getUserInfoFromCache(String userId){
        try{
            ShardedJedis jedis = redis.redisPoolFactory().getResource();
            if(!jedis.exists(userId)){
                return null;
            }
            String json = jedis.get(userId);
            return JSONObject.parseObject(json, UserVO.class);
    
        }catch (Exception e){
            LOG.error("连接Redis失败");
            return null;
        }
    }
    
    /**
     * 使用邮件发送验证码
     * @param to 发送到
     * @param title 邮件标题
     * @param content 邮件内容
     * @param code 验证码
     */
    private void sendEmailWithCode(String to, String title, String content, String code){
        ShardedJedis jedis = redis.redisPoolFactory().getResource();
        jedis.set(to , code);
        // 设置过期时长30分钟
        jedis.expire(to , 30 * 60);
        emailAsync.sendEmail(to , title, content);
    }
    
    /**
     * 验证邮件
     */
    private Boolean checkEmailCode(ShardedJedis jedis, String email, String input){
        if (!jedis.exists(email)){
            return false;
        }
        return jedis.get(email).equalsIgnoreCase(input);
    }
    
}