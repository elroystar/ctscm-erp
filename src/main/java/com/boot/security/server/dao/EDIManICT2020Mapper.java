package com.boot.security.server.dao;

import com.boot.security.server.model.EDIManICT2020;
import com.boot.security.server.model.EDIManICT2020DTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EDIManICT2020Mapper {
    int deleteByPrimaryKey(Integer manid);

    int insert(EDIManICT2020 record);

    int insertSelective(EDIManICT2020 record);

    EDIManICT2020 selectByPrimaryKey(Integer manid);

    int updateByPrimaryKeySelective(EDIManICT2020 record);

    int updateByPrimaryKey(EDIManICT2020 record);

    List<Integer> count(@Param("params") Map<String, Object> params);

    List<EDIManICT2020DTO> list(@Param("params") Map<String, Object> params,
                                @Param("offset") Integer offset,
                                @Param("limit") Integer limit);

    List<EDIManICT2020DTO> listAll(@Param("params") Map<String, Object> params);

    List<EDIManICT2020DTO> findByIdArray(String[] yfSplit);
}