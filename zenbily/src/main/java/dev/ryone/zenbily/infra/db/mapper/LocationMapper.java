package dev.ryone.zenbily.infra.db.mapper;

import dev.ryone.zenbily.infra.db.entity.LocationEntity;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LocationMapper {

    @Select("SELECT * FROM locations LIMIT #{limit}")
    List<LocationEntity> findAll(int limit);

    @Select("SELECT * FROM locations WHERE user_id = #{userId}")
    LocationEntity findByUserId(String userId);

    @Insert("INSERT INTO locations (user_id, latitude, longitude) VALUES (#{userId}, #{latitude}, #{longitude})")
    void insertLocation(String userId, double latitude, double longitude);

    @Update("UPDATE locations SET latitude = #{latitude}, longitude = #{longitude} WHERE user_id = #{userId}")
    void updateLocation(String userId, double latitude, double longitude);
}
