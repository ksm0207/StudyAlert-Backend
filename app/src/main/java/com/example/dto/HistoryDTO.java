package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {
    private String start_date_time; // 시작
    private String end_date_time;   // 종료
    private long member_id;
}
