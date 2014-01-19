package com.jaredstemen.blogspot;

import com.google.common.collect.ImmutableList;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParentCategory extends AbstractEntity {
    @Column(unique = true)
    private String name;

    public List<Category> getCategoryList() {
        return Collections.unmodifiableList(categoryList);
    }

    public void addCategory(Category category) {
        this.categoryList.add(category);
    }

    public void removeCategory(Category category) {
        this.categoryList.remove(category);
    }

    public ParentCategory(String name) {
        this.name = name;
    }

    @OneToMany
    private List<Category> categoryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
