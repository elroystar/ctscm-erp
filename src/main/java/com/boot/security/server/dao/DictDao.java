package com.boot.security.server.dao;

import com.boot.security.server.model.Dict;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictDao {

    @Select("select * from t_dict t where t.id = #{id}")
    Dict getById(Long id);

    @Delete("delete from t_dict where id = #{id}")
    int delete(Long id);

    int update(Dict dict);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_dict(name, type, k, val, createTime, updateTime) values(#{name}, #{type}, #{k}, #{val}, now(), now())")
    int save(Dict dict);

    int count(@Param("params") Map<String, Object> params);

    List<Dict> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                    @Param("limit") Integer limit);

    @Select("select * from t_dict t where t.type = #{type} and k = #{k}")
    Dict getByTypeAndK(@Param("type") String type, @Param("k") String k);

    @Select("select * from t_dict t where t.type = #{type}")
    List<Dict> listByType(String type);

    List<Dict> getAllDict(@Param("params") Map<String, Object> params);

    @Select("select t.val from t_dict t where t.type = #{type}")
    List<String> listValueByType(String region);

    @Select("select * from t_dict t where t.name = #{name}")
    List<Dict> listByName(String name);

    @Select("select type from t_dict t where t.name = 'region' and t.k = #{k}")
    List<String> getRegionTypeByK(String k);
}
