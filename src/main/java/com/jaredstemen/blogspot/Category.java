package com.jaredstemen.blogspot;

import javax.persistence.Column;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Category extends AbstractEntity {
    @Column(unique = true)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
