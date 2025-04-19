package smallITgroup.door.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import smallITgroup.door.model.Door;

@Repository // Marks this interface as a Spring Data repository
public interface DoorRepository extends MongoRepository<Door, String> {
    // Inherits CRUD and query methods for Door entities with String as the ID type
}
