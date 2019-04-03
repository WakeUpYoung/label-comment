package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.controller.vo.request.EmailVO;
import cn.wakeupeidolon.label.comment.controller.vo.request.RegisterVO;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.domain.enums.ErrorCode;
import cn.wakeupeidolon.label.comment.entity.User;
import cn.wakeupeidolon.label.comment.service.UserService;
import cn.wakeupeidolon.label.comment.utils.BeanMapper;
import cn.wakeupeidolon.label.comment.utils.EmailContent;
import cn.wakeupeidolon.label.comment.service.async.EmailAsync;
import cn.wakeupeidolon.label.comment.utils.EncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    
    private final EmailAsync emailAsync;
    
    @Autowired
    public UserController(UserService userService, EmailAsync emailAsync) {
        this.userService = userService;
        this.emailAsync = emailAsync;
    }
    
    @PostMapping("/sendEmail")
    @ApiOperation("发送验证邮件")
    public Result<Boolean> sendEmail(@RequestBody EmailVO vo, HttpServletRequest request){
        // 1. 验证邮箱是否存在
        if (!userService.checkEmail(vo.getEmail())){
            return Result.error(ErrorCode.USER_REPEAT);
        }
        // 2. 若该用户尚未注册则发送验证邮件
        String code = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        HttpSession session = request.getSession();
        session.setAttribute("emailCode", code);
        // 30分钟后将会过期
        session.setMaxInactiveInterval(30 * 60);
        emailAsync.sendEmail(vo.getEmail(), "[Wake Up Eidolon]邮箱验证", EmailContent.validation(code));
        return Result.success(Boolean.TRUE);
    }
    
    @PostMapping("/register")
    @ApiOperation("注册")
    public Result<Boolean> register(@RequestBody RegisterVO vo, HttpServletRequest request){
        // 验证输入的验证码是否正确匹配
        HttpSession session = request.getSession();
        String emailCode = (String)session.getAttribute("emailCode");
        if (!vo.getValidCode().equalsIgnoreCase(emailCode)){
            return Result.error(ErrorCode.INVALID_CODE);
        }
        // 保存用户信息
        User user = BeanMapper.map(vo, User.class);
        user.setLastLoginTime(new Date());
        user.setPassword(EncryptUtils.md5(user.getPassword()));
        User save = userService.save(user);
        session.setAttribute("user", save);
        return Result.success(Boolean.TRUE);
    }
    
}