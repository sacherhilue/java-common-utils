package com.wuwei.service;

import com.wuwei.entity.Result;
import com.wuwei.entity.User;
import com.wuwei.util.ExcelUtil;
import com.wuwei.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wuwei
 * @date 2018/7/7 10:58
 * @description
 */
@Service
public class FileServiceImpl implements FileService {

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * 下载空白的Excel文件模板
     *
     * @param response
     */
    @Override
    public Result downloadExcelTemplate(HttpServletResponse response) {
        try {
            ExcelUtil.exportExcel(User.class, "用户表.xls", response);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    /**
     * 导入Excel用户
     *
     * @param file
     */
    @Override
    public Result importExcelUser(MultipartFile file) {
        if (file == null) return Result.getFailedResult("文件不能为空！");
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return Result.getFailedResult("文件格式不正确！");
        }
        try {
            //从上传的excel中读取数据转换成User集合
            List<User> users = ExcelUtil.getUserList(file);
            //List<User> users = ExcelUtil.importExcel(file, 0, 1, User.class);
            for (User user : users) {
                //TODO 保存至数据库
                System.out.println(user);
            }
            return Result.getSuccessResult("导入成功!");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return Result.getSuccessResult("导入失败!");
        }
    }

    /**
     * 导出用户信息到Excel中
     *
     * @param response
     */
    @Override
    public Result exportExcelUser(HttpServletResponse response) {
        List<User> users = new LinkedList<>();
        users.add(new User("Tom", "male", "Nanjing"));
        users.add(new User("Jack", "male", "Shanghai"));
        users.add(new User("Marry", "female", "Hangzhou"));
        try {
            ExcelUtil.exportExcel(users, User.class, "用户表.xls", response);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    /**
     * 客户端上传文件到服务器
     *
     * @param file
     * @return
     */
    @Override
    public Result uploadFile(MultipartFile file) {
        if (file == null) return Result.getFailedResult("上传的文件不能为空！");
        String filePath = FileUtil.saveFileToDisk(file);
        return Result.getSuccessResult(filePath);
    }
}
