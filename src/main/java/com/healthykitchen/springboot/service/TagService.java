package com.healthykitchen.springboot.service;

import com.healthykitchen.springboot.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    public int getTagId(String tagName){
        return tagDao.getTagId(tagName);
    }
}
