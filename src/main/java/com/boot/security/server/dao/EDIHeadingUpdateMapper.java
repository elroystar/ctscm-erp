package com.boot.security.server.dao;

import com.boot.security.server.model.EDIHeadingUpdate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EDIHeadingUpdateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIHeadingUpdate record);

    int insertSelective(EDIHeadingUpdate record);

    EDIHeadingUpdate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIHeadingUpdate record);

    int updateByPrimaryKey(EDIHeadingUpdate record);

    List<EDIHeadingUpdate> selectByHid(@Param("hid") Integer hid, @Param("shipper") String shipper);
}