package com.example.service;

import com.example.dao.ArchiveDataAccessObject;
import com.example.entity.Member;
import com.example.entity.TimeHistory;
import com.example.repository.MemberRepository;
import com.example.repository.TimeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ArchiveService {
    @Autowired
    private TimeHistoryRepository timeHistoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArchiveDataAccessObject dao;

    public int serviceOnSave(String first_time , String end_time,long point) {
        return dao.onSave(first_time,end_time,point);
    }

    public Map<String,Object> saveTimeHistory(Long memberId, LocalDateTime startDate, LocalDateTime endTime) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자.. 누구냐 넌..."));

        TimeHistory timeHistory = new TimeHistory();
        timeHistory.setStartDate(startDate);
        timeHistory.setEndDate(endTime);
        timeHistory.setReservePoint(10000);
        timeHistory.setMember(member);

        Integer resPoint = timeHistoryRepository.save(timeHistory).getReservePoint();
        String message = "OK";
        Map<String,Object> res = new HashMap<>();
        res.put("reserve_point",resPoint);
        res.put("message", message);
        return res;
    }


}
