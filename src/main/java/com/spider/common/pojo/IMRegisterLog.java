package com.spider.common.pojo;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @Auther: zhang
 * @Date: 2019-08-30 16:36
 * @Description:
 */
@Data
@Table(name="IM_REGISTER_LOG")
public class IMRegisterLog {
    private Integer userId;
    private Integer statusCode;
}
