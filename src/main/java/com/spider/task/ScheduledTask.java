package com.spider.task;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spider.common.Constants;
import com.spider.common.ImageBean;
import com.spider.common.Information;
import com.spider.common.SslUtil;
import org.beetl.sql.core.SQLManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

/**
 * @Auther: zhang
 * @Date: 2019-01-10 10:18
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduledTask {
    @Autowired
    SQLManager sqlManager;

//    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduled() throws Exception {
        //        String[] accounts = {"中苗会", "园林头条", "苗木通天下", "中国花卉报"};
        String[] accounts = {"中苗会"};
        for (String account : accounts) {
            String seedUrl = "https://weixin.sogou.com/weixin?type=1&"
                    + "s_from=input&ie=utf8&query=" + URLEncoder.encode(account, "utf-8");
            Document doc = getDoc2(seedUrl);
            Element accountLinkEle = doc.select("p.tit>a").first();
            //防止搜索结果为空
            if (accountLinkEle == null) {
                return;
            }
            //防止公众号名错误
            String detectedAccount = accountLinkEle.text().trim();
            if (!account.equals(detectedAccount)) {
                return;
            }
            //解析出公众号搜索结果页面中的URL
            String accountUrl = accountLinkEle.attr("abs:href");

            int b = (int) (Math.floor(100 * Math.random()) + 1);
            int a = accountUrl.indexOf("url=");
            int start = a + 30 + b;
            String urlStr = accountUrl.substring(start, start + 1);
            accountUrl += "&k=" + b + "&h=" + urlStr;
            doc = getDoc(accountUrl);

            RestTemplate rest = new RestTemplate();
            String prefix = "url += '";
            String suffix = "==';";
            String resultBody = doc.toString();
            int startIndex = resultBody.indexOf(prefix) + prefix.length();
            int endIndex = resultBody.indexOf(suffix);
            String nginxUrl = resultBody.substring(startIndex, endIndex + 2);
            nginxUrl = nginxUrl.trim().replace(" ", "").replace("';\r\nurl+='", "");
            System.out.println("nginxUrl============" + nginxUrl);

            ResponseEntity<String> result = rest.getForEntity(nginxUrl, String.class);
            prefix = "msgList = ";
            suffix = "seajs.use";
            startIndex = result.getBody().indexOf(prefix) + prefix.length();
            endIndex = result.getBody().indexOf(suffix);
            //trim()函数去除首尾空格
            String jsonStr = result.getBody().substring(startIndex, endIndex).trim();
            int len = jsonStr.length();
            //去掉最后一个分号，否则无法解析为jsonobject
            jsonStr = jsonStr.substring(0, len - 1);
            //将字符串转换为jsonobject
            JSONObject json = JSONObject.parseObject(jsonStr);
            JSONArray articleJSONArray = JSONArray.parseArray(json.getString("list"));

            for (int i = 0; i < articleJSONArray.size(); i++) {
                JSONObject msgInfoJSON = articleJSONArray.getJSONObject(i).getJSONObject("comm_msg_info");
                String datetime = msgInfoJSON.getString("datetime");
                String uploadTime = DateUtil.date(new Date(Long.parseLong(datetime) * 1000L)).toString();

                JSONObject articleJSON = articleJSONArray.getJSONObject(i).getJSONObject("app_msg_ext_info");
                String datitle = articleJSON.getString("title").trim();//文章标题，去重用
                String imagesUrl = articleJSON.getString("cover");
                String daUrl = articleJSON.getString(("content_url")).replace("&amp;", "&");

                long count = sqlManager.query(Information.class).andEq("info_title", datitle).count();
                if (count <= 0 && !datitle.equals("分享图片")) {
                    doSave(daUrl, datitle, uploadTime, imagesUrl, account);
                }
                JSONArray itemJSONArray = articleJSON.getJSONArray("multi_app_msg_item_list");
                for (int j = 0; j < itemJSONArray.size(); j++) {
                    JSONObject xJson = itemJSONArray.getJSONObject(j);
                    String xTitle = xJson.getString("title").trim();
                    String xImagesUrl = xJson.getString("cover");
                    String xUrl = xJson.getString(("content_url")).replace("&amp;", "&");
                    count = sqlManager.query(Information.class).andEq("info_title", xTitle).count();
                    if (count <= 0) {
                        doSave(xUrl, xTitle, uploadTime, xImagesUrl, account);
                    }
                }
            }
        }
    }

//    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduledByUrl() throws Exception {
        Document doc = null;
        String articleUrl = "https://mp.weixin.qq.com/s?src=11&timestamp=1563761679&ver=1743&signature=QchnVpPhoBfO9rB4qf6j487Li3Z*mr2hjqoBEfkgJ6Ff*TWUHW2AVRWDnlL0iDxGl2itAJYyHlP-oZkIa60EZsB3WX8HUYCE96hjMEUy6HO3a81ldJyPLZgf73Z6qWqO&new=1";
        String source= "中苗会";
        String title = "山西树多多园林绿化工程有限公司：以匠心专注培育精品苗";
        String uploadTime = DateUtil.now();
        String imageUrl = "https://mmbiz.qpic.cn/mmbiz_jpg/4riaWPomHXUVbVok1X71W72U6NGPMKINer7NBU1SMeeojLicibbteicfA1MFBkKiaZMd9I95t6gXheeGVO6mIt9Lb3g/640?wx_fmt=jpeg";
        try {
            doc = Jsoup.connect(articleUrl).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elementshl = doc.select("div.rich_media_content p");//找到所有p标签
        String content = elementshl.toString().replace("data-src", "src").replace("data-before-oversubscription-url", "src");
        if (content.indexOf("- END -") > 0)
            content = content.substring(0, content.indexOf("- END -")) + "- END -</span></strong></p> ";
        Information info = new Information();
        info.setProgramId(21);//1代表爬取栏目
        info.setImgType(0);//文章类型
        info.setInfoFrom(source);
        info.setIstop(0);
        info.setLabelId(0);
        info.setIsIndex(0);
        info.setInfoTitle(title.trim());
        info.setInfoSubTitle(title.trim());
        info.setInfoContent(title.trim());
        info.setInfomationDesc("");
        info.setUploadtime(uploadTime);
        String url11 = "/" + SslUtil.getImage(imageUrl);
        String[] urls = new String[1];
        urls[0] = url11;
        info.setInfoUrl(ImageBean.convertImageBean(urls,info.getInfoWH()));
        info.setViewTotal(0);
        info.setInfoType(1);//爬取文章类型
        info.setIsShow(0);

        //图片下载上传阿里云
        doc = Jsoup.parse(content);
        Elements pngs = doc.select("img");
        for (Element png : pngs) {
            String imgSrc = png.attr("src");
            String startStr = content.substring(0, content.indexOf(imgSrc));
            String endStr = content.substring(content.indexOf(imgSrc) + imgSrc.length(), content.length());
            content = startStr + Constants.IMAGE_OSS_URL + SslUtil.getImage(png.attr("src")) + endStr;
        }
        info.setHtmlContent(Jsoup.parse(content).toString().replace("width", "").replace("height", ""));
        sqlManager.insertTemplate(info, true);
        info.setInfomationUrl("http://www.miaoto.net/zmh/H5Page/info/main.html?id=" + info.getInfoId());
        sqlManager.updateTemplateById(info);
    }

    public static Document getDoc(String url) throws IOException {
        Connection conn = Jsoup.connect(url).timeout(5000);
        Long s = System.nanoTime() % 100;
        Long s1 = System.nanoTime() % 100;
        String UserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537." + s + " (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537." + s1;
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        conn.header("Accept-Encoding", "gzip,deflate");
        conn.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.header("User-Agent", UserAgent);
        conn.header("Connection", "keep-alive");
        conn.header("Cookie", "ABTEST=0|1556411153|v1; IPLOC=CN3301; SUID=CF579EB74018960A000000005CC4F311; SUV=009EC7C6B79E57CF5CC4F3127695E743; SUID=CF579EB72E08990A000000005CC4F312; sct=1; JSESSIONID=aaa4MXPKt80DUcAzS-zPw; PHPSESSID=595h71esc7skebr95pva0rvbp7; SNUID=6577BEB4C9CD41889CAD47CBC9DFFBF2");
        conn.header("Referer", "https://weixin.sogou.com" + url);
        Document doc = conn.get();
        return doc;
    }

    public static Document getDoc2(String url) throws IOException {
        Connection conn = Jsoup.connect(url).timeout(5000);
        Long s = System.nanoTime() % 100;
        Long s1 = System.nanoTime() % 100;
        String UserAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537." + s + " (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537." + s1;
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        conn.header("Accept-Encoding", "gzip,deflate");
        conn.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.header("User-Agent", UserAgent);
        conn.header("Connection", "keep-alive");
        conn.header("Cookie", "ABTEST=0|1556411153|v1; IPLOC=CN3301; SUID=CF579EB74018960A000000005CC4F311; SUV=009EC7C6B79E57CF5CC4F3127695E743; SUID=CF579EB72E08990A000000005CC4F312; sct=1; JSESSIONID=aaa4MXPKt80DUcAzS-zPw; PHPSESSID=595h71esc7skebr95pva0rvbp7; SNUID=6577BEB4C9CD41889CAD47CBC9DFFBF2");
        conn.header("Referer", url);
        conn.header("Host", "weixin.sogou.com");
        Document doc = conn.get();
        return doc;
    }

    public void doSave(String articleUrl, String title, String uploadTime, String imageUrl, String source) throws Exception {
        Document doc = null;
        articleUrl = "http://mp.weixin.qq.com" + articleUrl;
        try {
            doc = Jsoup.connect(articleUrl).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elementshl = doc.select("div.rich_media_content p");//找到所有p标签
        String content = elementshl.toString().replace("data-src", "src").replace("data-before-oversubscription-url", "src");
        if (content.indexOf("- END -") > 0)
            content = content.substring(0, content.indexOf("- END -")) + "- END -</span></strong></p> ";
        Information info = new Information();
        info.setProgramId(21);//1代表爬取栏目
        info.setImgType(0);//文章类型
        info.setInfoFrom(source);
        info.setIstop(0);
        info.setLabelId(0);
        info.setIsIndex(0);
        info.setInfoTitle(title.trim());
        info.setInfoSubTitle(title.trim());
        info.setInfoContent(title.trim());
        info.setInfomationDesc("");
        info.setUploadtime(uploadTime);
        String url11 = "/" + SslUtil.getImage(imageUrl);
        String[] urls = new String[1];
        urls[0] = url11;
        info.setInfoUrl(ImageBean.convertImageBean(urls,info.getInfoWH()));
        info.setViewTotal(0);
        info.setInfoType(1);//爬取文章类型
        info.setIsShow(0);

        //图片下载上传阿里云
        doc = Jsoup.parse(content);
        Elements pngs = doc.select("img");
        for (Element png : pngs) {
            String imgSrc = png.attr("src");
            String startStr = content.substring(0, content.indexOf(imgSrc));
            String endStr = content.substring(content.indexOf(imgSrc) + imgSrc.length(), content.length());
            content = startStr + Constants.IMAGE_OSS_URL + SslUtil.getImage(png.attr("src")) + endStr;
        }
        info.setHtmlContent(Jsoup.parse(content).toString().replace("width", "").replace("height", ""));
        sqlManager.insertTemplate(info, true);
        info.setInfomationUrl("http://www.miaoto.net/zmh/H5Page/info/main.html?id=" + info.getInfoId());
        sqlManager.updateTemplateById(info);
        Random random = new Random();
        Thread.sleep((random.nextInt(9) + 1) * 1000);
    }

}
