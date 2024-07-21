package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArchiveDataAccessObject {

    public int onSave(String first_time , String end_time);

}
