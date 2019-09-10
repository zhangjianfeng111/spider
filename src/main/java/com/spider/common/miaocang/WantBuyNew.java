package com.spider.common.miaocang;

import lombok.Data;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Table(name = "WANT_BUY_NEW")
public class WantBuyNew {

    @AutoID
    private Integer wantBuyId ;
    //分枝数最大
    private Integer branchNumEnd ;
    //分枝数最小
    private Integer branchNumStart ;
    //分枝点最大
    private Integer branchPointEnd ;
    //分枝点最小
    private Integer branchPointStart ;
    //冠幅最大
    private Integer crownEnd ;
    //冠幅最小
    private Integer crownStart ;
    //胸径最大
    private Integer diameterEnd ;
    //胸径最小
    private Integer diameterStart ;
    //地径最大
    private Integer groundDiameterEnd ;
    //地径最小
    private Integer groundDiameterStart ;
    //米径最小
    private Integer metersDiameterStart;
    //米径最大
    private Integer metersDiameterEnd;
    //高度最大
    private Integer heightEnd ;
    //高度最小
    private Integer heightStart ;
    //求购数量
    @NotNull(message = "求购数量不能为空")
    private Integer num ;
    //品种id
    @NotNull(message = "苗木名称不能为空")
    private Integer seedingNameId ;
    //状态；0：正常状态；-1：结束求购 -2删除求购
    private Integer status ;
    @NotNull(message = "用户ID不能为空")
    private Integer userId ;
    //用苗地  市
    @NotNull(message = "城市不能为空")
    private String city ;
    //求购图片url
    private String cover ;
    //种植类型
    private String plantType ;
    //用苗地  省
    private String province ;
    //品质要求
    private String qualityRequirement ;
    //求购描述
    private String wantBuyDescribe ;
    //求购期限
    @NotNull(message = "求购期限不能为空")
    private Integer wantBuyTerm ;

    private String modelType;
    //创建时间
    private String createTime ;
    //到期时间
    private String maturityTime ;

}
