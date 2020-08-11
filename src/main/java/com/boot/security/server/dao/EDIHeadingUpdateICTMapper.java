package com.boot.security.server.dao;

import com.boot.security.server.model.EDIHeadingUpdateICT;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EDIHeadingUpdateICTMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIHeadingUpdateICT record);

    int insertSelective(EDIHeadingUpdateICT record);

    EDIHeadingUpdateICT selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIHeadingUpdateICT record);

    int updateByPrimaryKey(EDIHeadingUpdateICT record);

    List<EDIHeadingUpdateICT> selectByHid(@Param("hid") Integer hid, @Param("shipper") String shipper);
}