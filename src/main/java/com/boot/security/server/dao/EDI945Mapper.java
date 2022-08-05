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

    List<EDI945> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                      @Param("limit") Integer limit);

    void editTruck(EditTruckDTO editTruckDTO);

    void editTruckOEM(EditTruckDTO editTruckDTO);

    List<EDI945> selectByIds(@Param("ids") String[] split);

    List<EDI945> selectOEMByIds(@Param("ids") String[] split);

    List<EDI945> selectByGPSState(@Param("state") int state);

    List<EDI945> selectByTruckPlantNumber(@Param("truckPlantNumber") String truckPlantNumber);

    List<EDI945> selectGPSByTruckPlantNumber(@Param("truckPlantNumber") String truckPlantNumber);

    void insertGPS(EDI945 edi945GPS);

    int countOEM(@Param("params") Map<String, Object> params);

    List<EDI945> listOEM(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                         @Param("limit") Integer limit);

    void updateByTruckPlantNumber(EDI945 edi945);

    void updateGPSByTruckPlantNumber(EDI945 edi945);
}