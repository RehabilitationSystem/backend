package com.example.commons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "url")

//# 配置多个微服务的配置文件格式（基于yml）
//url:
//  # 订单微服务的地址
//  orderUrl: http://localhost:8002
//  # 用户微服务的地址
//  userUrl: http://localhost:8003
//  # 购物车微服务的地址
//  shoppingUrl: http://localhost:8004
public class MicroServiceUrl {

    private String orderUrl;
    private String userUrl;
    private String shoppingUrl;
    // 省去get和set方法


    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getShoppingUrl() {
        return shoppingUrl;
    }

    public void setShoppingUrl(String shoppingUrl) {
        this.shoppingUrl = shoppingUrl;
    }
}
