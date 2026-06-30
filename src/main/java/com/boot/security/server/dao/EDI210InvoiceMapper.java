package com.boot.security.server.dao;

import com.boot.security.server.dto.EDI210ExcelRowDTO;
import com.boot.security.server.dto.EDI210ListDTO;
import com.boot.security.server.model.EDI210Invoice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EDI210InvoiceMapper {
    int insert(EDI210Invoice row);

    int insertSelective(EDI210Invoice row);

    int deleteByPrimaryKey(@Param("id") Long id);

    EDI210Invoice selectByPrimaryKey(@Param("id") Long id);

    Long selectIdByUk(@Param("region") String region,
                      @Param("scac") String scac,
                      @Param("invNo") String invNo);

    int updateByPrimaryKeySelective(EDI210Invoice row);

    int count(@Param("params") Map<String, Object> params);

    List<EDI210ListDTO> list(@Param("params") Map<String, Object> params,
                             @Param("offset") Integer offset,
                             @Param("limit") Integer limit);

    /** 导出宽表（按筛选 + JOIN，每条 charge 一行）。 */
    List<EDI210ExcelRowDTO> exportFlat(@Param("params") Map<String, Object> params);
}
