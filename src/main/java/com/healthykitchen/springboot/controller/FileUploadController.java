package com.healthykitchen.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.StorageService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 16:01 2019/12/08
 * @version: v1.0
 */

@Controller
public class FileUploadController {

    /**
     * 商品图片上传
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam(value = "pic") MultipartFile pic, @RequestParam Map param) throws ParseException {
        System.err.println("传过来的值" + param);
        if (pic.isEmpty()) {
            System.err.println("上传文件不可为空");
        }
        String fileName = pic.getOriginalFilename();//得到文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//得到后缀名
        System.err.println("suffixName:" + suffixName);
        String filepath = "/Users/anonym_co/Desktop/";//指定图片上传到哪个文件夹的路径
        fileName = UUID.randomUUID() + suffixName;//重新命名图片，变成随机的名字
        System.err.println("fileName:" + fileName);
        File dest = new File(filepath + fileName);//在上传的文件夹处创建文件
        try {
            pic.transferTo(dest);//把上传的图片写入磁盘中
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultFactory.buildSuccessResult(pic);

    }
}
