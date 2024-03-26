package com.kgc.filter;

import com.alibaba.fastjson.JSON;
import com.kgc.constant.UserConstant;
import com.kgc.entity.User;
import com.kgc.utils.DateUtil;
import com.kgc.utils.RedisUtil;
import com.kgc.utils.ReplayUtil;
import com.kgc.utils.UserSessionUtil;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 22:59
 * 解决重放攻击
 **/
//@Component
@WebFilter(urlPatterns = {"/*"})
public class RequestFilter implements Filter {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ReplayUtil replayUtil;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 不会被拦截的UUId或请求重复攻击 第一层
     */
//    "/doImg","/alipayNotify","/getBrandList","/getCategoryList","/getCategorySecond","/getCategoryThrid"
//            ,"/getCategoryThrid"
    private String[] urlStr = {"/Regist.html","/index.html", "/Login.html", "/js", "/images", "/css", "/commonJS", "/getUUID","/addUser",
    "/checkUserByName","/checkUserByLoginName","/sendEmailCode","/loginto","/loginOut"
    ,"/updatePassword","/getUser","/checkUserByUpdateName","/checkUserByUpdateMobile","/checkEmail",
    "/checkMobile","/checkUserByEmail","/checkUserPhone","/doImg","/getNewsList","/getCategoryList",
    "/getCategorySecond","/getCategoryThrid","/getFristIdByThrid","/getSecondIdByThrid"};

//第二层特殊的要处理的
    private String[] specialStr={};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化 SignAuthFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest  request=(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //放行静态公共资源
        String requestUri = request.getRequestURI();
        for (int i = 0; i < urlStr.length; i++) {
            if (requestUri.contains(urlStr[i])) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }

        //验签
        if (!requestUri.contains("html")){
            boolean checkSign=CheckSign(request);
            if (!checkSign){
                logger.warn("非法请求参数");
                return;
            }
        }

        //公共静态资源页面放行
        boolean userLoggedIn = isUserLoggedIn();
        if (!userLoggedIn){
            //登录失败 重定向到login.html
            response.sendRedirect("/login.html");
        }



        filterChain.doFilter(request, servletResponse);

    }


    /**
     * 验签是否通过
     * @param request
     * @return
     */
    private boolean CheckSign(HttpServletRequest request) {
        String timestamp = request.getHeader("timestamp");
        String random = request.getHeader("signature");
        if (StringUtils.isEmpty(timestamp)) {
            logger.info("无时间戳");
            return false;
        }
        if (StringUtils.isEmpty(random)) {
            logger.info("无签名");
            return  false;
        }
        //验签
        String randomTemp = replayUtil.checkRandom(random);
        if (randomTemp == null) {
            logger.info("验签不通过");
            return false;
        }
        boolean falg = DateUtil.checkReplaytimestamp(timestamp);
        if (!falg){
            logger.info("时间戳不通过");
            return false;
        }
        //验签通过删uuid
        replayUtil.removeRandom(random);
     return true;
    }

    /**
     * 判断用户登录
     *
     * @return
     */
    private boolean isUserLoggedIn() {
        String valueByKey = redisUtil.getValueByKey(UserConstant.USER_SESSION);
        User user = JSON.parseObject(valueByKey, User.class);
        if (user!=null&&user.getUserName()!=null){
            return true;
        }
        return false;
    }


    @Override
    public void destroy() {
        logger.info("SignAuthFilter.....销毁了");
    }
}
