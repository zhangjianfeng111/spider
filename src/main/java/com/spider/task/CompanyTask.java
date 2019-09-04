package com.spider.task;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.spider.common.CompanyInfo;
import com.spider.common.Information;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-08-20 10:23
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class CompanyTask {
    @Autowired
    SQLManager sqlManager;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() {
        ExcelReader reader = ExcelUtil.getReader("f:/百强联盟.xls");
        List<List<Object>> readAll = reader.read();
        for (int i = 0; i < readAll.size(); i++) {
            List<Object> arg = readAll.get(i);
            CompanyInfo companyInfo = sqlManager.query(CompanyInfo.class).andEq("company_name", arg.get(0)).single();
            if (companyInfo == null){
                System.out.println("不存在的企业："+arg.get(0));
            }
        }
    }
}
