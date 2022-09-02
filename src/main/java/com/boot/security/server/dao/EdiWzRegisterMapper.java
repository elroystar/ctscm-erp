package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzRegister;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiWzRegisterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzRegister row);

    int insertSelective(EdiWzRegister row);

    EdiWzRegister selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzRegister row);

    int updateByPrimaryKey(EdiWzRegister row);
}