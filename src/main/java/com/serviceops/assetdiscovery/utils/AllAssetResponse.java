package com.serviceops.assetdiscovery.utils;

import com.serviceops.assetdiscovery.rest.AssetRest;

import java.util.List;

public class AllAssetResponse {

    List<AssetRest> data;
    private int pageNo;
    private int pageSize;
    private long totalElements;

    public List<AssetRest> getAssetRestList() {
        return data;
    }

    public void setAssetRestList(List<AssetRest> assetRestList) {
        this.data = assetRestList;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

}
