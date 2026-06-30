package com.boot.security.server.dao;

import com.boot.security.server.model.EDI210Charge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EDI210ChargeMapper {
    int insert(EDI210Charge row);

    int insertBatch(@Param("list") List<EDI210Charge> list);

    int deleteByInvoiceId(@Param("invoiceId") Long invoiceId);

    List<EDI210Charge> selectByInvoiceId(@Param("invoiceId") Long invoiceId);
}
