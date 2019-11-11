package com.kon.fighting.utils;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.common.constant.CustomStaticValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description token缓存操作工具类
 * @Author LK
 * @Date 2019/10/21 14:24
 * @Version V1.0
 **/
@Component
public class TokenCacheUtils {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 判断这个token是否在缓存中
     *
     * @return
     */
    public Boolean hasToken(String token) {
        return redisUtils.hasKey(token);
    }

    /**
     * 从缓存中删除这个token
     *
     * @param token
     */
    public void deleteToken(String token) {
        redisUtils.del(token);
    }

    /**
     * 判断此token是否在黑名单中
     *
     * @param token
     * @return
     */
    public Boolean isBlackList(String token) {
        return redisUtils.hHasKey(Constant.BLACK_LIST, token);
    }

    /**
     * 添加新旧token到以旧换新hash表中
     *
     * @param oldToken 旧token
     * @param newToken 新token
     * @return
     */
    public Boolean setOldChangeNew(String oldToken, String newToken) {
        return redisUtils.hset(Constant.OLD_CHANGE_NEW, oldToken, newToken, CustomStaticValue.OLD_CHANGE_NEW_SECOND);
    }

    /**
     * 根据旧的token
     * 去以旧换新的hash表中查看是否存在新的token
     *
     * @param oldToken
     * @return
     */
    public String getNewTokenByOld(String oldToken) {
        Object newToken = redisUtils.hget(Constant.OLD_CHANGE_NEW, oldToken);
        return newToken == null ? null : newToken.toString();
    }

    /**
     * 根据旧的token(带有失败重试)
     * 去以旧换新的hash表中查看是否存在新的token
     *
     * @param oldToken
     * @return
     */
    public String getNewTokenByOldRetry(String oldToken) {
        String newToken = (String) redisUtils.hget(Constant.OLD_CHANGE_NEW, oldToken);
        if (newToken == null) {
            int retry = 1;
            do {
                retry++;
                // 休眠等待
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newToken = (String) redisUtils.hget(Constant.OLD_CHANGE_NEW, oldToken);
            } while (newToken == null && retry < 3);
        }
        return newToken;
    }

    /**
     * 将token加入到redis黑名单中
     *
     * @param token
     */
    public Boolean addBlackList(String token) {
        return redisUtils.hset(Constant.BLACK_LIST, token, DateUtils.getTime());
    }

    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getTokenValidTimeByToken(String token) {
        return redisUtils.hget(token, Constant.TOKEN_VALID_TIME);
    }

    /**
     * 查询token下的用户名
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getUsernameByToken(String token) {
        return redisUtils.hget(token, Constant.USERNAME);
    }

    /**
     * 查询token下的Ip
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getIpByToken(String token) {
        return redisUtils.hget(token, Constant.IP);
    }

    /**
     * 查询token下的过期时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getExpirationTimeByToken(String token) {
        return redisUtils.hget(token, Constant.EXPIRATION_TIME);
    }

    /**
     * 设置token信息
     *
     * @param token    token
     * @param username 用户名
     * @param ip       ip
     */
    public void setTokenRefresh(String token, String username, String ip) {
        //刷新时间
        Integer expire = CustomStaticValue.VALID_TIME * 24 * 60 * 60 * 1000;

        redisUtils.hset(token, Constant.TOKEN_VALID_TIME, DateUtils.getAddDayTime(CustomStaticValue.VALID_TIME), expire);
        redisUtils.hset(token, Constant.EXPIRATION_TIME, DateUtils.getAddDaySecond(CustomStaticValue.EXPIRATION_SECOND), expire);
        redisUtils.hset(token, Constant.USERNAME, username, expire);
        redisUtils.hset(token, Constant.IP, ip, expire);
    }

}
