package com.spider.common;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class AliyunUtil {
    public static String endpoint = "img-cn-beijing.aliyuncs.com";
    public static String media_endpoint = "oss-cn-hangzhou.aliyuncs.com";
    public static String accessKeyId = "IaXh2fPzhJbK5d3b";
    public static String accessKeySecret = "3Fj6E8ZuvfTHsUPm8DlST01CzIXNlK";
    public static String bucketName = "miaotu1";
    public static String media_bucketName = "miaoto-video-out";
    public static ClientConfiguration cfg = null;

    static {
        cfg = new ClientConfiguration();
        cfg.setSupportCname(true);
        // Set the maximum number of allowed open HTTP connections
        cfg.setMaxConnections(100);
        // Set the amount of time to wait (in milliseconds) when initially establishing
        // a connection before giving up and timing out
        cfg.setConnectionTimeout(5000);
        // Set the maximum number of retry attempts for failed retryable requests
        cfg.setMaxErrorRetry(3);
        // Set the amount of time to wait (in milliseconds) for data to betransfered over
        // an established connection before the connection times out and is closed
        cfg.setSocketTimeout(2000);
        cfg.setSupportCname(true);

    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来  
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);
        }
        //关闭输入流  
        inStream.close();
        //把outStream里的数据写入内存  
        return outStream.toByteArray();
    }

    /**
     * 图片上传阿里云
     *
     * @param is  图片流
     * @param tag 1:活动 2:头像图片 3:供应,求购,话题图片,4:资讯一张大图,5:资讯一张图,6:资讯三张小图,7:主题,8:资讯,9:ueditor上传的图片
     * @return
     */
    public static String uploadFile(InputStream is, int tag) throws IOException {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpeg");
        String key = "";
        long currentTimeMillis = System.currentTimeMillis();
        if (2 == tag) { //头像图片
            key = "header/header_" + currentTimeMillis + ".jpg";
        } else if (3 == tag) { //供应,求购,话题图片
            key = "content/content_" + currentTimeMillis + ".jpg";
        } else if (9 == tag) {
            key = "ueditor/" + currentTimeMillis + new Random().nextInt() + ".jpg";
        } else if (10 == tag) {
            key = "company/logo/logo_" + currentTimeMillis + ".jpg";
        } else if (11 == tag) {
            key = "company/company_" + currentTimeMillis + ".jpg";
        } else {
            key = "activities/" + currentTimeMillis + ".jpg";
        }
        ByteArrayOutputStream outImgStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(is).scale(1f)
                    .outputQuality(0.5f)
                    .toOutputStream(outImgStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = new ByteArrayInputStream(outImgStream.toByteArray());

        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, cfg);
        client.putObject(new PutObjectRequest(bucketName, key, inputStream, meta));
        inputStream.close();
        return key;
    }





}
