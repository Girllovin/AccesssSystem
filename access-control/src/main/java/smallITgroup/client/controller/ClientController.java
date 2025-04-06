package smallITgroup.client.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.service.ClientService;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController


public class ClientController {
	
	final ClientService clientService;

	@GetMapping("/cardholder/get/{uuid}")
	public CardHolderDto getCardHolderById(@PathVariable UUID uuid) {
		return clientService.getCardHolderById(uuid);
	} 

	@GetMapping("/cardholder/getbyname/")
	public List<CardHolderDto> getCardHolderByName(@RequestParam String firstName, String lastName) {
		if(firstName.equals(null)) firstName =" ";
		if(lastName.equals(null)) lastName =" ";
		return clientService.getCardHolderByName(firstName, lastName);
	}

	@GetMapping("/cardholder/getbycard/{cardNumber}")
	public CardHolderDto getCardHolderByCard(@PathVariable Integer cardNumber) {
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

	@DeleteMapping("/cardholder/remove/{uuid}")
	public CardHolderDto remooveCardHolder(@PathVariable UUID uuid) {
		return clientService.remooveCardHolder(uuid);
	}

}
