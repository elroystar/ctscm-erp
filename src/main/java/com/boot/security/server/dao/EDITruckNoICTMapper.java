package com.boot.security.server.dao;

import com.boot.security.server.model.EDITruckNoICT;
import org.springframework.stereotype.Repository;

@Repository
public interface EDITruckNoICTMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDITruckNoICT record);

    int insertSelective(EDITruckNoICT record);

    EDITruckNoICT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDITruckNoICT record);

    int updateByPrimaryKey(EDITruckNoICT record);
}