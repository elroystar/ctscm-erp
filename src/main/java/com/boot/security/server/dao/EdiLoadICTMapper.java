package com.boot.security.server.dao;

import com.boot.security.server.model.EdiLoadICT;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiLoadICTMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiLoadICT record);

    int insertSelective(EdiLoadICT record);

    EdiLoadICT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiLoadICT record);

    int updateByPrimaryKey(EdiLoadICT record);
}