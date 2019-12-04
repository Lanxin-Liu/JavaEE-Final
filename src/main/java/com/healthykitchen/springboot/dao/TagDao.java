package com.healthykitchen.springboot.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao {

    @Select("select tag_id from Tag where tag_id=#{tagName}")
    int getTagId(String tagName);

}
