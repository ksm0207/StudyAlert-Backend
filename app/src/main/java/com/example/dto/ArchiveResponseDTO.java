package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchiveResponseDTO {
    //  별도로 응답객체정의
    private long reserve_point; // 적립금 포인트
    private String message; // 메세지
}