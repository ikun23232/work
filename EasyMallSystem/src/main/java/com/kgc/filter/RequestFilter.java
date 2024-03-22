package com.kgc.filter;

import com.kgc.utils.DateUtil;
import com.kgc.utils.ReplayUtil;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
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

    private String[] urlStr = {"index.html", "/Login.html", "/js", "/images", "/css", "/commonJS", "/getUUID"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化 SignAuthFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String timestamp = request.getHeader("timestamp");
        String random = request.getHeader("signature");
        String requestUri = request.getRequestURI();
        for (int i = 0; i < urlStr.length; i++) {
            if (requestUri.contains(urlStr[i])) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }
        if (StringUtils.isEmpty(timestamp)) {
            logger.info("无时间戳");
            return;
        }
        if (StringUtils.isEmpty(random)) {
            logger.info("无签名");
            return;
        }
        //验签
        String randomTemp = replayUtil.checkRandom(random);
        if (randomTemp == null) {
            logger.info("验签不通过");
            return;
        }
        boolean falg = DateUtil.checkReplaytimestamp(timestamp);
        if (!falg){
            logger.info("时间戳不通过");
            return;
        }
        //验签通过删uuid
        replayUtil.removeRandom(random);
        filterChain.doFilter(request, servletResponse);

    }

    @Override
    public void destroy() {
        logger.info("SignAuthFilter.....销毁了");
    }
}
