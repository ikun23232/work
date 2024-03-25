package com.kgc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.kgc.dao.OrderDao;
import com.kgc.dao.ProductDao;
import com.kgc.entity.Alipay;
import com.kgc.entity.Message;
import com.kgc.service.AlipayService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/19/ 10:36
 * @Description
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    private Logger logger = Logger.getLogger(AlipayServiceImpl.class);


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Alipay alipay;

    public Message createOrder(int amount, String orderName, String serialNumber) {
        logger.info("AliPayServiceImpl createOrder is start======");
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGateway(), alipay.getAppId(), alipay.getPrivateKey(), "json", "UTF-8", "alipay_public_key", "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
//异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl(alipay.getNotifyUrl());
//同步跳转地址，仅支持http/https
        request.setReturnUrl(alipay.getReturnUrl());
/******必传参数******/
        JSONObject bizContent = new JSONObject();
//商户订单号，商家自定义，保持唯一性
        UUID uuid = UUID.randomUUID();
        if (serialNumber == null) {
            bizContent.put("out_trade_no", uuid.toString());
        } else {
            bizContent.put("out_trade_no", serialNumber);
        }

//支付金额，最小值0.01元
        bizContent.put("total_amount", amount);
//订单标题，不可使用特殊符号
        bizContent.put("subject", orderName);
//电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

/******可选参数******/
//bizContent.put("time_expire", "2022-08-01 22:00:00");

//// 商品明细信息，按需传入
//JSONArray goodsDetail = new JSONArray();
//JSONObject goods1 = new JSONObject();
//goods1.put("goods_id", "goodsNo1");
//goods1.put("goods_name", "子商品1");
//goods1.put("quantity", 1);
//goods1.put("price", 0.01);
//goodsDetail.add(goods1);
//bizContent.put("goods_detail", goodsDetail);

//// 扩展信息，按需传入
//JSONObject extendParams = new JSONObject();
//extendParams.put("sys_service_provider_id", "2088511833207846");
//bizContent.put("extend_params", extendParams);

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request, "POST");
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
// 如果需要返回GET请求，请使用
// AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
        String pageRedirectionData = response.getBody();
        if (response.isSuccess()) {
            return Message.success(pageRedirectionData);
        } else {
            return Message.error("下单失败");
        }
    }

    @Override
    public Message backOrder(Map params) {
        logger.info("AliPayServiceImpl backOrder is start======params:" + params);
        if (params == null || params.isEmpty()) {
            logger.error("params is Empty!");
            return Message.error("订单支付失败！");
        }
        //校验签名
        try {
            boolean checkSign = AlipaySignature.rsaCheckV2(params, alipay.getAlipayPublicKey(), "UTF-8", "RSA2");
            logger.info("AliPayServiceImpl checkSign result======" + checkSign);
            if (checkSign) {
                String orderNo = (String) params.get("out_trade_no");
                String orderStatus = (String) params.get("trade_status");
                if (orderStatus.equals("TRADE_SUCCESS")) {
                    //更改我的订单状态
                    int i = orderDao.updateStatusById(orderNo);
                    if (i <= 0) {
                        return Message.error("更新失败");
                    }
                    return Message.success("success");
                }
            } else {
                return Message.success("sign is error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Message.error();
    }
}
