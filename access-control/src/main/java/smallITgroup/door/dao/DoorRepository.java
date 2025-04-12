package smallITgroup.door.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import smallITgroup.door.model.Door;

@Repository
public interface DoorRepository extends MongoRepository<Door, String>{

}
