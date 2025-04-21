package org.example.blog.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.blog.common.utils.JsonUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    /** 以自定义 @ApiOperationLog 注解为切点，凡是添加 @ApiOperationLog 的方法，都会执行环绕中的代码 */
    @Pointcut("@annotation(org.example.blog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {}

    /**
     * 环绕
     * @param joinPoint  切入点
     * @return object
     * @throws Throwable 异常
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 请求开始时间
            long startTime = System.currentTimeMillis();

            // MDC
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取被请求的类和方法
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();


            // 请求入参
            Object[] args = joinPoint.getArgs();

            // 入参转 JSON 字符串
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));


            // 打印请求相关参数
            log.info("{}; {}; 入参: {}",
                    className, methodName, argsJsonStr);

            // 执行切点方法
            Object result = joinPoint.proceed();

            // 执行耗时
            long executionTime = System.currentTimeMillis() - startTime;

            // 打印出参等相关信息
            log.info("{}; {}; 耗时: {}ms, 出参: {}",
                    className, methodName, executionTime, JsonUtil.toJsonString(result));

            return result;
        } finally {
            MDC.clear();
        }
    }



    /**
     * 转 JSON 字符串
     * @return json
     */
    private Function<Object, String> toJsonStr() {
        return JsonUtil::toJsonString;
    }

}
