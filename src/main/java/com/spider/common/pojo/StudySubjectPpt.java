package com.spider.common.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.TableTemplate;

import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "STUDY_SUBJECT_PPT")
@TableTemplate("order by sort desc")
public class StudySubjectPpt {
    private Integer id;

    private Integer subId;

    private String pic;

    private String coverPic;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

}