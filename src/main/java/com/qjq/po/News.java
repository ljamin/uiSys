package com.qjq.po;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private String id;

    private String fromUserId;

    private String title;

    private Integer typeId;

    private String filename="";

    private String createtime;

    private Integer likesnum=0;

    private Integer commentsnum=0;

    private Integer againstnum=0;

    private String content;

    private String picPath="";

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime.length()>=21?createtime.substring(0,createtime.length()-2):createtime;
    }

    public Integer getLikesnum() {
        return likesnum;
    }

    public void setLikesnum(Integer likesnum) {
        this.likesnum = likesnum;
    }

    public Integer getCommentsnum() {
        return commentsnum;
    }

    public void setCommentsnum(Integer commentsnum) {
        this.commentsnum = commentsnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getAgainstnum() {
        return againstnum;
    }

    public void setAgainstnum(Integer againstnum) {
        this.againstnum = againstnum;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        News other = (News) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFromUserId() == null ? other.getFromUserId() == null : this.getFromUserId().equals(other.getFromUserId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getLikesnum() == null ? other.getLikesnum() == null : this.getLikesnum().equals(other.getLikesnum()))
            && (this.getCommentsnum() == null ? other.getCommentsnum() == null : this.getCommentsnum().equals(other.getCommentsnum()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFromUserId() == null) ? 0 : getFromUserId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getLikesnum() == null) ? 0 : getLikesnum().hashCode());
        result = prime * result + ((getCommentsnum() == null) ? 0 : getCommentsnum().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", title='" + title + '\'' +
                ", typeId=" + typeId +
                ", filename='" + filename + '\'' +
                ", createtime='" + createtime + '\'' +
                ", likesnum=" + likesnum +
                ", commentsnum=" + commentsnum +
                ", content='" + content + '\'' +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}