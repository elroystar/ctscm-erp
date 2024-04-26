package com.boot.security.server.dao;

import com.boot.security.server.dto.load.EDIDimTransfer;
import org.springframework.stereotype.Repository;

@Repository
public interface EDIDimTransferMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDIDimTransfer row);

    int insertSelective(EDIDimTransfer row);

    EDIDimTransfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDIDimTransfer row);

    int updateByPrimaryKey(EDIDimTransfer row);
}