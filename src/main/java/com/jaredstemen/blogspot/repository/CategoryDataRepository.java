package com.jaredstemen.blogspot.repository;

import com.jaredstemen.blogspot.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: jared
 * Date: 1/19/14
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */

public interface CategoryDataRepository extends JpaRepository<CategoryData,Long> {
    CategoryData findByParentCategoryAndCategory(String parentCategory, String category);
}
