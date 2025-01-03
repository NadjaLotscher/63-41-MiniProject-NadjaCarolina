package ch.hevs.bankservice;

import ch.hevs.businessobject.Publisher;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PublisherServiceBean implements PublisherService {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    @Override
    public List<Publisher> getAllPublishers() {
        return em.createQuery("SELECT p FROM Publisher p", Publisher.class).getResultList();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return em.find(Publisher.class, id);
    }

    @Override
    public void addPublisher(Publisher publisher) {
        em.persist(publisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        em.merge(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher publisher = em.find(Publisher.class, id);
        if (publisher != null) {
            em.remove(publisher);
        }
    }
}
