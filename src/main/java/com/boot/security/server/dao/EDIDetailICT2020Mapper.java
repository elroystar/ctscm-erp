package com.boot.security.server.dao;

import com.boot.security.server.model.EDIDetailICT2020;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIDetailICT2020Mapper {
    int deleteByPrimaryKey(Integer linid);

    int insert(EDIDetailICT2020 record);

    int insertSelective(EDIDetailICT2020 record);

    EDIDetailICT2020 selectByPrimaryKey(Integer linid);

    int updateByPrimaryKeySelective(EDIDetailICT2020 record);

    int updateByPrimaryKey(EDIDetailICT2020 record);

    String selectCTNsByHeadingId(@Param("hid") Integer hid);

    String selectQTYByHeadingId(@Param("hid") Integer hid);

    Double selectFactoryWeightByHeadingId(@Param("hid") Integer hid);
}