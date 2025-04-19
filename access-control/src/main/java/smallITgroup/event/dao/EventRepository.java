package smallITgroup.event.dao;

import smallITgroup.event.model.Event;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

// This interface extends MongoRepository to provide CRUD operations for the Event entity
// MongoRepository is a Spring Data interface that simplifies interactions with MongoDB
public interface EventRepository extends MongoRepository<Event, Long> {

    // In this case, EventRepository will automatically have all the methods of MongoRepository,
    // such as save(), findAll(), findById(), delete(), etc. for the Event entity.
    // You can also define custom query methods if needed (e.g., findByEventName(String name)).
	
	List<Event> findByNewEventsTrue();

	List<Event> findByDoorid(String doorId);

}