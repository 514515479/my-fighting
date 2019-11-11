package com.kon.fighting.config.autorun;

import com.kon.fighting.common.constant.CustomStaticValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Description 自启动任务
 * @Author LK
 * @Date 2019/11/5 17:42
 * @Version V1.0
 **/
//@Order(value=1) // value值越小，执行的优先级越高
@Slf4j
@Component
public class AutoRunTask implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        createUploadPath();
    }

    private void createUploadPath() {
        File uploadPath = new File(CustomStaticValue.PATH);
        // 如果上传路径不存在
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
            log.info("上传路径{}创建成功",uploadPath.getPath());
        }
    }

}
