package com.example.controller;

import com.example.dto.ArchiveDTO;
import com.example.dto.ArchiveResponseDTO;
import com.example.dto.HistoryDTO;
import com.example.entity.TimeHistory;
import com.example.service.ArchiveService;
import com.example.util.EarnedPoint;
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
    @Autowired
    private EarnedPoint earnedPoint;
    private final Logger logger = Logger.getLogger(ArchiveController.class.getName());

    @PostMapping("/hello")
    public ResponseEntity<ArchiveResponseDTO > save(@RequestBody ArchiveDTO archiveTime){
        ArchiveResponseDTO response = new ArchiveResponseDTO();

        try {
            long point = this.earnedPoint.minCalReturnPoint(archiveTime.getFirst_time(),archiveTime.getEnd_time());

            if (point > 0) logger.info("시간계산완료 : " + point);

            int status = archive.serviceOnSave(archiveTime.getFirst_time(),archiveTime.getEnd_time(),point);
            switch (status) {
                case 1 -> {
                    logger.info("DB 저장 값 : " + status);
                    response.setReserve_point(point);
                    response.setMessage("Success");
                }
                case 0 -> {
                    logger.info("DB 실패 값 : " + status);
                    response.setReserve_point(point);
                    response.setMessage("Fail");
                }
            }
        }catch (Exception error) {
            response.setMessage(error.toString());
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
