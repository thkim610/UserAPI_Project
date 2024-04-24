package com.example.user.service;

import com.example.user.db.UserEntity;
import com.example.user.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    //엔터티를 DTO로 변환하는 메서드
    public UserDto toDto(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .nickName(userEntity.getNickName())
                .phoneNumber(userEntity.getPhoneNumber())
                .email(userEntity.getEmail())
                .registeredAt(userEntity.getRegisteredAt())
                .build();
    }
}
