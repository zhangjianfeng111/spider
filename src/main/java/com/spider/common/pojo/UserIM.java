package com.spider.common.pojo;

import lombok.Data;

/**
 * @Auther: zhang
 * @Date: 2019-08-30 14:55
 * @Description:
 */
@Data
public class UserIM {
    private String userId;    //	否	用户ID，如果传空，系统会自动生成一个用户id
    private String name;//	是	登陆名
    private String displayName;//	string	是	显示名字
    private String password;//	string	否	用户密码，可以为空，如果为空，用户不可以在野火IM服务器登陆
    private String portrait;//	string	否	用户头像
    private String mobile;//	string	否	用户手机号码

    private String email;//	string	否	用户邮箱
    private String address;//	string	否	用户地址
    private String company;//	string	否	用户公司
    private String extra;// 否  附加信息
}
