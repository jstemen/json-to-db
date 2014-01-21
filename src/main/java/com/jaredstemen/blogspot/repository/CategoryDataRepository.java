package com.jaredstemen.blogspot.repository;

import com.jaredstemen.blogspot.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Jared Stemen
 * Date: 1/19/14
 * Time: 2:41 PM
 */

public interface CategoryDataRepository extends JpaRepository<CategoryData, Long> {
    CategoryData findByParentCategoryAndCategory(String parentCategory, String category);
}
