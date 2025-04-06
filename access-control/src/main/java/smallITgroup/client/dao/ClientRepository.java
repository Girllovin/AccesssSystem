package smallITgroup.client.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import smallITgroup.client.model.CardHolder;

public interface ClientRepository extends MongoRepository<CardHolder, UUID>{
	List<CardHolder> findByFirstNameAndLastName(String firstName, String lastName);
	
	@Query("{ 'firstName': { $regex: ?0, $options: 'i' } }")
	List<CardHolder> findByFirstNameIgnoreCase(String firstName);

	@Query("{ 'lastName': { $regex: ?0, $options: 'i' } }")
	List<CardHolder> findByLastNameIgnoreCase(String lastName);
	
	@Query("{ 'firstName': { $regex: :#{#first}, $options: 'i' }, 'lastName': { $regex: :#{#last}, $options: 'i' } }")
	List<CardHolder> searchByNames(@Param("first") String firstName, @Param("last") String lastName);


//  Database access:  gerllovin Accsess123!
}
