package com.example.user.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String userId;
    private String password;
    private String nickName;
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDateTime registeredAt;
}
