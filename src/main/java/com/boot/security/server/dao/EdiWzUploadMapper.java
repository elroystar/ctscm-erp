package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzUpload;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdiWzUploadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzUpload row);

    int insertSelective(EdiWzUpload row);

    EdiWzUpload selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzUpload row);

    int updateByPrimaryKey(EdiWzUpload row);

    List<EdiWzUpload> selectBykPlantNumber(String truckPlantNumber);

    void updateStatusByTruckPlantNumber(EdiWzUpload ediWzUpload);
}