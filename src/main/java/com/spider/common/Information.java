package com.spider.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @author qingyin
 * @date 2016/9/12
 */
@Getter
@Setter
@ToString
@Table(name = "INFORMATION")
public class Information {

    private Long infoId;

    private String infoTitle;

    private String infoSubTitle;
    private String gardenContent;

    private String infoContent;

    private String infoFrom;

    private Integer viewTotal;

    private String uploadtime;

    private String infoUrl; //图片url

    private String[] infoWH;  //图片url对应的width和height

    private Integer imgType;

    private Integer status;

    private String programName;

    private String infomationUrl;

    private String infomationDesc;

    private Integer istop;

    private Integer programId;

    private Integer labelId;

    private Integer sharetotal;

    private String relatedInfoId;

    private Integer ispush;

    private Integer isIndex;

    private String htmlContent;

    private Integer isShow;

    private Integer infoType;

    private String[] relateInfoId; //相关资讯id

    private String[] relateInfoTitle; //相关资讯title

    private String[] imagePath; //图片集路径

    private String[] imageDesc; //图片集描述

    private String[] imageSize;//图片大小

    private String detailUrl;

}
