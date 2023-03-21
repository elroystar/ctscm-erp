package com.boot.security.server.dao;

import com.boot.security.server.dto.EditTruckDTO;
import com.boot.security.server.model.EDI945;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EDI945Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EDI945 row);

    int insertSelective(EDI945 row);

    EDI945 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EDI945 row);

    int updateByPrimaryKey(EDI945 row);

    int count(@Param("params") Map<String, Object> params);

    int countOEM(@Param("params") Map<String, Object> params);

    int countICT(@Param("params") Map<String, Object> params);

    List<EDI945> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                      @Param("limit") Integer limit);

    List<EDI945> listOEM(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                         @Param("limit") Integer limit);

    List<EDI945> listICT(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                         @Param("limit") Integer limit);

    List<EDI945> selectByGPSState(@Param("state") int state);

    List<EDI945> selectByGPSStateOEM(@Param("state") int state);

    List<EDI945> selectByGPSStateICT(@Param("state") int state);

    List<EDI945> selectGPSByGpsDevice(@Param("gpsDevice") String gpsDevice);

    List<EDI945> selectGPSByGpsDeviceOEM(@Param("gpsDevice") String gpsDevice);

    List<EDI945> selectGPSByGpsDeviceICT(@Param("gpsDevice") String gpsDevice);

    void insertGPS(EDI945 edi945GPS);

    void insertGPSOEM(EDI945 edi945GPS);

    void insertGPSICT(EDI945 edi945GPS);

    void updateByTruckPlantNumber(EDI945 edi945);

    void updateByTruckPlantNumberOEM(EDI945 edi945);

    void updateByTruckPlantNumberICT(EDI945 edi945);

    void updateGPSByTruckPlantNumber(EDI945 edi945);

    void updateGPSByTruckPlantNumberOEM(EDI945 edi945);

    void updateGPSByTruckPlantNumberICT(EDI945 edi945);

    void editTruckBy(EditTruckDTO editTruckDTO);

    void editTruckByOEM(EditTruckDTO editTruckDTO);

    void editTruckByICT(EditTruckDTO editTruckDTO);

}