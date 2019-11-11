package com.kon.fighting.common.dto;

import com.kon.fighting.common.enums.ResultEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 消息返回类
 * @Author LK
 * @Date 2019/10/16 11:28
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class Result implements Serializable {

    private static final long serialVersionUID = -8606453552559000068L;

    /**
     * 成功状态码
     */
    private static final int OK_STATUS = 200;

    /**
     * 失败状态码
     */
    private static int FAIL_STATUS = 100;

    /**
     * 成功默认消息
     */
    private static final String DEFAULT_OK_MSG = "操作成功";

    /**
     * 失败默认消息
     */
    private static final String DEFAULT_FAIL_MSG = "操作失败";

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 禁止通过new的方式，推荐直接使用静态方法
     */
    private Result() {

    }

    private Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功
     *
     * @return
     */
    public static Result ok() {
        return new Result().setStatus(OK_STATUS).setMsg(DEFAULT_OK_MSG);
    }

    /**
     * 响应成功
     *
     * @param msg 响应消息内容
     * @return
     */
    public static Result ok(String msg) {
        return new Result().setStatus(OK_STATUS).setMsg(msg);
    }

    /**
     * 响应成功
     *
     * @param data 响应数据
     * @return
     */
    public static Result okData(Object data) {
        return new Result(OK_STATUS, null, data);
    }

    /**
     * 响应成功
     *
     * @param status 响应业务状态
     * @param msg    响应消息内容
     * @param data   响应数据
     * @return
     */
    public static Result ok(int status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    /**
     * 响应失败
     *
     * @return
     */
    public static Result fail() {
        return fail(DEFAULT_FAIL_MSG);
    }

    /**
     * 响应失败
     *
     * @param msg 响应消息内容
     * @return
     */
    public static Result fail(String msg) {
        return new Result().setStatus(FAIL_STATUS).setMsg(msg);
    }

    /**
     * 响应失败
     *
     * @param status 响应业务状态
     * @param msg    响应消息内容
     * @return
     */
    public static Result fail(int status, String msg) {
        return new Result().setStatus(status).setMsg(msg);
    }

    /**
     * 响应失败
     *
     * @param status 响应业务状态
     * @param msg    响应消息内容
     * @param data   响应数据
     * @return
     */
    public static Result fail(int status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    //-----------------------------------------------响应枚举-----------------------------------------------

    /**
     * 响应成功
     *
     * @param resultEnum 响应枚举
     * @return
     */
    public static Result ok(ResultEnum resultEnum) {
        return new Result().setStatus(resultEnum.getIntCodeValue()).setMsg(resultEnum.getDescribe());
    }

    /**
     * 响应成功
     *
     * @param resultEnum 响应枚举
     * @param data       数据
     * @return
     */
    public static Result ok(ResultEnum resultEnum, Object data) {
        return new Result()
                .setStatus(resultEnum.getIntCodeValue())
                .setMsg(resultEnum.getDescribe())
                .setData(data);
    }

    /**
     * 响应失败
     *
     * @param resultEnum 响应枚举
     * @return
     */
    public static Result fail(ResultEnum resultEnum) {
        return new Result().setStatus(resultEnum.getIntCodeValue()).setMsg(resultEnum.getDescribe());
    }

    /**
     * 响应失败
     *
     * @param resultEnum 响应枚举
     * @param data       数据
     * @return
     */
    public static Result fail(ResultEnum resultEnum, Object data) {
        return new Result()
                .setStatus(resultEnum.getIntCodeValue())
                .setMsg(resultEnum.getDescribe())
                .setData(data);
    }

    //-----------------------------------------------crud枚举-----------------------------------------------

    /**
     * 增删改的默认相应
     *
     * @param num 影响行数
     * @return
     */
    public static Result crud(int num) {
        if (num > 0) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 增删改的默认相应
     *
     * @param result 结果
     * @return
     */
    public static Result crud(boolean result) {
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}
