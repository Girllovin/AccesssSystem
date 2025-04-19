package smallITgroup.building.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import smallITgroup.building.model.Building;

// The @Repository annotation marks this interface as a Spring Data repository.
// It indicates that this interface is intended to interact with MongoDB for CRUD operations on Building entities.
@Repository
public interface BuildingRepository extends MongoRepository<Building, Integer> {

    // MongoRepository provides default CRUD methods like save(), findAll(), findById(), delete(), etc.
    // The first generic parameter (Building) specifies the entity type that this repository works with.
    // The second generic parameter (Integer) is the type of the entity's ID, which in this case is Integer for the Building's ID.
   
}
