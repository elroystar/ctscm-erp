package com.boot.security.server.dao;

import com.boot.security.server.model.EDI210Tax;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EDI210TaxMapper {
    int insert(EDI210Tax row);

    int deleteByInvoiceId(@Param("invoiceId") Long invoiceId);

    EDI210Tax selectByInvoiceId(@Param("invoiceId") Long invoiceId);

    int updateByPrimaryKeySelective(EDI210Tax row);
}
