package smallITgroup.client.service;

import java.util.List;
import java.util.UUID;

import smallITgroup.client.dto.CardHolderDto;

public interface ClientService {
	
	// Get a card holder by their unique ID
	CardHolderDto getCardHolderById(UUID uuid);
	 
	// Get card holders by first and last name
	List<CardHolderDto> getCardHolderByName(String firstName, String lastName);
	 
	// Get a card holder using their card number
	CardHolderDto getCardHolderByCard(int cardNumber);
	 
	// Get a list of all card holders
	List<CardHolderDto> cardHolders();
	 
	// Add a new card holder
	Boolean addCardHolder(CardHolderDto cardHolder);
	 
	// Update an existing card holder
	Boolean changeCardHolder(CardHolderDto cardHolder);
	 
	// Remove a card holder by ID
	CardHolderDto remooveCardHolder(UUID uuid);	 
}
