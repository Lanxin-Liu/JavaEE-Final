package com.healthykitchen.springboot.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipDao {
    @Select("select tip_content from Tip where tip_id=#{tipId}")
    String getTips(int tipId);
}
