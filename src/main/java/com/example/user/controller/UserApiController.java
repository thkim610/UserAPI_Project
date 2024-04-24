package com.example.user.controller;

import com.example.user.model.UserDto;
import com.example.user.model.UserRequest;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    public UserDto join(@RequestBody @Valid UserRequest userRequest){

        return userService.join(userRequest);
    }

}

