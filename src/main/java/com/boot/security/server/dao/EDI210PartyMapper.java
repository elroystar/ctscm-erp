package com.boot.security.server.dao;

import com.boot.security.server.model.EDI210Party;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EDI210PartyMapper {
    int insert(EDI210Party row);

    int insertBatch(@Param("list") List<EDI210Party> list);

    int deleteByInvoiceId(@Param("invoiceId") Long invoiceId);

    List<EDI210Party> selectByInvoiceId(@Param("invoiceId") Long invoiceId);

    EDI210Party selectByInvoiceIdAndRole(@Param("invoiceId") Long invoiceId,
                                          @Param("role") String role);

    int updateByPrimaryKeySelective(EDI210Party row);
}
