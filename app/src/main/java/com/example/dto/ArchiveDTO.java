package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchiveDTO {
    private String seq;     // seq번호
    private String name;    // 이름
    private String start_date; // 시작날짜
    private String first_time; // 시작시간
    private String end_time;   // 종료시간
    private String end_date;   // 종료날짜
    private long reserve_point; // 적립금 포인트
}
