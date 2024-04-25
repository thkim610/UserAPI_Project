package com.example.user.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //회원목록 조회 (가입일 순/ 이름 순으로 정렬)
    //select * from user order by registered_at desc, name asc
    Page<UserEntity> findAllByOrderByRegisteredAtDescNameAsc(Pageable pageable);

    //로그인 id를 통해 회원 조회
    Optional<UserEntity> findByUserId(String userId);
}
