package com.boot.security.server.dao;

import com.boot.security.server.dto.ShipperDetailDTO;
import com.boot.security.server.model.EDIManOEM2020;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EDIManOEM2020Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIManOEM2020 record);

    int insertSelective(EDIManOEM2020 record);

    EDIManOEM2020 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIManOEM2020 record);

    int updateByPrimaryKey(EDIManOEM2020 record);

    int count(@Param("params") Map<String, Object> params, @Param("isCountry") Integer isCountry);

    List<ShipperDetailDTO> list(@Param("params") Map<String, Object> params,
                                @Param("isCountry") Integer isCountry,
                                @Param("offset") Integer offset,
                                @Param("limit") Integer limit);

    List<ShipperDetailDTO> listAll(@Param("params") Map<String, Object> params, @Param("isCountry") Integer isCountry);
}