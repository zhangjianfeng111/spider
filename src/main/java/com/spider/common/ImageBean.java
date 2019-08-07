package com.spider.common;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应数据库中存储多张图片的bean
 * @author qingyin
 * @date 2016/9/12
 */
public class ImageBean {

    private String t_height;

    private String t_width;

    private String t_url;

    public String getT_height() {
        return t_height;
    }

    public void setT_height(String t_height) {
        this.t_height = t_height;
    }

    public String getT_width() {
        return t_width;
    }

    public void setT_width(String t_width) {
        this.t_width = t_width;
    }

    public String getT_url() {
        return t_url;
    }

    public void setT_url(String t_url) {
        this.t_url = t_url;
    }

    /**
     * wh 存储格式为width&height
     * @param urls
     * @param wh
     */
    public static String convertImageBean(String[] urls,String[] wh){
        String imagesJson="";
        List<ImageBean> imageFileBeen=new ArrayList<>();
        if(urls!=null) {
            for (int i = 0; i < urls.length; i = i + 1) {
                ImageBean bean = new ImageBean();
                bean.setT_url(urls[i]);
                if(wh!=null) {
                    String[] wds = wh[i].split("&");
                    bean.setT_width(wds[0]);
                    bean.setT_height(wds[1]);
                }else{
                    bean.setT_width("0");
                    bean.setT_height("0");
                }
                imageFileBeen.add(bean);
            }
            imagesJson=JSONArray.fromObject(imageFileBeen).toString();
        }
        return imagesJson;
    }
}
