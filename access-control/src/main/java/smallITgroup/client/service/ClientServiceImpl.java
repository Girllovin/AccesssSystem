package smallITgroup.client.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.client.dao.ClientRepository;
import smallITgroup.client.dao.exeptions.CardHolderNotFoundExeption;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.model.Card;
import smallITgroup.client.model.CardHolder;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	final ClientRepository clientRepository;

	@Override
	public CardHolderDto getCardHolderById(UUID uuid) {
		CardHolder cardHolder = clientRepository.findCardHolderById(uuid).orElseThrow(() -> new CardHolderNotFoundExeption());
		return new CardHolderDto(cardHolder.getUuid(), cardHolder.getFirstName(), cardHolder.getLastName(), cardHolder.getCompany(), null, null);
	}

	@Override
	public CardHolderDto getCardHolderByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardHolderDto getCardHolderByCard(int cardNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardHolderDto> cardHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCardHolder(CardHolderDto cardHolderDto) {
//		if (clientRepository.findCardHolderById(cardHolderDto.getUuid()).isPresent()) {
//			return false;
//		}
		System.out.println((cardHolderDto));
		CardHolder cardHolder = new CardHolder(cardHolderDto.getUuid(), cardHolderDto.getFirstName(), cardHolderDto.getLastName(), cardHolderDto.getCompany());
		cardHolder.setCards(cardHolderDto.getCards().entrySet()
					.stream()
					.collect(Collectors.toMap(Map.Entry::getKey,  
                entry -> new Card(entry.getKey(),true,entry.getValue()))));
		cardHolder.setPermissions(cardHolderDto.getPermissions());
		
		System.out.println(cardHolder);
                
		clientRepository.addCardHolder(cardHolder);
		return true;
	}

	@Override
	public boolean changeCardHolder(CardHolderDto cardHolder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CardHolderDto remooveCardHolder(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
