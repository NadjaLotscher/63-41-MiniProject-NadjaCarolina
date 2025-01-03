package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CategoryServiceBean implements CategoryService {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    @Override
    public List<Category> getAllCategories() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }
    
    @Override
    public Category findCategoryById(Long id) {
        return em.find(Category.class, id);
    }

}

