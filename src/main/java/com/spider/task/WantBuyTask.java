package com.spider.task;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spider.common.CompanyInfo;
import com.spider.common.ibeetl.BeetlSQLConfig;
import com.spider.common.miaocang.WantBuyList;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-09-10 12:23
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class WantBuyTask {
    @Autowired
    BeetlSQLConfig beetlSQLConfig;

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() {
        SQLManager sqlManager = beetlSQLConfig.getSqlManager();
        RestTemplate restTemplate = new RestTemplate();
//        for (int i = 0; i < 8; i++) {
//        }
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity(new StringBuilder("http://api.miaocang.cc:12100/api/get_purchase_seedling_page.htm?page=").append(1).toString(), String.class);
        String jsonStr = responseEntity.getBody();
        WantBuyList wantBuyList = JSONObject.parseObject(jsonStr, WantBuyList.class);
        System.out.println(wantBuyList);
    }
}
