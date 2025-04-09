package com.walking.carpractice.model;

import java.util.ArrayList;
import java.util.List;

public class CarFilter {
    private List<Long> brandIds = new ArrayList<>();

    private Long ownerId;

    private String numberQuery;

    public List<Long> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Long> brandIds) {
        this.brandIds = brandIds;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getNumberQuery() {
        return numberQuery;
    }

    public void setNumberQuery(String numberQuery) {
        this.numberQuery = numberQuery;
    }
}
