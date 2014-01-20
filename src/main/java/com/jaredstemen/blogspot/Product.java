package com.jaredstemen.blogspot;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Product extends AbstractEntity{

    private Date createdDate;
    private String imageUrl;
    private String title;
    private String  isActive;
    private String popularityIndex;
    private String itemId;
    private String department;
    private String upc;
    private String brand;
    private Date modifiedDate;
    private String itemHashint64;
    @ManyToOne
    private CategoryData categoryData;

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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

    public String getActive() {
        return isActive;
    }

    public void setActive(String active) {
        isActive = active;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getItemHashint64() {
        return itemHashint64;
    }

    public void setItemHashint64(String itemHashint64) {
        this.itemHashint64 = itemHashint64;
    }

}
