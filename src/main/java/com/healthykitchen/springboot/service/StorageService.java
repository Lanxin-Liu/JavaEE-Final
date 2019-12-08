package com.healthykitchen.springboot.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 16:05 2019/12/08
 * @version: v1.0
 */
public interface StorageService {
    void init();
    void store(MultipartFile file);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();
}
