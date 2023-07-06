package com.boot.security.server.dao;

import com.boot.security.server.model.FieldIhub997;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldIhub997Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FieldIhub997 row);

    int insertSelective(FieldIhub997 row);

    FieldIhub997 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FieldIhub997 row);

    int updateByPrimaryKey(FieldIhub997 row);
}