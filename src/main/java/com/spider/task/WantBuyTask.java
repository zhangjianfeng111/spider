package com.spider.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spider.common.AliyunUtil;
import com.spider.common.miaocang.*;
import com.spider.common.pojo.UserInfo;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
    SQLManager sqlManager;

//    @Scheduled(cron = "0 0 0/2 * * ?")
    public void scheduled() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 1; i < 3; i++) {
            ResponseEntity<String> responseEntity = restTemplate
                    .getForEntity(new StringBuilder("http://api.miaocang.cc:12100/api/get_purchase_seedling_page.htm?page=").append(i).toString(), String.class);
            String jsonStr = responseEntity.getBody();
            WantBuyList wantBuyList = JSONObject.parseObject(jsonStr, WantBuyList.class);
            List<DataBean> dataBeanList = wantBuyList.getData().getList();
            String createTimeStr;
            String overTimeStr;
            for (DataBean dataBean : dataBeanList) {
                createTimeStr = DateUtil.date(dataBean.getItem().getGmtCreate()).toString();
                overTimeStr = DateUtil.date(dataBean.getItem().getAutoSoldoutTime()).toString();
                WantBuyNew wantBuyNew = new WantBuyNew();
                List<String> urlList = new ArrayList<>();
                urlList.add("/header/o_1d4jpnpgp9a71c1a6tr1p8f15ad10.png");
                urlList.add("/header/defaulthead125%402x.png");
                urlList.add("/header/o_1d0c13jd11ne8mplutc3b71upfc.png");
                UserInfo userInfo = sqlManager.query(UserInfo.class)
                        .andNotIn("heed_image_url",urlList)
                        .orderBy("rand()").single();
                SeedingName seedingName = sqlManager.query(SeedingName.class).andEq("base_name", dataBean.getBase_name().trim()).single();

                if (seedingName != null) {
                    wantBuyNew.setSeedingNameId(seedingName.getId());//苗木名称id
                    WantBuyNew wantBuyNew2 = sqlManager.query(WantBuyNew.class).andEq("seeding_name_id", seedingName.getId())
                            .andEq("create_time", createTimeStr).single();
                    if (wantBuyNew2 != null)//去重
                        break;
                }else{
                    break;
                }
                wantBuyNew.setUserId(userInfo.getUserId());//用户id
                wantBuyNew.setNum(dataBean.getInventory());//数量

                if (StrUtil.isNotBlank(dataBean.getSample_pic())) {
                    List<String> samplePics = JSON.parseArray(dataBean.getSample_pic(),String.class);
                    List<PicParams> picParamsList = new ArrayList<>();
                    for (String samplePic : samplePics) {
                        // 构造URL
                        URL urlModel = new URL(samplePic);
                        // 打开连接
                        URLConnection con = urlModel.openConnection();
                        //设置请求超时为5s
                        con.setConnectTimeout(5 * 1000);
                        // 输入流
                        InputStream inputStream = con.getInputStream();
                        // 输出的文件流
//            File file=new File("/alidata/www/miaoto.net/images/"+System.currentTimeMillis()+".jpg");
                        File file = new File("E:\\" + System.currentTimeMillis() + ".jpg");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        OutputStream outputStream = new FileOutputStream(file);
                        int len;
                        byte[] buf = new byte[1024];
                        while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                            outputStream.write(buf, 0, len);
                        }
                        outputStream.flush();
                        inputStream = new FileInputStream(file);
                        String key = AliyunUtil.uploadFile(inputStream, 3);//上传图片
                        outputStream.close();
                        inputStream.close();
                        if (file.exists()) {
                            file.delete();
                        }
                        PicParams picParams = new PicParams();
                        picParams.setT_url(key);
                        picParams.setT_height(new String("").intern());
                        picParams.setT_width(new String("").intern());
                        picParamsList.add(picParams);
                    }
                    wantBuyNew.setCover(JSONObject.toJSONString(picParamsList));//图片
                }

                List<Details> detailsList = dataBean.getDetails();
                for (Details details : detailsList) {
                    switch (details.getName()) {
                        case "胸径":
                            wantBuyNew.setDiameterStart(Integer.parseInt(details.getValue()));
                            wantBuyNew.setDiameterEnd(Integer.parseInt(details.getValue_end()));
                            break;
                        case "地径":
                            wantBuyNew.setGroundDiameterStart(Integer.parseInt(details.getValue()));
                            wantBuyNew.setGroundDiameterEnd(Integer.parseInt(details.getValue_end()));
                            break;
                        case "高度":
                            wantBuyNew.setHeightStart(Integer.parseInt(details.getValue()));
                            wantBuyNew.setHeightEnd(Integer.parseInt(details.getValue_end()));
                            break;
                        case "冠幅":
                            wantBuyNew.setCrownStart(Integer.parseInt(details.getValue()));
                            wantBuyNew.setCrownEnd(Integer.parseInt(details.getValue_end()));
                            break;
                        case "分枝数":
                            wantBuyNew.setBranchNumStart(Integer.parseInt(details.getValue()));
                            wantBuyNew.setBranchNumEnd(Integer.parseInt(details.getValue_end()));
                            break;
                        case "分支点":
                            wantBuyNew.setBranchPointStart(Integer.parseInt(details.getValue()));
                            wantBuyNew.setBranchPointEnd(Integer.parseInt(details.getValue_end()));
                            break;
                    }
                }

                wantBuyNew.setQualityRequirement(dataBean.getQuality());//品质要求
                wantBuyNew.setPlantType(dataBean.getPlanting_type());//种植类型
                Item item = dataBean.getItem();
                if (StrUtil.isNotBlank(item.getRemark())) {
                    wantBuyNew.setWantBuyDescribe(item.getRemark());//求购描述
                }
                if (item.getProvinceName().equals(new String("不限").intern())) {//省市
                    wantBuyNew.setProvince(new String("全国").intern());
                    wantBuyNew.setCity(new String("不限").intern());
                } else {
                    wantBuyNew.setProvince(new StringBuilder(item.getProvinceName()).append("省").toString());
                    wantBuyNew.setCity(new StringBuilder(item.getCityName()).append("市").toString());
                }

                switch (dataBean.getPurchase_day()) {
                    case 1:
                        wantBuyNew.setWantBuyTerm(1);
                        break;
                    case 7:
                        wantBuyNew.setWantBuyTerm(2);
                        break;
                    case 30:
                        wantBuyNew.setWantBuyTerm(3);
                        break;
                }
                wantBuyNew.setMaturityTime(overTimeStr);//过期时间
                wantBuyNew.setCreateTime(createTimeStr);//过期时间
                wantBuyNew.setModelType(new String("spider").intern());

                sqlManager.insertTemplate(wantBuyNew);

            }
        }
    }
}
