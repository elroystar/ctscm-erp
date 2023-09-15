package com.boot.security.server.dao;

import com.boot.security.server.model.EdiLoadIct;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiLoadIctMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiLoadIct row);

    int insertSelective(EdiLoadIct row);

    EdiLoadIct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiLoadIct row);

    int updateByPrimaryKey(EdiLoadIct row);
}