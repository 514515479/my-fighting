package com.kon.fighting.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kon.fighting.common.dto.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description HttpServlet相关工具
 * @Author LK
 * @Date 2019/10/17 11:32
 * @Version V1.0
 **/
public class HttpServletUtils {

    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    /**
     * 使用PrintWriter写出消息
     *
     * @param response
     * @param contentType 内容类型
     * @param result      响应消息
     */
    public static void writeResult(HttpServletResponse response, String contentType, Result result) {
        PrintWriter pw = null;
        try {
            response.setStatus(result.getStatus());
            response.setContentType(contentType);
            pw = response.getWriter();
            pw.write(new ObjectMapper().writeValueAsString(result));
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 使用PrintWriter写出消息
     *
     * @param response
     * @param result   响应消息
     */
    public static void writeResult(HttpServletResponse response, Result result) {
        writeResult(response, CONTENT_TYPE_JSON, result);
    }

    /**
     * 使用PrintWriter写出消息并修改响应状态码
     *
     * @param response
     * @param result   响应消息
     */
    public static void writeResultAndChangeStatus(HttpServletResponse response, Result result) {
        response.setStatus(result.getStatus());
        writeResult(response, CONTENT_TYPE_JSON, result);
    }

    /**
     * 使用PrintWriter写出消息
     *
     * @param response
     * @param contentType 内容类型
     * @param msg      响应消息
     */
    public static void writeMsg(HttpServletResponse response, String contentType, String msg) {
        PrintWriter pw = null;
        try {
            response.setContentType(contentType);
            pw = response.getWriter();
            pw.write(new ObjectMapper().writeValueAsString(msg));
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 将value设置到header中
     *
     * @param response
     * @param key
     * @param value
     */
    public static void setHeader(HttpServletResponse response, String key, String value) {
        response.setHeader(key, value);
    }

}
