package com.example.news_control.listner;

import com.example.login_authetic.service.UserService;
import com.example.news_control.entity.News;
import com.example.news_control.service.NewsService;
import jakarta.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义监听器，监听MyEvent事件
 * @author Liu
 * @date 2023/12/07
 */
@Component
public class MyServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 先获取到application上下文
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        // 获取对应的service
        NewsService newsService = applicationContext.getBean(NewsService.class);
        List<Integer> newsIdList = newsService.getNewsId();
        // 获取application域对象，将查到的信息放到application域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("newsList", newsIdList); // 初始化，
        System.out.println("上下文监听器运行");
    }
}

