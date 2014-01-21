package com.jaredstemen.blogspot;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity used to house all data pertaining to a product's category
 * User: Jared Stemen
 * Date: 1/19/14
 * Time: 3:05 PM
 */
@Entity
/**
 *Ensure that every parent category - category relationship is unique in the db.
 * I'm assuming that if two categories have different parent categories, then the categories themselves
 * must be different categories.
 */

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"category", "parentCategory"}))
public class CategoryData extends AbstractEntity {
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
