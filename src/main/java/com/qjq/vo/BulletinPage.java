package com.qjq.vo;

import com.qjq.po.Bulletin;
import com.qjq.po.Page;

import java.util.List;

public class BulletinPage {
    private List<Bulletin> bulletins;
    private Page page;

    public List<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(List<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
