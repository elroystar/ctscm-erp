package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzTrack;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiWzTrackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzTrack row);

    int insertSelective(EdiWzTrack row);

    EdiWzTrack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzTrack row);

    int updateByPrimaryKey(EdiWzTrack row);
}