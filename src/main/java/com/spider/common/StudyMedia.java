package com.spider.common;

import org.beetl.sql.core.annotatoin.Table;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Table(name="STUDY_MEDIA")
public class StudyMedia {
    private Integer id;

    private String mediaName;

    private String mediaUrl;

    private String mediaActualUrl;

    private String mediaContentType;

    private Integer mediaType;

    private String mediaSize;

    private String mediaTime;

    private String showTime;

    private Integer isDeleted;

    private Integer sort;

    private MultipartFile files;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName == null ? null : mediaName.trim();
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
    }

    public String getMediaActualUrl() {
        return mediaActualUrl;
    }

    public void setMediaActualUrl(String mediaActualUrl) {
        this.mediaActualUrl = mediaActualUrl == null ? null : mediaActualUrl.trim();
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType == null ? null : mediaContentType.trim();
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(String mediaSize) {
        this.mediaSize = mediaSize == null ? null : mediaSize.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public MultipartFile getFiles() {
        return files;
    }

    public void setFiles(MultipartFile files) {
        this.files = files;
    }

    public String getMediaTime() {
        return mediaTime;
    }

    public void setMediaTime(String mediaTime) {
        this.mediaTime = mediaTime;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}