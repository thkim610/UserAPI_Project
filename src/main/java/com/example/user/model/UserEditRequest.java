package com.example.user.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 회원 정보 수정
 * - 비밀번호 : 10자리
 * - 닉네임
 * - 전화번호 : 전화번호 형식
 * - 이메일 : 이메일 형식
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserEditRequest {

    @Size(min = 10, max = 10)
    private String password;

    private String nickName;

    //전화번호 정규식 : 01로 시작하고 (0,1,,6,7,8,9) 중 하나의 문자 + -(선택)(3~4자리 숫자) + -(선택)(4자리 숫자)
    //ex : 010-000(0)-0000 or 01000000000
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
    private String phoneNumber;

    @Email
    private String email;
}
