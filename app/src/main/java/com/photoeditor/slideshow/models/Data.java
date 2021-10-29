package com.photoeditor.slideshow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Data<T> {
    @SerializedName("application_name")
    @Expose
    private String applicationName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("page_count")
    @Expose
    private Integer pageCount;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    @SerializedName("results")
    @Expose
    private List<T> results = null;
    @SerializedName("row_count")
    @Expose
    private Integer rowCount;

    public List<T> getResults() {
        return this.results;
    }

    public void setResults(List<T> list) {
        this.results = list;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer num) {
        this.currentPage = num;
    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(Integer num) {
        this.pageCount = num;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public Integer getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(Integer num) {
        this.rowCount = num;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String str) {
        this.applicationName = str;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String str) {
        this.moduleName = str;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String toString() {
        return "Data{results=" + this.results + ", currentPage=" + this.currentPage + ", pageCount=" + this.pageCount + ", pageSize=" + this.pageSize + ", rowCount=" + this.rowCount + ", applicationName='" + this.applicationName + '\'' + ", moduleName='" + this.moduleName + '\'' + ", categoryName='" + this.categoryName + '\'' + '}';
    }
}
