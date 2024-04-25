package com.example.user.service;

import com.example.common.Api;
import com.example.common.Pagination;
import com.example.user.db.UserEntity;
import com.example.user.db.UserRepository;
import com.example.user.model.UserDto;
import com.example.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .userId(userRequest.getUserId())
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


    //회원 목록 조회
    public Api<List<UserDto>> all(int page, int pageSize) {

        Page<UserEntity> userList = toUserEntityPageList(page, pageSize);

        //페이지 정보 생성
        Pagination pagination = Pagination.builder()
                .page(userList.getNumber())
                .pageSize(userList.getSize())
                .currentElements(userList.getNumberOfElements()) //현재 페이지의 항목 개수
                .totalPage(userList.getTotalPages())
                .totalElements(userList.getTotalElements())
                .build();

        //엔터티 리스트 -> DTO 리스트로 변환.
        List<UserDto> userDtoList = userList.stream()
                .map(it -> {
                    return userConverter.toDto(it);
                })
                .collect(Collectors.toList());

        Api<List<UserDto>> UserListApi = Api.<List<UserDto>>builder()
                .body(userDtoList) //회원 리스트
                .pagination(pagination) //페이지 정보
                .build();


        return UserListApi;

    }

    //페이징 처리를 위한 페이지 설정
    private Page<UserEntity> toUserEntityPageList(int page, int pageSize) {

        //페이징을 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, pageSize);

        //페이징 된 가입일 desc , 이름 asc 정렬 된 회원리스트 반환
        return userRepository.findAllByOrderByRegisteredAtDescNameAsc(pageable);
    }
}
