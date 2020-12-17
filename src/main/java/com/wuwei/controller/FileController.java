package com.wuwei.controller;

import com.wuwei.entity.Result;
import com.wuwei.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wuwei
 * @date 2018/7/7 10:35
 * @description 一些测试接口
 */
@Api(value = "文件操作controller", tags = {"文件操作接口"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息")
    @GetMapping("/findAllUser")
    public Result findAllUser() {
        return fileService.findAllUser();
    }

    @ApiOperation(value = "下载Excel导入模板", notes = "下载Excel导入模板")
    @GetMapping("/downloadExcelTemplate")
    public Result downloadExcelTemplate(HttpServletResponse response) {
        return fileService.downloadExcelTemplate(response);
    }

    @ApiOperation(value = "导入Excel用户", notes = "导入Excel用户")
    @PostMapping("/importExcelUser")
    public Result importExcelUser(@RequestParam("file") MultipartFile file) {
        return fileService.importExcelUser(file);
    }

    @ApiOperation(value = "导出Excel用户", notes = "导出Excel用户")
    @GetMapping("/exportExcelUser")
    public Result exportExcelUser(HttpServletResponse response) {
        return fileService.exportExcelUser(response);
    }

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
