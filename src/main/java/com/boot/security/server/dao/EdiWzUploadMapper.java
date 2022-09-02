package com.boot.security.server.dao;

import com.boot.security.server.model.EdiWzUpload;
import org.springframework.stereotype.Repository;

@Repository
public interface EdiWzUploadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EdiWzUpload row);

    int insertSelective(EdiWzUpload row);

    EdiWzUpload selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EdiWzUpload row);

    int updateByPrimaryKey(EdiWzUpload row);
}