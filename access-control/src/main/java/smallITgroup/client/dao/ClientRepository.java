package smallITgroup.client.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.model.CardHolder;

public interface ClientRepository {
	
	Optional<CardHolder> findCardHolderById(UUID uuid);
	
	Optional<CardHolder> findCardHolderByName(String firstName, String lastName);
	
	Optional<CardHolder> findCardHolderByCard(Integer cardNumber);
	
	List<CardHolder> cardHoldersList();
	
	boolean addCardHolder(CardHolder cardHolder);
	
	boolean changeCardHolder(CardHolder cardHolder);
	
	CardHolder cardHolderRemove(UUID uuid);

}
