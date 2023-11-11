package com.example.login_authetic.listner;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器，监听MyEvent事件
 * @author shengwu ni
 * @date 2018/07/05
 */
@Component
public class UserEventListener implements ApplicationListener<UserPublishEvent> {
    @Override
    public void onApplicationEvent(UserPublishEvent myEvent) {
        // 把事件中的信息获取到

        // 处理事件，实际项目中可以通知别的微服务或者处理其他逻辑等等

    }
}
