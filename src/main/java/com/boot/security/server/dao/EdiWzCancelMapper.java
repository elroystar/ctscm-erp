package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzCancel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdiWzCancelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzCancel row);

    int insertSelective(EdiWzCancel row);

    EdiWzCancel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzCancel row);

    int updateByPrimaryKey(EdiWzCancel row);

    List<EdiWzCancel> selectBykPlantNumber(String truckPlantNumber);
}