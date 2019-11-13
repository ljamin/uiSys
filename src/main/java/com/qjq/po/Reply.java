package com.qjq.po;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
    private String commentsId;

    private String fromUserId;

    private String toUserId;

    private Date createtime;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId == null ? null : commentsId.trim();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        Reply other = (Reply) that;
        return (this.getCommentsId() == null ? other.getCommentsId() == null : this.getCommentsId().equals(other.getCommentsId()))
            && (this.getFromUserId() == null ? other.getFromUserId() == null : this.getFromUserId().equals(other.getFromUserId()))
            && (this.getToUserId() == null ? other.getToUserId() == null : this.getToUserId().equals(other.getToUserId()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentsId() == null) ? 0 : getCommentsId().hashCode());
        result = prime * result + ((getFromUserId() == null) ? 0 : getFromUserId().hashCode());
        result = prime * result + ((getToUserId() == null) ? 0 : getToUserId().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentsId=").append(commentsId);
        sb.append(", fromUserId=").append(fromUserId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", createtime=").append(createtime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}