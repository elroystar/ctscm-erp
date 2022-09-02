package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzDevice;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiWzDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzDevice row);

    int insertSelective(EdiWzDevice row);

    EdiWzDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzDevice row);

    int updateByPrimaryKey(EdiWzDevice row);
}