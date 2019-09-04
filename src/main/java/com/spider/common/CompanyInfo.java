package com.spider.common;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @Auther: zhang
 * @Date: 2019-08-20 09:27
 * @Description:
 */
@Data
@Table(name="COMPANY_INFO")
public class CompanyInfo {

    private String companyName;//苗圃名称
    private String shortName;
    private String contacts;//联系人
    private String mobile;//联系电话

    private Integer companyId;
    private Integer userId;
    private String companyDesc;//简介
    private Integer companyArea;//苗圃面积
    private String provice;//省
    private String city;//市
    private String area;//区
    private String address;//苗圃地址
    private String companyImage;//产品图片
    private String mainBusiness;//主营产品
    private Integer status;
    private String staffSize;
    private String companyLon;//经度
    private String companyLat;//纬度
    private String createTime;
    private String updateTime;
}
