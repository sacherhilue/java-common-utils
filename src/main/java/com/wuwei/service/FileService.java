package com.wuwei.service;

import com.wuwei.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wuwei
 * @date 2018/7/7 10:57
 * @description
 */
public interface FileService {
    Result findAllUser();

    Result downloadExcelTemplate(HttpServletResponse response);

    Result importExcelUser(MultipartFile file);

    Result exportExcelUser(HttpServletResponse response);

    Result uploadFile(MultipartFile file);
}
