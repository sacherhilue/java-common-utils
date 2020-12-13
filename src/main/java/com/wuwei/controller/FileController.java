package com.wuwei.controller;

import com.wuwei.entity.Result;
import com.wuwei.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wuwei
 * @date 2018/7/7 10:35
 * @description 一些测试方法
 */
@RestController
@RequestMapping("/test")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/findAllUser")
    public Result findAllUser() {
        return fileService.findAllUser();
    }

    @GetMapping("/downloadExcelTemplate")
    public Result downloadExcelTemplate(HttpServletResponse response) {
        return fileService.downloadExcelTemplate(response);
    }

    @PostMapping("/importExcelUser")
    public Result importExcelUser(@RequestParam("file") MultipartFile file) {
        return fileService.importExcelUser(file);
    }

    @GetMapping("/exportExcelUser")
    public Result exportExcelUser(HttpServletResponse response) {
        return fileService.exportExcelUser(response);
    }

    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
