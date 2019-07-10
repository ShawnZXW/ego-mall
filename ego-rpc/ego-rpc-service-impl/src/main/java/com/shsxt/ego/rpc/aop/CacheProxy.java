package com.shsxt.ego.rpc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义注解 @CacheProxy
 *
 * @author Shawn
 * @date 2019-07-05 22:33
 */
@Aspect
@Component
public class CacheProxy {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public  Object around(ProceedingJoinPoint pjp) throws Throwable {

        // 获取key
        String key = "";
        Object result = null;
         if (redisTemplate.hasKey(key)) {
             // 获取缓存

         }else {
             result = pjp.proceed();
             if (null != result) {
                 redisTemplate.opsForValue().set(key, result);
             }
         }
        return result;
    }
}
