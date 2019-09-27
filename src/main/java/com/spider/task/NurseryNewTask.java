package com.spider.task;

import com.spider.common.pojo.NurseryDetail;
import com.spider.common.pojo.Supply;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-01-10 10:18
 * @Description:
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class NurseryNewTask {
    @Autowired
    SQLManager sqlManager;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduled() throws Exception {
        List<NurseryDetail> list = sqlManager.query(NurseryDetail.class).select();
        for (NurseryDetail nurseryDetail : list) {
            Supply supply = new Supply();
            supply.setUserId(nurseryDetail.getUserId());
            supply.setVarieties(nurseryDetail.getPlantName());
            supply.setPlantType(nurseryDetail.getPlantType());
            supply.setNumber(nurseryDetail.getNum());
            supply.setUnit(nurseryDetail.getUnit());
            supply.setSupplyUrl(nurseryDetail.getShowPic());
            supply.setUnitPrice(nurseryDetail.getLoadingPrice().floatValue());
            supply.setHeightS(nurseryDetail.getHeignt());
            supply.setCrownWidthS(nurseryDetail.getCrown());
            supply.setBranchPoint(nurseryDetail.getBranchPoint());
            supply.setRaiseMethod(nurseryDetail.getOffspring());
            supply.setCulturalMethod(nurseryDetail.getSeedlingType());
            supply.setModel(nurseryDetail.getDendroids());
            supply.setType(nurseryDetail.getBranch());
            supply.setDensity(nurseryDetail.getDensity());
            supply.setSoilBallDress(nurseryDetail.getSoilBallDress());
            supply.setSoilBall(nurseryDetail.getSoilBall());
            supply.setSoilBallSize(nurseryDetail.getSoilBallSize()==null?"":nurseryDetail.getSoilBallSize().toString());
            supply.setSoilThickness(nurseryDetail.getSoilThickness()==null?"":nurseryDetail.getSoilThickness().toString());
            supply.setSoilBallShape(nurseryDetail.getSoilBallShape());
            supply.setSafeguard(nurseryDetail.getSafeguard());
            supply.setRemark(nurseryDetail.getRemark());
            supply.setSeedlingSourceAddress(nurseryDetail.getNurseryAddress());
            supply.setHasTrunk(nurseryDetail.getHasTrunk());
            supply.setUploadtime(nurseryDetail.getCreateTime());
            long count = sqlManager.query(Supply.class).andEq("supply_url",nurseryDetail.getShowPic()).count();
            if(count<=0)
                sqlManager.insertTemplate(supply);
        }        
    }



}
