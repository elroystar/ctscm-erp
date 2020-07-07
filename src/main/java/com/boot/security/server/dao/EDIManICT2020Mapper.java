package com.boot.security.server.dao;

import com.boot.security.server.model.EDIManICT2020;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIManICT2020Mapper {
    int deleteByPrimaryKey(Integer manid);

    int insert(EDIManICT2020 record);

    int insertSelective(EDIManICT2020 record);

    EDIManICT2020 selectByPrimaryKey(Integer manid);

    int updateByPrimaryKeySelective(EDIManICT2020 record);

    int updateByPrimaryKey(EDIManICT2020 record);
}