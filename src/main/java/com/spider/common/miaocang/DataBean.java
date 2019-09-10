package com.spider.common.miaocang;

import lombok.Data;

import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-09-10 12:16
 * @Description:
 */
@Data
public class DataBean {

    /**
     * auth_status : false
     * base_name :  九里香
     * company_name : 启扬花木场
     * company_number : subiRRGC
     * create_time : 2019-09-10
     * details : []
     * has_guanzhu : false
     * inventory : 240
     * is_followed : false
     * is_necessary_pic : 0
     * item : {}
     * location : 广东 中山
     * number : eN9VbIjV
     * planting_type : 容器苗
     * purchase_day : 7
     * quality : B+(良好)
     * quote_count : 0
     * sample_pic : ["http://img.miaocang.cc/mc/df/201909101144340664.jpg"]
     * sample_pics : ["[\"http://img.miaocang.cc/mc/df/201909101144340664.jpg\"]"]
     * share_purchase_img_url : http://img.miaocang.cc/purchase_share.png
     * share_url : http://api.miaocang.cc:12100/style/h5/purchase_details.html?number=eN9VbIjV
     * status : buying
     * suitable : false
     * time : 2019-09-10
     * total_purchase_count : 1
     * total_quote_count : 0
     * unit : 株
     * unread_count : 0
     * user_auth_status : false
     * user_vip_status : false
     * valid_time : 2019-09-17
     */

    private boolean auth_status;
    private String base_name;
    private String company_name;
    private String company_number;
    private String create_time;
    private List<Details> details;
    private boolean has_guanzhu;
    private int inventory;
    private boolean is_followed;
    private int is_necessary_pic;
    private Item item;
    private String location;
    private String number;
    private String planting_type;
    private int purchase_day;
    private String quality;
    private int quote_count;
    private String sample_pic;
    private String share_purchase_img_url;
    private String share_url;
    private String status;
    private boolean suitable;
    private String time;
    private int total_purchase_count;
    private int total_quote_count;
    private String unit;
    private int unread_count;
    private boolean user_auth_status;
    private boolean user_vip_status;
    private String valid_time;
    private List<String> sample_pics;


}
