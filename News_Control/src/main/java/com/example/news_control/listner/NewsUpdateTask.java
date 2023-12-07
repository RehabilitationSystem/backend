package com.example.news_control.listner;

import com.example.news_control.entity.News;
import com.example.news_control.service.NewsService;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:TODO
 * @DateTime:7/12/2023 上午11:35
 * @param:
 **/
@Configuration
@EnableScheduling
public class NewsUpdateTask {
    @Resource
    NewsService newsService;
    @Resource
    ApplicationContext applicationContext;
    @Scheduled(cron = "0/30 * * * * ? ")
    private void updateNews() throws NoSuchFieldException {
        System.out.println("测试："+ LocalDateTime.now());
        List<Integer> newsIdList = newsService.getNewsId();
//        // 获取上下文对象
//        ServletContext application = applicationContext.getBean(ServletContext.class);
//        application.setAttribute("newsList", newsIdList);
//        System.out.println(newsIdList);
//
//        // 获取新闻列表
            List<News> news = newsService.getRecentNews();
        for (News n:news
             ) {
            newsService.addNews(n);
        }

    }
}
