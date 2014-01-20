package com.jaredstemen.blogspot.repository;

import com.jaredstemen.blogspot.CategoryData;
import com.jaredstemen.blogspot.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/19/14
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryData_category(String category);
}
