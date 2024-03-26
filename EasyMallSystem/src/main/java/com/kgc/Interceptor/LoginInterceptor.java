//package com.kgc.Interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.kgc.entity.User;
//import com.kgc.utils.RedisUtil;
//import org.apache.log4j.Logger;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author: 欧洋宏
// * @create: 2024-03-18 20:16
// **/
//public class LoginInterceptor implements HandlerInterceptor {
//    private final Logger logger = Logger.getLogger(getClass());
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String urlTime = request.getHeader("urlTime"); // 时间戳
//        String random = request.getHeader("random"); // 随机数
//        String value = .getValueByKey("user");
//        User user = JSON.parseObject(value, User.class);
//        if (user == null || user.getUserName().equals("")) {
//            return false;
//        }
//        if (urlTime == null || random == null || urlTime.isEmpty() || random.isEmpty()) {
//            return false;
//        }
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//}
