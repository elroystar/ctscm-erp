package com.boot.security.server.dao;

import com.boot.security.server.model.EDIDetailOEM2020;
import com.boot.security.server.model.EDIDetailOEM2020WithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIDetailOEM2020Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIDetailOEM2020WithBLOBs record);

    int insertSelective(EDIDetailOEM2020WithBLOBs record);

    EDIDetailOEM2020WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIDetailOEM2020WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EDIDetailOEM2020WithBLOBs record);

    int updateByPrimaryKey(EDIDetailOEM2020 record);

    String selectCTNsByHeadingId(@Param("hid") Integer hid);

    String selectQTYByHeadingId(@Param("hid") Integer hid);

    Double selectFactoryWeightByHeadingId(@Param("hid") Integer hid);
}