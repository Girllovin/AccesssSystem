package smallITgroup.client.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import smallITgroup.client.model.CardHolder;
import smallITgroup.client.service.ClientService;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {
	Map<UUID, CardHolder> cardholders = new ConcurrentHashMap<>( ); 

	@Override
	public Optional<CardHolder> findCardHolderById(UUID uuid) {
		return Optional.ofNullable(cardholders.get(uuid));
	}

	@Override
	public Optional<CardHolder> findCardHolderByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<CardHolder> findCardHolderByCard(Integer cardNumber) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<CardHolder> cardHoldersList() {		
		return cardholders.values().stream().toList();
	}

	@Override
	public boolean addCardHolder(CardHolder cardHolder) {	
		try {
			cardholders.put( cardHolder.getUuid(), cardHolder);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean changeCardHolder(CardHolder cardHolder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CardHolder cardHolderRemove(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
