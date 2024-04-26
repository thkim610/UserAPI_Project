package com.example.user.model;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 클라이언트로부터 받아오는 데이터 정보를 담음.
 * - 회원 id : not null
 * - 비밀번호 : not null , 10자리
 * - 회원이름 : not null
 * - 닉네임 : not null
 * - 이메일 : 이메일 형식
 * - 전화번호 : not null , 전화번호 형식
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserRequest {

    @NotBlank
    private String userId;

    @NotBlank
    @Size(min = 10, max = 10)
    private String password;

    @NotBlank
    private String nickName;

    @NotBlank
    private String name;

    @NotBlank
    //전화번호 정규식 : 01로 시작하고 (0,1,,6,7,8,9) 중 하나의 문자 + -(선택)(3~4자리 숫자) + -(선택)(4자리 숫자)
    //ex : 010-000(0)-0000 or 01000000000
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "올바른 형식의 전화번호이어야 합니다.")
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;
}
