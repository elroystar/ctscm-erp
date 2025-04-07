package com.boot.security.server.dao;

import com.boot.security.server.dto.load.DimTransfer;
import com.boot.security.server.dto.load.DimTransferSH;
import com.boot.security.server.model.EDI945;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DimTransferMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DimTransfer row);

    int insertSelective(DimTransfer row);

    int insertSelectiveSH(DimTransferSH row);

    DimTransfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DimTransfer row);

    int updateByPrimaryKey(DimTransfer row);

    int count(@Param("params") Map<String, Object> params);

    int countSH(@Param("params") Map<String, Object> params);

    List<DimTransfer> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                           @Param("limit") Integer limit);

    List<DimTransferSH> listSH(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                               @Param("limit") Integer limit);

    List<DimTransfer> selectByHawb(@Param("hawb") String hawb);

    List<DimTransfer> selectByHawbAndPalletId(@Param("hawb") String hawb, @Param("palletId") String palletIdOem);

    List<DimTransfer> getSendPddData(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                                @Param("limit") Integer limit);
}