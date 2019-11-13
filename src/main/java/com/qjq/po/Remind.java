package com.qjq.po;

import java.io.Serializable;

public class Remind implements Serializable {
    private String id;

    private String fromUserId;

    private String toUserId;

    private String contentid;

    private Integer readstatus=0;

    private String createtime;

    private Integer remindType=0;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime.length()>=21?createtime.substring(0,createtime.length()-2):createtime;
    }

    public Integer getRemindType() {
        return remindType;
    }

    public void setRemindType(Integer remindType) {
        this.remindType = remindType;
    }

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
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid == null ? null : contentid.trim();
    }

    public Integer getReadstatus() {
        return readstatus;
    }

    public void setReadstatus(Integer readstatus) {
        this.readstatus = readstatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", contentid=").append(contentid);
        sb.append(", readstatus=").append(readstatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}