package com.kgc.utils;

import com.kgc.entity.Message;
import com.kgc.service.OrderService;
import com.kgc.service.impl.OrderServiceImpl;
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
    public void startOrderTimeoutTask(String orderId, long createTime) {
        long delay = 30 * 6000 * 1000; // 30分钟的延迟时间
        executor.schedule(() -> checkAndDeleteOrder(orderId, createTime), delay, TimeUnit.MILLISECONDS);
    }
    private void checkAndDeleteOrder(String orderId, long createTime) {
        // 在这里编写检查订单并删除的逻辑\
        //直接删除我的订单

            // 在子线程中执行订单检查和删除逻辑

            System.out.println("定时器来呐");
            orderService.delOrderById(Integer.parseInt(orderId));





    }
    public void shutdown() {
        executor.shutdown();
    }
}
