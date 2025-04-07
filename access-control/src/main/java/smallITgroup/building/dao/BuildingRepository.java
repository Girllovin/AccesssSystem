package smallITgroup.building.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import smallITgroup.building.model.Building;

@Repository
public interface BuildingRepository extends MongoRepository<Building, Integer>{

	
}
