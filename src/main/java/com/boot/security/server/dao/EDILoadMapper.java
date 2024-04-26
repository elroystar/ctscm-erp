package com.boot.security.server.dao;

import com.boot.security.server.dto.load.EDILoad;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EDILoadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDILoad row);

    int insertSelectiveICT(EDILoad row);

    int countICT(@Param("params") Map<String, Object> params);

    List<EDILoad> listICT(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                         @Param("limit") Integer limit);

    EDILoad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDILoad row);

    int updateByPrimaryKey(EDILoad row);

}