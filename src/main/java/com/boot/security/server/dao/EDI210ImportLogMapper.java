package com.boot.security.server.dao;

import com.boot.security.server.model.EDI210ImportLog;
import org.springframework.stereotype.Repository;

@Repository
public interface EDI210ImportLogMapper {
    int insert(EDI210ImportLog row);
}
