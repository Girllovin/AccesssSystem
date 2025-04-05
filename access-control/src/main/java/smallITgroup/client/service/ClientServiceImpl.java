package smallITgroup.client.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import jakarta.xml.ws.Holder;
import lombok.RequiredArgsConstructor;
import smallITgroup.client.dao.ClientRepository;
import smallITgroup.client.dao.exeptions.CardHolderNotFoundExeption;
import smallITgroup.client.dao.exeptions.NameNotCorrectExeption;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.dto.CardRecieveDto;
import smallITgroup.client.model.Card;
import smallITgroup.client.model.CardHolder;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	final ClientRepository clientRepository;

	@Override
	public CardHolderDto getCardHolderById(UUID uuid) {
		CardHolder cardHolder = clientRepository.findById(uuid).orElseThrow(() -> new CardHolderNotFoundExeption());
		CardRecieveDto cards = new CardRecieveDto(cardHolder.getCards().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getInactiveDate())));
		return new CardHolderDto(cardHolder.getUuid(), cardHolder.getFirstName(), cardHolder.getLastName(),
				cardHolder.getCompany(), cards.getDto(), cardHolder.getPermissions());
	}

	@Override
	public List<CardHolderDto> getCardHolderByName(String firstName, String lastName) {
		if ((firstName == null||firstName.isBlank()||firstName.isEmpty())&&(lastName == null||lastName.isBlank()||lastName.isEmpty())) {
		throw new NameNotCorrectExeption();
		}
		if (!(firstName.isBlank()||!firstName.isEmpty())) {
			return clientRepository
					.findAll().stream().filter(holder -> holder.getFirstName() == firstName)
//					.filter(holder -> (holder.getLastName() == null)
//							||(holder.getLastName() == lastName)
//							|| (holder.getLastName().isBlank()))
					.map(holder -> {
			            CardRecieveDto cards = new CardRecieveDto(
			                holder.getCards().entrySet().stream()
			                    .collect(Collectors.toMap(
			                        Map.Entry::getKey,
			                        entry -> entry.getValue().getInactiveDate()
			                    ))
			            );

			            return new CardHolderDto(
			                holder.getUuid(),
			                holder.getFirstName(),
			                holder.getLastName(),
			                holder.getCompany(),
			                cards.getDto(), 
			                holder.getPermissions()
			            );
			        })
			        .collect(Collectors.toList()); 
		} else return clientRepository
				.findAll().stream().filter(holder -> (holder.getLastName() == lastName))						
				.map(holder -> {
		            CardRecieveDto cards = new CardRecieveDto(
		                holder.getCards().entrySet().stream()
		                    .collect(Collectors.toMap(
		                        Map.Entry::getKey,
		                        entry -> entry.getValue().getInactiveDate()
		                    ))
		            );

		            return new CardHolderDto(
		                holder.getUuid(),
		                holder.getFirstName(),
		                holder.getLastName(),
		                holder.getCompany(),
		                cards.getDto(), 
		                holder.getPermissions()
		            );
		        })
		        .collect(Collectors.toList()); 
	}

	@Override
	public CardHolderDto getCardHolderByCard(int cardNumber) {
		CardHolder cardHolder = clientRepository.findAll().stream()
			    .filter(holder -> holder.getCards().containsKey(cardNumber))
			    .findFirst()
			    .orElseThrow(() -> new CardHolderNotFoundExeption());
		CardRecieveDto cards = new CardRecieveDto(cardHolder.getCards().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getInactiveDate())));
		 return new CardHolderDto(
	                cardHolder.getUuid(),
	                cardHolder.getFirstName(),
	                cardHolder.getLastName(),
	                cardHolder.getCompany(),
	                cards.getDto(), 
	                cardHolder.getPermissions());
	}
	
	@Override
	public List<CardHolderDto> cardHolders() {
	    return clientRepository.findAll().stream()
	        .map(holder -> {
	            CardRecieveDto cards = new CardRecieveDto(
	                holder.getCards().entrySet().stream()
	                    .collect(Collectors.toMap(
	                        Map.Entry::getKey,
	                        entry -> entry.getValue().getInactiveDate()
	                    ))
	            );

	            return new CardHolderDto(
	                holder.getUuid(),
	                holder.getFirstName(),
	                holder.getLastName(),
	                holder.getCompany(),
	                cards.getDto(), 
	                holder.getPermissions()
	            );
	        })
	        .collect(Collectors.toList()); 
	}

	@Override
	public Boolean addCardHolder(CardHolderDto cardHolderDto) {
		if (clientRepository.findById(cardHolderDto.getUuid()).isPresent()) {
			return false;
		}
		System.out.println((cardHolderDto));
		CardHolder cardHolder = new CardHolder(cardHolderDto.getUuid(), cardHolderDto.getFirstName(), cardHolderDto.getLastName(), cardHolderDto.getCompany());
		cardHolder.setCards(cardHolderDto.getCards().entrySet()
					.stream()
					.collect(Collectors.toMap(Map.Entry::getKey,  
                entry -> new Card(entry.getKey(),true,entry.getValue()))));
		cardHolder.setPermissions(cardHolderDto.getPermissions());
		
		System.out.println(cardHolder);
                
		clientRepository.save(cardHolder);
		return true;
	}

	@Override
	public Boolean changeCardHolder(CardHolderDto cardHolderDto) {
		if (clientRepository.existsById(cardHolderDto.getUuid())) {
			CardHolder cardHolder = new CardHolder(cardHolderDto.getUuid(), cardHolderDto.getFirstName(),
					cardHolderDto.getLastName(), cardHolderDto.getCompany());
			cardHolder.setCards(cardHolderDto.getCards().entrySet().stream().collect(
					Collectors.toMap(Map.Entry::getKey, entry -> new Card(entry.getKey(), true, entry.getValue()))));
			cardHolder.setPermissions(cardHolderDto.getPermissions());

			System.out.println(cardHolder);

			clientRepository.save(cardHolder);
			return true;
		} else
			throw new CardHolderNotFoundExeption();
	}

	@Override
	public CardHolderDto remooveCardHolder(UUID uuid) {
		CardHolder cardHolder = clientRepository.findById(uuid).orElseThrow(() -> new CardHolderNotFoundExeption());
		clientRepository.delete(cardHolder);
		return new CardHolderDto(cardHolder.getUuid(), cardHolder.getFirstName(), cardHolder.getLastName(), cardHolder.getCompany(), null, null);
	
	}

}
