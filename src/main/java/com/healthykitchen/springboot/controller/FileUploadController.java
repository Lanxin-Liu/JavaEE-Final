package com.healthykitchen.springboot.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {

        this.storageService = storageService;

    }

    // 跳转到上传图片的界面
    @GetMapping("/upload")
    public String showUploadPage(Model model) throws IOException {

        return "uploadForm";

    }

    // 以POST方式获得请求，图片上传成功后，以JSON格式将图片返回，用于回显 
    @PostMapping("/upload")
    @ResponseBody
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        String imgUrl = "/upload-dir/" + file.getOriginalFilename();
        storageService.store(file);
        return ResultFactory.buildSuccessResult(imgUrl);
    }


}
