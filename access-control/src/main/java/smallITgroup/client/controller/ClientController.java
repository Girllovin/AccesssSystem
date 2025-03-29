package smallITgroup.client.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.service.ClientService;

public class ClientController {
	
	ClientService clientService;

	@GetMapping("/cardholder/get/{uuid}")
	public CardHolderDto geCardHolderById(@PathVariable UUID uuid) {
		return clientService.geCardHolderById(uuid);
	} 

	@GetMapping("/cardholder/getbyname/{firstName}/{lastName}")
	public CardHolderDto geCardHolderByName(@PathVariable String firstName, String lastName) {
		return clientService.geCardHolderByName(firstName, lastName);
	}

	@GetMapping("/cardholder/getbycard/{cardNumber}")
	public CardHolderDto geCardHolderByCard(@PathVariable int cardNumber) {
		return clientService.geCardHolderByCard(cardNumber);
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
