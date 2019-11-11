package com.kon.fighting.common.enums;

/**
 * @Description 菜单类型枚举
 * @Author LK
 * @Date 2019/11/3 12:12
 * @Version V1.0
 **/
public enum MenuTypeEnum implements BaseEnum {

    CATALOG("0", "目录"),
    MENU("1", "菜单"),
    PERMISSION("2", "权限");

    private String codeValue;

    private String describe;

    MenuTypeEnum(String codeValue, String describe) {
        this.codeValue = codeValue;
        this.describe = describe;
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
