package com.boot.security.server.dao;

import com.boot.security.server.dto.load.EDIDim;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIDimMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIDim row);

    int insertSelective(EDIDim row);

    EDIDim selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIDim row);

    int updateByPrimaryKey(EDIDim row);
}