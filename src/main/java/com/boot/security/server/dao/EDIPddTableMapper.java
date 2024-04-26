package com.boot.security.server.dao;

import com.boot.security.server.dto.load.EDIPddTable;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIPddTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIPddTable row);

    int insertSelective(EDIPddTable row);

    EDIPddTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIPddTable row);

    int updateByPrimaryKey(EDIPddTable row);
}