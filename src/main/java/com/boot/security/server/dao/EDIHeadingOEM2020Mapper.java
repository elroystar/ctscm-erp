package com.boot.security.server.dao;

import com.boot.security.server.model.EDIHeadingOEM2020;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EDIHeadingOEM2020Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIHeadingOEM2020 record);

    int insertSelective(EDIHeadingOEM2020 record);

    EDIHeadingOEM2020 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIHeadingOEM2020 record);

    int updateByPrimaryKey(EDIHeadingOEM2020 record);

    int count(@Param("params") Map<String, Object> params);

    List<EDIHeadingOEM2020> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                                 @Param("limit") Integer limit);
}