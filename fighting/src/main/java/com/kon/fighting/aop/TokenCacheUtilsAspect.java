package com.kon.fighting.aop;

import com.kon.fighting.common.constant.Constant;
import com.kon.fighting.utils.AopUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Description 对TokenCacheUtils的所有方法做增强
 * @Author LK
 * @Date 2019/10/22 15:39
 * @Version V1.0
 **/
@Aspect
@Component
public class TokenCacheUtilsAspect {

    @Pointcut("execution(public * com.kon.fighting.utils.TokenCacheUtils.*(..))")
    public void point() {
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 方法名
        String methodName = methodSignature.getName();
        // 参数名数组
        String[] parameters = methodSignature.getParameterNames();
        // 参数值
        Object[] args = joinPoint.getArgs();

        // token参数所对应的index值
        int token = AopUtils.getParameterIndex(parameters, "token");
        if (token == -1) {
            return joinPoint.proceed();
        }
        args[token] = Constant.BEARER_KEY + args[token];
        return joinPoint.proceed(args);
    }


}
