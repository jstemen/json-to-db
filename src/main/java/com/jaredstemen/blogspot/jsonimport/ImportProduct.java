
package com.jaredstemen.blogspot.jsonimport;
import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.googlecode.jsonschema2pojo")
public class ImportProduct {
    private String createdDate;
    private String imageUrl;
    private String title;
    private String category;
    private String isActive;
    private String popularityIndex;
    private String itemId;
    private String parentCategory;
    private String department;
    private String upc;
    private String brand;
    private String modifiedDate;
    private String itemHashint64;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPopularityIndex() {
        return popularityIndex;
    }

    public void setPopularityIndex(String popularityIndex) {
        this.popularityIndex = popularityIndex;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getItemHashint64() {
        return itemHashint64;
    }

    public void setItemHashint64(String itemHashint64) {
        this.itemHashint64 = itemHashint64;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}