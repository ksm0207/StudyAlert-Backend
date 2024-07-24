package com.example.util;

public class TestCode {
    public static void main(String[] args) {

        String firstTime = "09:00";
        String endTime = "18:00";
        EarnedPoint e = new EarnedPoint();
        long result = e.minCalReturnPoint(firstTime,endTime);

    }
}