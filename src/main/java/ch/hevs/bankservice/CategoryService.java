package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Category;

public interface CategoryService {
    List<Category> getAllCategories();
    Category findCategoryById(Long id);
}

