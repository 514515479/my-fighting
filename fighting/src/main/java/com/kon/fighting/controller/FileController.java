package com.kon.fighting.controller;

import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.common.dto.Result;
import com.kon.fighting.common.enums.ResultEnum;
import com.kon.fighting.utils.DateUtils;
import com.kon.fighting.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 文件控制器
 * @Author LK
 * @Date 2019/11/5 17:25
 * @Version V1.0
 **/
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 上传单个文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        // 检查并创建当日目录
        String dayStr = DateUtils.getDay();
        FileUtils.checkDir(CustomStaticValue.PATH + dayStr);
        // 文件校验
        String checkResult = FileUtils.checkFile(file);
        if (checkResult != null) {
            return Result.fail(checkResult);
        }
        // 上传
        Map<String, String> result = FileUtils.uploadFile(file, dayStr);
        String error = result.get("error");
        if (error == null) {
            return Result.okData(result.get("url"));
        } else {
            return Result.fail(error);
        }
    }

    /**
     * 上传多个文件
     *
     * @param request
     * @return
     */
    @PostMapping("/multiUpload")
    public Result multiUpload(HttpServletRequest request) {
        // 结果Map
        Map<String, Object> resultMap = new HashMap<>();
        // 检查并创建当日目录
        String dayStr = DateUtils.getDay();
        FileUtils.checkDir(CustomStaticValue.PATH + dayStr);
        // 文件list
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        // 校验结果
        String errorMsg = FileUtils.checkFileList(fileList);
        if (errorMsg != null) {
            resultMap.put("error", errorMsg);
            return Result.fail(ResultEnum.FILE_UPLOAD_FAIL, resultMap);
        }
        // 上传文件
        resultMap = FileUtils.uploadFileList(fileList, dayStr);
        return Result.okData(resultMap);
    }

}
