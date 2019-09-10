package com.spider.common.miaocang;

import lombok.Data;

import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-09-10 11:48
 * @Description:
 */
@Data
public class WantBuyList {

    /**
     * code : 200
     * data : {"list":[{"auth_status":false,"base_name":" 红花玉蕊","company_name":"暂无企业","company_number":"1juVYYEK","create_time":"2019-09-10","details":[],"has_guanzhu":false,"inventory":36,"is_followed":false,"is_necessary_pic":0,"item":{},"location":"广东 中山","number":"iupQFaYA","planting_type":"假植苗","purchase_day":7,"quality":"B+(良好)","quote_count":0,"sample_pic":"[\"http://img.miaocang.cc/mc/9874343830/201909101129030131.jpg\",\"http://img.miaocang.cc/mc/9874343830/201909101129010756.jpg\"]","sample_pics":["[\"http://img.miaocang.cc/mc/9874343830/201909101129030131.jpg\"","\"http://img.miaocang.cc/mc/9874343830/201909101129010756.jpg\"]"],"share_purchase_img_url":"http://img.miaocang.cc/purchase_share.png","share_url":"http://api.miaocang.cc:12100/style/h5/purchase_details.html?number=iupQFaYA","status":"buying","suitable":false,"time":"2019-09-10","total_purchase_count":1,"total_quote_count":0,"unit":"株","unread_count":0,"user_auth_status":false,"user_vip_status":false,"valid_time":"2019-09-17"},{"auth_status":false,"base_name":" 红花紫荆","company_name":"和诚花木","company_number":"5vAx04jp","create_time":"2019-09-10","details":[],"has_guanzhu":false,"inventory":400,"is_followed":false,"is_necessary_pic":0,"item":{},"location":"广东 中山","number":"29RX1SqH","planting_type":"容器苗","purchase_day":7,"quality":"B+(良好)","quote_count":4,"share_purchase_img_url":"http://img.miaocang.cc/purchase_share.png","share_url":"http://api.miaocang.cc:12100/style/h5/purchase_details.html?number=29RX1SqH","status":"buying","suitable":false,"time":"2019-09-10","total_purchase_count":1,"total_quote_count":4,"unit":"株","unread_count":0,"user_auth_status":false,"user_vip_status":true,"valid_time":"2019-09-17","vip_level":"9"},{},{},{},{},{},{},{},{},{},{},{},{},{}],"page":1,"page_size":100,"total_cnt":386,"total_page":26}
     */

    private String code;
    private DataListBean data;


}
