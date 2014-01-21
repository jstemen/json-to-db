package com.jaredstemen.blogspot;

import javax.persistence.*;

/**
 * Base object class for all DB-backed objects.  Includes logic for managing db ids.
 * User: Jared Stemen
 * Date: 1/18/14
 * Time: 6:26 PM
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

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
}
