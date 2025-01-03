package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Publisher;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Publisher findPublisherById(Long id);
    void addPublisher(Publisher publisher);
    void updatePublisher(Publisher publisher);
    void deletePublisher(Long id);
}

