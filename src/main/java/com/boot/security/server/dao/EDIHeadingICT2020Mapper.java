package com.boot.security.server.dao;

import com.boot.security.server.model.EDIHeadingICT2020;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIHeadingICT2020Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIHeadingICT2020 record);

    int insertSelective(EDIHeadingICT2020 record);

    EDIHeadingICT2020 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIHeadingICT2020 record);

    int updateByPrimaryKey(EDIHeadingICT2020 record);
}