//package com.kgc.filter;
//
//import org.apache.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author: 欧洋宏
// * @create: 2024-03-18 22:59
// **/
//public class RequestFilter implements Filter {
//    private Logger logger = Logger.getLogger(getClass());
//    @Autowired
//    private SignAuthProperties signAuthProperties;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("初始化 SignAuthFilter...");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        // 过滤不需要签名验证的地址
//        String requestUri = request.getRequestURI();
//        for (String ignoreUri : signAuthProperties.getIgnoreUri()) {
//            if (requestUri.contains(ignoreUri)) {
//                log.info("当前URI地址：" + requestUri + "，不需要签名校验！");
//                chain.doFilter(request, response);
//                return;
//            }
//        }
//
//        // 获取签名和时间戳
//        String sign = httpRequest.getHeader("Sign");
//        String timestampStr = httpRequest.getHeader("Timestamp");
//        if (StringUtils.isEmpty(sign)) {
//            responseFail("签名不能为空", response);
//            return;
//        }
//        if (StringUtils.isEmpty(timestampStr)) {
//            responseFail("时间戳不能为空", response);
//            return;
//        }
//
//        // 重放时间限制
//        long timestamp = Long.parseLong(timestampStr);
//        if (System.currentTimeMillis() - timestamp >= signAuthProperties.getTimeout()*1000) {
//            responseFail("签名已过期", response);
//            return;
//        }
//
//        // 校验签名
//        Map<String, String[]> parameterMap = httpRequest.getParameterMap();
//        if (SignUtils.verifySign(parameterMap, sign, timestamp)) {
//            chain.doFilter(httpRequest, response);
//        } else {
//            responseFail("签名校验失败", response);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        logger.info("SignAuthFilter.....销毁了");
//    }
//}
