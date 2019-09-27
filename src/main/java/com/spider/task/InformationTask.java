package com.spider.task;

import com.spider.common.Information;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-07-25 14:01
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class InformationTask {
    @Autowired
    SQLManager sqlManager;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() throws Exception {
        List<Information> list = sqlManager.query(Information.class).andLike("html_content","%function aa()%").select();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(new StringBuffer("当前数据：").append(i));
            String shortStr = list.get(i).getHtmlContent().replace("<script type=\"text/javascript\">window.onload = aa();\n" +
                    "function aa(){\n" +
                    "var imgstyle=document.getElementsByTagName(\"img\");\n" +
                    "for(i=0;i<imgstyle.length;i++)\n" +
                    "{\n" +
                    "imgstyle[i].style.width='333px';\n" +
                    "}\n" +
                    "}\n" +
                    " </script>","");
            list.get(i).setHtmlContent(shortStr);
            sqlManager.updateTemplateById(list.get(i));
        }
    }
}
