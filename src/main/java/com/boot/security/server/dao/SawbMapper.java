package com.boot.security.server.dao;

import com.boot.security.server.dto.load.SawbDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SawbMapper {

    int insertSelective(SawbDTO row);

    int count(@Param("params") Map<String, Object> params);

    List<SawbDTO> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

}