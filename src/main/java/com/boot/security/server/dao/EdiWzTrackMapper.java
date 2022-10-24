package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzTrack;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdiWzTrackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzTrack row);

    int insertSelective(EdiWzTrack row);

    EdiWzTrack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzTrack row);

    int updateByPrimaryKey(EdiWzTrack row);

    List<EdiWzTrack> selectBykPlantNumber(String truckPlantNumber);

    void updateStatusByTruckPlantNumber(EdiWzTrack ediWzTrack);
}