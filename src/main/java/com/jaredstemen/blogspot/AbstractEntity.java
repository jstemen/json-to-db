package com.jaredstemen.blogspot;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/18/14
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Id
    private Long id;
}
