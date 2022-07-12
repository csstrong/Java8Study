package com.chensi.util.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/***********************************
 * @author chensi
 * @date 2022/6/29 17:50
 ***********************************/

public class Page {
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    protected int page = 1;

    protected int pageSize = 0;

    protected String orderBy = null;

    protected String order = "ASC";

    protected Object rows = null;

    protected int records = -1;

    protected boolean autoCount = false;

    protected int start = 0;

    protected int total = -1;

    public Page() {
        this.records = 0;
        this.rows = new ArrayList<>();
    }

    public Page(Object rows, int records) {
        this.rows = rows;
        this.records = records;
    }

    public Page(int page, int pageSize, String orderBy, String order) {
        this.page = page;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        setOrder(order);
        calculateStart();
    }

    public boolean isAsc() {
        return !"DESC".equalsIgnoreCase(this.order);
    }

    public String getInverseOrder() {
        if ("DESC".equalsIgnoreCase(this.order))
            return "ASC";
        return "DESC";
    }

    public boolean isPageSizeEnabled() {
        return (this.pageSize > 0);
    }

    public boolean isStartEnabled() {
        return (this.start >= 0);
    }

    public boolean isOrderEnabled() {
        return (this.orderBy != null && this.orderBy.trim().length() != 0);
    }

    public boolean isPreviousEnabled() {
        return (this.page > 1);
    }

    public boolean isNextEnabled() {
        return (this.page < this.total);
    }

    public boolean isTotalEnabled() {
        return (this.total >= 0);
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
        calculateStart();
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        calculateStart();
        calculateTotal();
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return this.order;
    }

    public Object getRows() {
        return this.rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public int getRecords() {
        return this.records;
    }

    public void setRecords(int records) {
        this.records = records;
        calculateTotal();
    }

    public boolean isAutoCount() {
        return this.autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public int getStart() {
        return this.start;
    }

    public int getTotal() {
        return this.total;
    }

    public void setPageData(List list, int totalCount, int pageNo, int pageSize) {
        setRows(list);
        setRecords(totalCount);
        setPageSize(pageSize);
        setPage(pageNo);
    }

    //=====================================
    private void setOrder(String order) {
        if ("ASC".equalsIgnoreCase(order) || "DESC".equalsIgnoreCase(order)) {
            this.order = order.toLowerCase(Locale.CHINA);
        } else {
            throw new IllegalArgumentException("order should be 'DESC' or 'ASC'");
        }
    }

    private void calculateStart() {
        if (this.page < 1 || this.pageSize < 1) {
            this.start = -1;
        } else {
            this.start = (this.start - 1) * this.pageSize;
        }
    }

    private void calculateTotal() {
        if (this.page < 1 || this.pageSize < 1) {
            this.start = -1;
        } else {
            this.total = (this.records - 1) / this.pageSize + 1;
        }
    }
}
