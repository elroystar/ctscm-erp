package com.boot.security.server.dao;

import com.boot.security.server.model.EdiLoadIHub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EdiLoadIHubMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiLoadIHub record);

    int insertSelective(EdiLoadIHub record);

    EdiLoadIHub selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiLoadIHub record);

    int updateByPrimaryKey(EdiLoadIHub record);

    int count(@Param("params") Map<String, Object> params);

    List<EdiLoadIHub> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                           @Param("limit") Integer limit);
}