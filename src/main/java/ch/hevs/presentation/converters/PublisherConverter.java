package ch.hevs.presentation.converters;

import ch.hevs.businessobject.Publisher;
import ch.hevs.bankservice.PublisherService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "publisherConverter", managed = true)
@RequestScoped
public class PublisherConverter implements jakarta.faces.convert.Converter<Publisher> {

    @EJB
    private PublisherService publisherService;

    @Override
    public Publisher getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            return publisherService.findPublisherById(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid publisher ID: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Publisher publisher) {
        if (publisher == null || publisher.getId() == null) {
            return "";
        }
        return publisher.getId().toString();
    }
}
