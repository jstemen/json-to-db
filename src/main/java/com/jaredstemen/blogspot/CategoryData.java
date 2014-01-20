package com.jaredstemen.blogspot;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/19/14
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"category", "parentCategory"}))
public class CategoryData extends AbstractEntity{
    private String category;
    private String parentCategory;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
}
