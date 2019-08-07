package com.spider.common.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.core.annotatoin.Table;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString
@Table(name="NURSERY_DETAIL")
public class NurseryDetail {
    private Integer id;

    private Integer plantType;

    private String plantName;

    private String plantTitle;

    private Integer num;

    private String unit;

    private String showPic;

    private BigDecimal loadingPrice;

    private Float heignt;

    private Float crown;

    private Float diameter;

    private Float branchPoint;

    private Float four;

    private Float groundDiameter;

    private String offspring;

    private String seedlingType;

    private String dendroids;

    private String branch;

    private String density;

    private String soilBallDress;

    private String soilBall;

    private Float soilBallSize;

    private Float soilThickness;

    private String soilBallShape;

    private String safeguard;

    private String remark;

    private Integer userId;

    private String nurseryAddress;

    private String material;

    private String subsoil;

    private String size;

    private String wearBag;

    private String aRequire;

    private String advanced;

    private String hasTrunk;

    private String location;

    private Integer status;

    private String treeAge;

    private Float rodDiameter;

    private String hint;

    private Float weight;

    private Date createTime;

    private Date updateTime;

    private Integer istop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlantType() {
        return plantType;
    }

    public void setPlantType(Integer plantType) {
        this.plantType = plantType;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName == null ? null : plantName.trim();
    }

    public String getPlantTitle() {
        return plantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        this.plantTitle = plantTitle == null ? null : plantTitle.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    public BigDecimal getLoadingPrice() {
        return loadingPrice;
    }

    public void setLoadingPrice(BigDecimal loadingPrice) {
        this.loadingPrice = loadingPrice;
    }

    public Float getHeignt() {
        return heignt;
    }

    public void setHeignt(Float heignt) {
        this.heignt = heignt;
    }

    public Float getCrown() {
        return crown;
    }

    public void setCrown(Float crown) {
        this.crown = crown;
    }

    public Float getDiameter() {
        return diameter;
    }

    public void setDiameter(Float diameter) {
        this.diameter = diameter;
    }

    public Float getBranchPoint() {
        return branchPoint;
    }

    public void setBranchPoint(Float branchPoint) {
        this.branchPoint = branchPoint;
    }

    public Float getFour() {
        return four;
    }

    public void setFour(Float four) {
        this.four = four;
    }

    public Float getGroundDiameter() {
        return groundDiameter;
    }

    public void setGroundDiameter(Float groundDiameter) {
        this.groundDiameter = groundDiameter;
    }

    public String getOffspring() {
        return offspring;
    }

    public void setOffspring(String offspring) {
        this.offspring = offspring == null ? null : offspring.trim();
    }

    public String getSeedlingType() {
        return seedlingType;
    }

    public void setSeedlingType(String seedlingType) {
        this.seedlingType = seedlingType == null ? null : seedlingType.trim();
    }

    public String getDendroids() {
        return dendroids;
    }

    public void setDendroids(String dendroids) {
        this.dendroids = dendroids == null ? null : dendroids.trim();
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density == null ? null : density.trim();
    }

    public String getSoilBallDress() {
        return soilBallDress;
    }

    public void setSoilBallDress(String soilBallDress) {
        this.soilBallDress = soilBallDress == null ? null : soilBallDress.trim();
    }

    public String getSoilBall() {
        return soilBall;
    }

    public void setSoilBall(String soilBall) {
        this.soilBall = soilBall == null ? null : soilBall.trim();
    }

    public Float getSoilBallSize() {
        return soilBallSize;
    }

    public void setSoilBallSize(Float soilBallSize) {
        this.soilBallSize = soilBallSize;
    }

    public Float getSoilThickness() {
        return soilThickness;
    }

    public void setSoilThickness(Float soilThickness) {
        this.soilThickness = soilThickness;
    }

    public String getSoilBallShape() {
        return soilBallShape;
    }

    public void setSoilBallShape(String soilBallShape) {
        this.soilBallShape = soilBallShape == null ? null : soilBallShape.trim();
    }

    public String getSafeguard() {
        return safeguard;
    }

    public void setSafeguard(String safeguard) {
        this.safeguard = safeguard == null ? null : safeguard.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNurseryAddress() {
        return nurseryAddress;
    }

    public void setNurseryAddress(String nurseryAddress) {
        this.nurseryAddress = nurseryAddress == null ? null : nurseryAddress.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getSubsoil() {
        return subsoil;
    }

    public void setSubsoil(String subsoil) {
        this.subsoil = subsoil == null ? null : subsoil.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getWearBag() {
        return wearBag;
    }

    public void setWearBag(String wearBag) {
        this.wearBag = wearBag == null ? null : wearBag.trim();
    }

    public String getaRequire() {
        return aRequire;
    }

    public void setaRequire(String aRequire) {
        this.aRequire = aRequire == null ? null : aRequire.trim();
    }

    public String getAdvanced() {
        return advanced;
    }

    public void setAdvanced(String advanced) {
        this.advanced = advanced == null ? null : advanced.trim();
    }

    public String getHasTrunk() {
        return hasTrunk;
    }

    public void setHasTrunk(String hasTrunk) {
        this.hasTrunk = hasTrunk == null ? null : hasTrunk.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTreeAge() {
        return treeAge;
    }

    public void setTreeAge(String treeAge) {
        this.treeAge = treeAge == null ? null : treeAge.trim();
    }

    public Float getRodDiameter() {
        return rodDiameter;
    }

    public void setRodDiameter(Float rodDiameter) {
        this.rodDiameter = rodDiameter;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? null : hint.trim();
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }
}