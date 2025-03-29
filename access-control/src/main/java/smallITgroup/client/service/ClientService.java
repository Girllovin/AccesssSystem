package smallITgroup.client.service;

import java.util.List;
import java.util.UUID;

import smallITgroup.client.dto.CardHolderDto;

public interface ClientService {
	
	 CardHolderDto geCardHolderById(UUID uuid);
	 
	 CardHolderDto geCardHolderByName(String firstName, String lastName);
	 
	 CardHolderDto geCardHolderByCard(int cardNumber);
	 
	 List<CardHolderDto> cardHolders();
	 
	 boolean addCardHolder(CardHolderDto cardHolder);
	 
	 boolean changeCardHolder(CardHolderDto cardHolder);
	 
	 CardHolderDto remooveCardHolder(UUID uuid);
}
