package smallITgroup.client.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	ClientService clientService;

	@GetMapping("/cardholder/get/{uuid}")
	public CardHolderDto getCardHolderById(@PathVariable UUID uuid) {
		return clientService.getCardHolderById(uuid);
	} 

	@GetMapping("/cardholder/getbyname/{firstName}/{lastName}")
	public CardHolderDto getCardHolderByName(@PathVariable String firstName, String lastName) {
		return clientService.getCardHolderByName(firstName, lastName);
	}

	@GetMapping("/cardholder/getbycard/{cardNumber}")
	public CardHolderDto getCardHolderByCard(@PathVariable int cardNumber) {
		return clientService.getCardHolderByCard(cardNumber);
	}
	
	@GetMapping("/cardholderlist")
	public List<CardHolderDto> cardHolders() {
		return clientService.cardHolders();
	}

	@PostMapping("/cardholder/new")
	public boolean addCardHolder(@RequestBody CardHolderDto cardHolder) {
		return clientService.addCardHolder(cardHolder);
	}

	@PutMapping("/cardholder/update")
	public boolean changeCardHolder(@RequestBody CardHolderDto cardHolder) {
		return clientService.changeCardHolder(cardHolder);
	}

	@DeleteMapping("/cardholder/remove")
	public CardHolderDto remooveCardHolder(@RequestBody UUID uuid) {
		return clientService.remooveCardHolder(uuid);
	}

}
