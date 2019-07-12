package com.shsxt.ego.cart.interceptors;

import com.shsxt.ego.commom.utils.CookieUtils;
import com.shsxt.ego.commom.utils.JsonUtils;
import com.shsxt.ego.rpc.pojo.TbUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录过滤器
 *
 * -  preHandle 在请求方法拦截前执行
 * -  返回 true 代表对当前请求进行放行处理
 *
 * @author Shawn
 * @date 2019-07-08 23:02
 */
//@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * preHandle，在方法被拦截前执行
     *
     * - 1、获取 cookie token
     * - 2、查询缓存中对应的用户信息
     * -    存在，则放行
     * - 3、反之不存在，
     * -    重定向到登录页面 http:localhost:8083/login
     *
     * @param request
     * @param response
     * @param handler
     * @return 返回true表示对当前方法放行，反之，为拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = CookieUtils.getCookieValue(request, "sso_token");
        // 拿到 String 类型的请求地址
        String uri = request.getRequestURI().toString();
        // 打印信息查看 uri
        System.out.println("在com.shsxt.ego.cart.Interceptors打印:"+uri);
        // token 非空校验
        if (!StringUtils.isEmpty(token)) {//  token 非空
            // 从 redis 中用 token 把 user 对象取出来，注意格式转换
            TbUser user = JsonUtils.jsonToPojo((String) redisTemplate.opsForValue().get(token), TbUser.class);
            //
            if (null != user) {//　user非空，表示用户已登录
                return true;// 放行
            }else { // user 为空，表示为登录
                // 重定向到登录页面
                response.sendRedirect("http:127.0.0.1:8083/login?redirect=" + uri);
                return false;
            }
        }

        // 执行重定向
        response.sendRedirect("http://127.0.0.1:8083/login?redirect=" + uri);
        return false;
    }
}
