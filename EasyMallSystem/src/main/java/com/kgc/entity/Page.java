package com.kgc.entity;

public class Page {
    private int currentPageNo;
    private int pageSize;
    private int totalCount;
    private int totalPageCount;
    private Object data;

    public Page() {
    }



    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (totalCount%pageSize==0){
            totalPageCount=totalCount/pageSize;
        }else {
            totalPageCount=(totalCount/pageSize)+1;

        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPageNo=" + currentPageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", data=" + data +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }


}
