package ch.hevs.presentation.converters;

import ch.hevs.businessobject.Category;
import ch.hevs.bankservice.CategoryService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "categoryConverter", managed = true)
@RequestScoped
public class CategoryConverter implements jakarta.faces.convert.Converter<Category> {

    @EJB
    private CategoryService categoryService;

    @Override
    public Category getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            return categoryService.findCategoryById(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid category ID: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Category category) {
        if (category == null) {
            return "";
        }
        return category.getId() != null ? category.getId().toString() : "";
    }
}
