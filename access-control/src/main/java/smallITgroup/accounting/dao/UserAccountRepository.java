package smallITgroup.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import smallITgroup.accounting.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
