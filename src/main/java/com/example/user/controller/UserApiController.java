package com.example.user.controller;

import com.example.user.common.Api;
import com.example.user.model.UserDto;
import com.example.user.model.UserEditRequest;
import com.example.user.model.UserRequest;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
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

    //회원 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> edit(@RequestBody @Valid UserEditRequest userEditRequest, @PathVariable String userId){

        //로그인 id를 통해 회원 정보 수정
        UserDto userEditDto = userService.edit(userEditRequest, userId);

        return new ResponseEntity<>(userEditDto, HttpStatus.OK);
    }

}

