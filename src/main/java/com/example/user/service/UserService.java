package com.example.user.service;

import com.example.user.db.UserEntity;
import com.example.user.db.UserRepository;
import com.example.user.model.UserDto;
import com.example.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    //회원가입
    public UserDto join(UserRequest userRequest){

        //클라이언트 정보로 엔티티 생성
        UserEntity entity = UserEntity.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .nickName(userRequest.getNickName())
                .phoneNumber(userRequest.getPhoneNumber())
                .email(userRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        //JPA를 통해 DB에 저장.
        userRepository.save(entity);

        //엔터티를 DTO로 변환하여 반환.
        return userConverter.toDto(entity);
    }


}
