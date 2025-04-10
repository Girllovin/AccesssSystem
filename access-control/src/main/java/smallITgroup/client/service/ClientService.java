package smallITgroup.client.service;

import java.util.List;
import java.util.UUID;

import smallITgroup.building.dto.BuildingDto;
import smallITgroup.client.dto.CardHolderDto;

public interface ClientService {
	
	 CardHolderDto getCardHolderById(UUID uuid);
	 
	 List<CardHolderDto> getCardHolderByName(String firstName, String lastName);
	 
	 CardHolderDto getCardHolderByCard(int cardNumber);
	 
	 List<CardHolderDto> cardHolders();
	 
	 Boolean addCardHolder(CardHolderDto cardHolder);
	 
	 Boolean changeCardHolder(CardHolderDto cardHolder);
	 
	 CardHolderDto remooveCardHolder(UUID uuid);	 

}
