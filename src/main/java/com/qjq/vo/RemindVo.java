package com.qjq.vo;

import com.qjq.po.Remind;

public class RemindVo extends Remind {
   private String content;
   private String toNewsId;

    public String getContent() {
        return content;
    }

    public void setConetent(String conetent) {
        this.content = content;
    }


    public String getToNewsId() {
        return toNewsId;
    }

    public void setToNewsId(String toNewsId) {
        this.toNewsId = toNewsId;
    }
}
