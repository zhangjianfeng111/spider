package com.spider.common.pojo;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @Auther: zhang
 * @Date: 2019-08-30 14:49
 * @Description:
 */
@Data
@Table(name="USER_INFO")
public class UserInfo {
    private Integer userId;
    private String userName;
    private String nickname;
    private String heedImageUrl;
}
