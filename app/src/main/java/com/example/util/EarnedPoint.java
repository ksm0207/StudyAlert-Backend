package com.example.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class EarnedPoint {

    public long minCalReturnPoint (String start , String end) {

        final Logger logger = Logger.getLogger(EarnedPoint.class.getName());
        // 적립포인트
        long resultPoint = 0;



        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date dateCal1 = null;
            Date dateCal2 = null;


            // 비교할 시간 (문자열)
            String firstTime = start;
            String endTime = end;

            logger.info("시작시간 : " + firstTime);
            logger.info("종료시간 : " + endTime);

            // 문자열 -> Date변환 class java.util.Date
            dateCal1 = sdf.parse(firstTime);
            dateCal2 = sdf.parse(endTime);

            // Date -> 밀리세컨즈 / getTime return : long
            long timeMil1 = dateCal1.getTime();
            long timeMil2 = dateCal2.getTime();

            // 비교 (큰 시간 - 작은시간)
            long diff = timeMil2 - timeMil1;
            logger.info("최초 비교값 : " + diff); //
            long diffMin = diff / (1000 * 60);         // 1000 * 60 = 60000
            logger.info("시간 차이(분) : " + diffMin + "분");
            // 포인트적립
            resultPoint = diffMin * 20;
            logger.info("적립된 포인트 : " + resultPoint);
        }catch(Exception error){
            logger.info("System Error :" + error);
        }
        return resultPoint;
    } // end of method line
}
