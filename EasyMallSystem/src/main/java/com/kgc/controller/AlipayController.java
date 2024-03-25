package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.AlipayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/19/ 11:51
 * @Description
 */
@Controller()
public class AlipayController {

    private Logger logger = Logger.getLogger(AlipayController.class);

    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/getOrder")
    public void createOrder(HttpServletResponse response,int amount,String orderName,@RequestParam(value = "serialNumber",required = false) String serialNumber){
        logger.info("AliPayController createOrder is start=========param:response:"+response);
        response.setContentType("text/html;charset=UTF-8");
        Message message = alipayService.createOrder(amount,orderName,serialNumber);
        try {
            PrintWriter pw = response.getWriter();
            pw.print(message.getData());
        } catch (IOException e) {
            logger.info("AliPayController createOrder is error=========reason:"+e);
            e.printStackTrace();
        }
    }

    @RequestMapping("/alipayNotify")
    public String alipayNotify(HttpServletRequest req, HttpServletResponse resp){
        Map<String,String[]> values = req.getParameterMap();
        logger.info("alipayNotify values:"+values);
        resp.setContentType("text/html;charset=utf-8");
        Map params = new HashMap();
        for(Map.Entry entry : values.entrySet()){
            String[] value = (String[]) entry.getValue();
            String key = (String) entry.getKey();
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < value.length;i++){
                if(i==value.length-1){
                    sb.append(value[i]);
                }else {
                    sb.append(value[i]+",");
                }
            }
            params.put(key,sb.toString());
            params.remove("sign_type");
        }
        Message message = alipayService.backOrder(params);
        if("200".equals(message.getCode())){
            return "success";
        }else {
            return "error";
        }
    }
}
