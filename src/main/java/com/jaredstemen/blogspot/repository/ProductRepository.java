package com.jaredstemen.blogspot.repository;

import com.jaredstemen.blogspot.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: Jared Stemen
 * Date: 1/19/14
 * Time: 2:41 PM
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryData_category(String category);

    List<Product> findByTitleIsContainingIgnoreCaseAndCategoryData_Category(String title, String category);

    List<Product> findByTitleIsContainingIgnoreCase(String title);
}
