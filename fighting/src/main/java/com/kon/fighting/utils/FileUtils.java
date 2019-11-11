package com.kon.fighting.utils;

import com.kon.fighting.common.constant.CustomStaticValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * @Description 文件工具类
 * @Author LK
 * @Date 2019/11/6 9:54
 * @Version V1.0
 **/
@Slf4j
public class FileUtils {

    /**
     * 校验文件list
     *
     * @param fileList 文件list
     * @return 返回null说明文件全部正常
     */
    public static String checkFileList(List<MultipartFile> fileList) {
        StringBuilder sb = new StringBuilder();
        // 定义错误接收变量
        String error;
        // 校验文件
        for (int i = 0; i < fileList.size(); i++) {
            error = FileUtils.checkFile(fileList.get(i));
            if (error != null) {
                sb.append("第").append(i + 1).append("个文件").append(error).append("\n");
            }
        }
        String errorMsg = sb.toString();
        // 判断校验文件是否通过
        if (!errorMsg.isEmpty()) {
            return errorMsg;
        } else {
            return null;
        }
    }

    /**
     * 上传多个文件
     *
     * @param fileList 文件list
     * @param dayStr   当日字符串格式
     * @return
     */
    public static Map<String, Object> uploadFileList(List<MultipartFile> fileList, String dayStr) {
        Map<String, Object> resultMap = new HashMap<>();
        // 上传成功的url集合
        List<String> urlList = new ArrayList<>();
        // 响应结果
        StringBuilder sb = new StringBuilder();
        // 上传的单个结果变量
        Map<String, String> result;
        // 定义错误接收变量
        String error;
        for (int i = 0; i < fileList.size(); i++) {
            result = FileUtils.uploadFile(fileList.get(i), dayStr);
            error = result.get("error");
            if (error == null) {
                urlList.add(result.get("url"));
            } else {
                sb.append("第").append(i + 1).append("个文件").append(error).append("\n");
            }
        }
        if (urlList.size() > 0) {
            resultMap.put("urlList", urlList);
        }
        String errorMsg = sb.toString();
        if (!errorMsg.isEmpty()) {
            resultMap.put("urlList", errorMsg);
        }
        return resultMap;
    }

    /**
     * 上传文件的基础校验
     *
     * @return
     */
    public static String checkFile(MultipartFile file) {
        // 是否是空文件校验
        if (file.isEmpty()) {
            return "上传失败,参数错误";
        }
        // 是否没有后缀校验
        String fileName = file.getOriginalFilename();
        String fileFormat = FileUtils.getFileFormat(fileName);
        if (null == fileFormat) {
            return "上传失败,缺少文件格式";
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param file       前端传来的文件
     * @param currentDay 当前日期字符串
     * @return
     */
    public static Map<String, String> uploadFile(MultipartFile file, String currentDay) {
        Map<String, String> result = new HashMap<>();
        String fileName = file.getOriginalFilename();
        String fileFormat = FileUtils.getFileFormat(fileName);
        // 生成新的文件名
        fileName = getFileMD5String(file) + fileFormat;
        // 文件路径
        String filePath = currentDay + "/" + fileName;
        File newFile = new File(CustomStaticValue.PATH + filePath);
        if (newFile.exists()) {
            log.info("文件已存在,直接返回url");
            result.put("url", CustomStaticValue.STATIC_ACCESS_PATH.replaceAll("\\**", "") + filePath);
        } else {
            try {
                file.transferTo(newFile);
                log.info("上传成功");
                result.put("url", CustomStaticValue.STATIC_ACCESS_PATH.replaceAll("\\**", "") + filePath);
            } catch (IOException e) {
                log.error(e.toString(), e);
                result.put("error", "上传失败,出大问题");
            }
        }
        return result;
    }

    /**
     * 判断该文件夹是否存在，不存在则创建，包括父级
     *
     * @param path
     */
    public static void checkDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 获取文件格式
     *
     * @param fileName 完整文件名
     * @return
     */
    public static String getFileFormat(String fileName) {
        if (null == fileName) {
            return null;
        }
        String[] formatNames = fileName.split("\\.");
        if (formatNames.length <= 1) {
            return null;
        }
        String format = "." + formatNames[formatNames.length - 1];
        return format;
    }

    /**
     * 得到文件的md5后，再用File的操作方法判断改文件在目标文件夹是否存在
     *
     * @param file
     * @return
     */
    public static String getFileMD5String(MultipartFile file) {
        InputStream fis = null;
        try {
            MessageDigest messageDigest = null;
            messageDigest = MessageDigest.getInstance("MD5");
            fis = file.getInputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) > 0) {
                messageDigest.update(buffer, 0, length);
            }
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
