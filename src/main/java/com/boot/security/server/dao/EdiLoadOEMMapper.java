package com.boot.security.server.dao;

import com.boot.security.server.model.EdiLoadOEM;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiLoadOEMMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiLoadOEM record);

    int insertSelective(EdiLoadOEM record);

    EdiLoadOEM selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiLoadOEM record);

    int updateByPrimaryKey(EdiLoadOEM record);
}