package com.healthykitchen.springboot.dao;

import com.healthykitchen.springboot.pojo.Material;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialDao {
    @Select("select * from Material where material_name=#{materialName}")
    Material getMaterialCalorie(String materialName);
}
