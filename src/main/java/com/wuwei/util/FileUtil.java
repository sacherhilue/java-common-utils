package com.wuwei.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wuwei
 * @date 2018/7/7 11:53
 * @description
 */
public class FileUtil {

    public static final String fileDir = "D:\\files\\";
    private static Logger logger = Logger.getLogger(FileUtil.class.getName());

    /**
     * 保存上传的文件到磁盘
     *
     * @param file
     */
    public static String saveFileToDisk(MultipartFile file) {
        if (file == null) return null;
        String fileName = file.getOriginalFilename();
        if (createFileDir(fileDir)) {
            String filePath = fileDir + fileName;
            try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                byte[] bytes = file.getBytes();
                fos.write(bytes, 0, bytes.length);
                return filePath;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return null;
    }

    /**
     * 创建文件的存放目录
     *
     * @param dir
     * @return
     */
    private static boolean createFileDir(String dir) {
        if (StringUtils.isEmpty(dir)) return false;
        File file = new File(dir);
        //如果目录存在，就不用创建，否则就创建
        return file.exists() || file.mkdirs();
    }
}
