package com.boot.security.server.dao;

import com.boot.security.server.model.EdiLoadIHub;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiLoadIHubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiLoadIHub row);

    int insertSelective(EdiLoadIHub row);

    EdiLoadIHub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiLoadIHub row);

    int updateByPrimaryKey(EdiLoadIHub row);
}