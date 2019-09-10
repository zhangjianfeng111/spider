package com.spider.common.miaocang;

import lombok.Data;

/**
 * @Auther: zhang
 * @Date: 2019-09-10 12:17
 * @Description:
 */
@Data
public class Item {

    /**
     * autoSoldoutTime : 1568690197000
     * baseName :  红花紫荆
     * cityName : 中山
     * cityNumber : 248
     * commonNameNumber : 2916
     * commonNames : 红花紫荆,红花羊蹄甲,艳紫荆,紫荆花,红紫荆
     * companyName : 和诚花木
     * companyNumber : 5vAx04jp
     * context : {}
     * gmtCreate : 1568085397000
     * gmtModify : 1568085397000
     * id : 20130
     * inventory : 400
     * isNecessaryPic : 1
     * isParent : 1
     * latinNumber : 486
     * number : pBmh745B
     * parentQuoteCount : 0
     * planting_type : 容器苗
     * provinceName : 广东
     * provinceNumber : 19
     * purchaseDay : 7
     * quality : B+(良好)
     * quoteCount : 3
     * status : buying
     * uid : 8501032438
     * unit : 株
     * unreadCount : 0
     * vipStatus : P
     */

    private long autoSoldoutTime;
    private String baseName;
    private String cityName;
    private String cityNumber;
    private String commonNameNumber;
    private String commonNames;
    private String companyName;
    private String companyNumber;
    private ContextBean context;
    private long gmtCreate;
    private long gmtModify;
    private int id;
    private int inventory;
    private int isNecessaryPic;
    private int isParent;
    private int latinNumber;
    private String number;
    private int parentQuoteCount;
    private String planting_type;
    private String provinceName;
    private String provinceNumber;
    private int purchaseDay;
    private String quality;
    private int quoteCount;
    private String remark;
    private String status;
    private String uid;
    private String unit;
    private int unreadCount;
    private String vipStatus;


}
