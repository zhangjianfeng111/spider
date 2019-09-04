package com.spider.task;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.wildfirechat.common.ErrorCode;
import cn.wildfirechat.pojos.InputOutputUserInfo;
import cn.wildfirechat.pojos.OutputCreateUser;
import cn.wildfirechat.sdk.UserAdmin;
import cn.wildfirechat.sdk.model.IMResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spider.common.CompanyInfo;
import com.spider.common.pojo.IMRegisterLog;
import com.spider.common.pojo.UserIM;
import com.spider.common.pojo.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhang
 * @Date: 2019-08-20 10:23
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class UserTask {
    @Autowired
    SQLManager sqlManager;

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() throws IOException {
//        List<UserInfo> list = sqlManager.select("user.selectUserList", UserInfo.class);
        List<UserInfo> list = sqlManager.query(UserInfo.class).andEq("user_id","73059").select();
        for (UserInfo userInfo : list) {
            if (userInfo.getUserName().startsWith("1") && userInfo.getUserName().length() == 11) {
                String mobile = userInfo.getUserName();
                UserIM userIM = new UserIM();
                userIM.setUserId(String.valueOf(userInfo.getUserId()));
                userIM.setName(mobile);
                if(StrUtil.isBlank(userInfo.getNickname()))
                    userIM.setDisplayName(mobile);
                else
                    userIM.setDisplayName(userInfo.getNickname());
                userIM.setMobile(mobile);
                userIM.setPassword(new String("123456").intern());
                userIM.setPortrait(url(userInfo.getHeedImageUrl()));
                String jsonStr = JSONObject.toJSONString(userIM);
                System.out.println("jsonStr===========" + jsonStr);

                HttpPost post = null;
                HttpClient httpClient = HttpClientBuilder.create().build();
                int nonce = (int) (Math.random() * 100000.0D + 3.0D);
                long timestamp = System.currentTimeMillis();
                String str = nonce + "|" + 123456 + "|" + timestamp;
                String sign = DigestUtils.sha1Hex(str);
                post = new HttpPost("http://101.201.31.194:18080/admin/user/create");
                post.setHeader("Content-type", "application/json; charset=utf-8");
                post.setHeader("Connection", "Keep-Alive");
                post.setHeader("nonce", nonce + "");
                post.setHeader("timestamp", "" + timestamp);
                post.setHeader("sign", sign);
                StringEntity entity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                post.setEntity(entity);
                HttpResponse response = httpClient.execute(post);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    IMRegisterLog log = new IMRegisterLog();
                    log.setUserId(userInfo.getUserId());
                    log.setStatusCode(statusCode);
                    sqlManager.insertTemplate(log);
                } else {
                    System.out.println(statusCode);
                }
            }
        }
    }

    public static String server = "http://imgs.miaoto.net/";

    public static String url(String url) {
        if (StrUtil.isNotBlank(url) && url.contains("http")) {
            url = url.replace("http://8yyq8.com/", "").replace("https://images.8yyq8.com/", "")
                    .replace("http://imgs.miaoto.net/", "");
        }
        if (StrUtil.isNotBlank(url) && !url.contains("http")) {
            url = (server + url).replace("t///", "t/").replace("t//", "t/").replace("t//", "t/");
        }
        return url;
    }
}
