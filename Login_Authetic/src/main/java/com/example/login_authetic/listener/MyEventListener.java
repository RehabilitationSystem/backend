package com.example.login_authetic.listener;

import com.example.login_authetic.entity.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器，监听MyEvent事件
 * @author shengwu ni
 * @date 2018/07/05
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        // 把事件中的信息获取到
        User user = myEvent.getUser();
        // 处理事件，实际项目中可以通知别的微服务或者处理其他逻辑等等
        System.out.println("用户名：" + user.getUsername());
        System.out.println("密码：" + user.getPassword());

    }
}
