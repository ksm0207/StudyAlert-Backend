package com.example.controller;

import com.example.dto.ArchiveDTO;
import com.example.dto.ArchiveResponseDTO;
import com.example.dto.HistoryDTO;
import com.example.entity.TimeHistory;
import com.example.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1")
public class ArchiveController {
    
    @Autowired
    private ArchiveService archive;

    @PostMapping("/hello")
    public ResponseEntity<ArchiveResponseDTO > save(@RequestBody ArchiveDTO a){
        int status = archive.serviceOnSave(a.getFirst_time(),a.getEnd_time());
        ArchiveResponseDTO response = new ArchiveResponseDTO();

        if(status > 0) {
            System.out.println("DB 저장됨");
            response.setReserve_point(10000);
        }else {
            System.out.println("DB 저장망함");
            response.setReserve_point(0);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/insert")
    public ResponseEntity<Map<String, Object>> save(@RequestBody HistoryDTO historyDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDateTime convertStartDateTime = LocalDateTime.parse(historyDTO.getStart_date_time(), formatter);
        LocalDateTime convertEndDateTime = LocalDateTime.parse(historyDTO.getEnd_date_time(), formatter);

        Map<String, Object> timeHistory =
                archive.saveTimeHistory(
                        historyDTO.getMember_id(),
                        convertStartDateTime,
                        convertEndDateTime
                );
        return new ResponseEntity<>(timeHistory, HttpStatus.OK);
    }
}
