package com.qjq.vo;

import com.qjq.po.News;
import com.qjq.po.Page;

import java.util.List;

public class NewsPage {
    private List<NewsVo> newsVoList;
    private Page page;

    public List<NewsVo> getNewsVoList() {
        return newsVoList;
    }

    public void setNewsVoList(List<NewsVo> newsVoList) {
        this.newsVoList = newsVoList;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
