package com.example.user.controller;

import com.example.common.Api;
import com.example.user.model.UserDto;
import com.example.user.model.UserRequest;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody @Valid UserRequest userRequest){

        UserDto userDto = userService.join(userRequest);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED); //응답코드 201로 반환.
    }

    //회원 목록 조회
    @GetMapping("/list")
    public ResponseEntity<Api<List<UserDto>>> list(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int pageSize){

        Api<List<UserDto>> listApi = userService.all(page, pageSize);

        return new ResponseEntity<>(listApi,HttpStatus.OK);
    }

}

