package com.example.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

    private Integer page; // 현재 페이지 번호
    private Integer pageSize; // 페이지 당 표시되는 항목 수
    private Integer currentElements; // 현재 페이지에 표시되는 항목 수
    private Integer totalPage; // 전체 페이지 수
    private Long totalElements; // 전체 항목 수

}
