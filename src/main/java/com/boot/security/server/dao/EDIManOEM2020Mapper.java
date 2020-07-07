package com.boot.security.server.dao;

import com.boot.security.server.model.EDIManOEM2020;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIManOEM2020Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIManOEM2020 record);

    int insertSelective(EDIManOEM2020 record);

    EDIManOEM2020 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIManOEM2020 record);

    int updateByPrimaryKey(EDIManOEM2020 record);
}