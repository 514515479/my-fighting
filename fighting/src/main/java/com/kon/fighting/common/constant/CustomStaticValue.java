package com.kon.fighting.common.constant;

/**
 * @Description 静态常量-读取配置文件
 * @Author LK
 * @Date 2019/10/17 21:23
 * @Version V1.0
 **/
public class CustomStaticValue {

    public static String SUFFIX;

    public static int EXPIRATION_SECOND;

    public static int VALID_TIME;

    public static int OLD_CHANGE_NEW_SECOND;

    public static String PATH;

    public static String STATIC_ACCESS_PATH;

    public static boolean INDIVIDUAL_MODEL;

    /**
     * 初始化静态常量的方法
     *
     * @param customValue
     */
    public static void init(CustomValue customValue) {
        CustomStaticValue.SUFFIX = customValue.getSuffix();
        CustomStaticValue.EXPIRATION_SECOND = customValue.getExpirationSecond();
        CustomStaticValue.VALID_TIME = customValue.getValidTime();
        CustomStaticValue.OLD_CHANGE_NEW_SECOND = customValue.getOldChangeNewSecond();
        CustomStaticValue.PATH = customValue.getPath();
        CustomStaticValue.STATIC_ACCESS_PATH = customValue.getStaticAccessPath();
        CustomStaticValue.INDIVIDUAL_MODEL = customValue.isIndividualModel();
    }

}
