package com.qjq.po;

import java.io.Serializable;

public class Great implements Serializable {
    private String fromUserId;

    private String newsId;

    private static final long serialVersionUID = 1L;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId == null ? null : newsId.trim();
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
        Great other = (Great) that;
        return (this.getFromUserId() == null ? other.getFromUserId() == null : this.getFromUserId().equals(other.getFromUserId()))
            && (this.getNewsId() == null ? other.getNewsId() == null : this.getNewsId().equals(other.getNewsId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFromUserId() == null) ? 0 : getFromUserId().hashCode());
        result = prime * result + ((getNewsId() == null) ? 0 : getNewsId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fromUserId=").append(fromUserId);
        sb.append(", newsId=").append(newsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}