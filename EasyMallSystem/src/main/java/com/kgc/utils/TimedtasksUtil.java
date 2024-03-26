package com.kgc.utils;

import com.kgc.entity.Order;
import com.kgc.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * @author: 欧洋宏
 * @create: 2024-03-23 15:55
 * 定时类
 **/
@Component
public class TimedtasksUtil {
    private ScheduledExecutorService executor;

    @Autowired
    private OrderService orderService;
    public TimedtasksUtil() {
        this.executor = Executors.newScheduledThreadPool(1);
    }
    public void startOrderTimeoutTask(String orderId, long createTime,int userId) {
        long delay = 10 * 1 * 1000; // 30分钟的延迟时间
        executor.schedule(() -> checkAndDeleteOrder(orderId, createTime,userId), delay, TimeUnit.MILLISECONDS);
    }
    private void checkAndDeleteOrder(String orderId, long createTime, int userId) {
        // 在这里编写检查订单并删除的逻辑\
        //直接删除我的订单
        System.out.println("我来了.................");
        int orderIdTemp = 0;
        if (!StringUtils.isEmpty(orderId)) {
            orderIdTemp = Integer.parseInt(orderId);
        }
        // 在子线程中执行订单检查和删除逻辑
        Order orderById = orderService.getOrderById(orderIdTemp);
        //订单状态为未支付直接删除回退库存数量
        if (orderById.getStatus()!=1){
            orderService.delOrderByIdAndUserId(Integer.parseInt(orderId),userId);
        }
        System.out.println("定时器来呐");
        //判断订单状态如果是1我就不执行

    }
    public void shutdown() {
        executor.shutdown();
    }
}
