package smallITgroup.client.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import smallITgroup.client.model.CardHolder;

public interface ClientRepository extends MongoRepository<CardHolder, UUID> {

    // Custom query method to find CardHolders by their first and last name.
    List<CardHolder> findByFirstNameAndLastName(String firstName, String lastName);

    // Query to find CardHolders whose first name matches the provided string (case-insensitive)
    @Query("{ 'firstName': { $regex: ?0, $options: 'i' } }")
    List<CardHolder> findByFirstNameIgnoreCase(String firstName);

    // Query to find CardHolders whose last name matches the provided string (case-insensitive)
    @Query("{ 'lastName': { $regex: ?0, $options: 'i' } }")
    List<CardHolder> findByLastNameIgnoreCase(String lastName);

    // Custom query using Spring Expression Language (SpEL) to search by both first and last names (case-insensitive)
    @Query("{ 'firstName': { $regex: :#{#first}, $options: 'i' }, 'lastName': { $regex: :#{#last}, $options: 'i' } }")
    List<CardHolder> searchByNames(@Param("first") String firstName, @Param("last") String lastName);
}
