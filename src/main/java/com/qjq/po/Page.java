package com.qjq.po;

public class Page {
    private int currentPage = 1;//当前页
    private int pageSize = 5; // 每页显示条数
    private int totalPage; //总页数
    private int rows; // 数据总条数
    private int begin; //起始下标




    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        //计算总页数，根据数据总条数是奇还是偶判断
        if (rows%pageSize == 0){
            totalPage=rows/pageSize;
        }else {
            totalPage=rows/pageSize+1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getBegin() {
        // 计算起始下标
        begin = (currentPage-1)*pageSize;
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }
}
