package com.spider.task;

import com.spider.common.AliyunUtil;
import com.spider.common.pojo.StudySubjectPpt;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-09-05 14:25
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StudyTask {
    @Autowired
    private SQLManager sqlManager;

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() throws IOException {
        List<StudySubjectPpt> list = sqlManager.query(StudySubjectPpt.class).andGreat("id",554).select();
        for (StudySubjectPpt studySubjectPpt : list) {

            String srcUrl = new StringBuilder("http://imgs.miaoto.net/").append(studySubjectPpt.getPic()).toString();
            // 读取原图片信息
            URL urlModel = new URL(srcUrl);
//            File srcImgFile = new File(srcImgPath);//得到文件
            BufferedImage srcImg = ImageIO.read(urlModel);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
//            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            //设置水印的坐标
            int y;
            int x;
            URL urlSmall = new URL("http://imgs.miaoto.net/content/content_1567731147839.png");//logo
            BufferedImage small = ImageIO.read(urlSmall);
            Graphics2D g6 = srcImg.createGraphics();
            int fontSize = (int) Math.sqrt(srcImgWidth);
            x = (int) ((srcImgWidth - fontSize) * 0.4);
            y = (int) (srcImgHeight * 0.4);
            g6.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);

            ByteArrayOutputStream outImgStream = new ByteArrayOutputStream();
            ImageIO.write(srcImg, "jpg", outImgStream);
            InputStream inputStream = new ByteArrayInputStream(outImgStream.toByteArray());
            String key = AliyunUtil.uploadFile(inputStream, 3);
            studySubjectPpt.setCoverPic(key);
            sqlManager.updateTemplateById(studySubjectPpt);
            System.out.println(new StringBuffer("======================").append(studySubjectPpt.getId()).toString());
            outImgStream.flush();
            outImgStream.close();
        }


    }
}
