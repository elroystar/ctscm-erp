package com.boot.security.server.dao;

import com.boot.security.server.model.EdiLoadOem;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiLoadOemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiLoadOem row);

    int insertSelective(EdiLoadOem row);

    EdiLoadOem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiLoadOem row);

    int updateByPrimaryKey(EdiLoadOem row);
}