package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.controller.vo.request.RegisterVO;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.entity.User;
import cn.wakeupeidolon.label.comment.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Yu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    @ApiOperation("注册")
    public Result<Boolean> register(@RequestBody RegisterVO vo){
        return null;
    }
    
}
