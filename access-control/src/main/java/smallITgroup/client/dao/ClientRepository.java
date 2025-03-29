package smallITgroup.client.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import smallITgroup.*;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.model.CardHolder;

public interface ClientRepository {
	
	Optional<CardHolderDto> findCardHolderById(UUID uuid);
	
	Optional<CardHolderDto> findCardHolderByName(String firstName, String lastName);
	
	Optional<CardHolderDto> findCardHolderByCard(Integer cardNumber);
	
	List<CardHolder> cardHoldersList();

}
