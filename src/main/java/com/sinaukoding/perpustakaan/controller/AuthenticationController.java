package com.sinaukoding.perpustakaan.controller;

import com.sinaukoding.perpustakaan.common.RestResult;
import com.sinaukoding.perpustakaan.entity.User;
import com.sinaukoding.perpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "do-login")
    public RestResult doLogin(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping(value = "do-register")
    public RestResult register(@RequestBody User user) {
        return new RestResult(userService.register(user, User.Role.ROLE_USER));
    }
}
