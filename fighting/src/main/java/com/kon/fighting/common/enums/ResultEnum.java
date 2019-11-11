package com.kon.fighting.common.enums;

/**
 * @Description 响应枚举
 * @Author LK
 * @Date 2019/10/17 11:16
 * @Version V1.0
 **/
public enum ResultEnum implements BaseEnum {

    LOGIN_SUCCESS("200", "登录成功"),
    LOGOUT_SUCCESS("200", "注销成功"),

    USERNAME_OR_PASSWORD_ERROR("401", "用户名或密码输入错误"),
    LOCKED("401", "账户被锁定，请联系管理员"),
    CREDENTIALS_EXPIRED("401", "账户过期，请联系管理员"),
    DISABLED("401", "账号被禁用，请联系管理员"),
    LOGIN_FAIL("401", "登录失败"),

    NO_LOGIN("401", "尚未登录，请登录"),
    TOKEN_IS_BLACKLIST("401", "Token非法，请重新登录"),
    IP_IS_CHANGED("401", "IP改变，请重新登录"),
    LOGIN_IS_OVERDUE("401", "登录过期，请重新登录"),

    NO_ACCESS("403", "权限不足,请联系管理员"),

    FILE_UPLOAD_FAIL("100", "文件上传失败"),
    FILE_UPLOAD_SUCCESS("200", "文件上传成功"),
    ;

    private String codeValue;

    private String describe;

    ResultEnum(String codeValue, String describe) {
        this.codeValue = codeValue;
        this.describe = describe;
    }

    /**
     * 获取CodeValue的int类型
     *
     * @return
     */
    public int getIntCodeValue() {
        return Integer.valueOf(codeValue);
    }

    @Override
    public String getCodeValue() {
        return codeValue;
    }

    @Override
    public String getDescribe() {
        return describe;
    }

}
