package smallITgroup.event.dao;
import smallITgroup.event.model.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository <Event, Long> {

}
