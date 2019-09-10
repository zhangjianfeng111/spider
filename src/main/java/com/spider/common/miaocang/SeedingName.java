package com.spider.common.miaocang;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

@Data
@Table(name = "SEEDING_NAME")
public class SeedingName {

    private Integer id;

    //苗木名称
    private String baseName;
    //开头字母
    private String beginLetter;
    //常用名称
    private String commonNames;
    //创建时间
    private String createTime;
    //修改时间
    private String modifyTime;

}
