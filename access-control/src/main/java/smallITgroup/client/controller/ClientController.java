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

@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments (final fields)
@CrossOrigin(origins = "http://localhost:3000") // Allowing cross-origin requests from the frontend running on localhost:3000
@RestController // Indicates that this class is a REST controller (handles HTTP requests)
public class ClientController {

    // Injecting the clientService to handle the logic for client-related operations
    final ClientService clientService;

    // Endpoint to get a CardHolder by UUID (unique identifier)
    @GetMapping("/cardholder/get/{uuid}")
    public CardHolderDto getCardHolderById(@PathVariable UUID uuid) {
        return clientService.getCardHolderById(uuid); // Calls service method to get the CardHolder data
    }

    // Endpoint to get CardHolders by their first and last names
    @GetMapping("/cardholder/getbyname/")
    public List<CardHolderDto> getCardHolderByName(@RequestParam String firstName, String lastName) {
        if(firstName == null) firstName =" "; // If firstName is null, set it to an empty string to avoid errors
        if(lastName == null) lastName =" "; // If lastName is null, set it to an empty string to avoid errors
        return clientService.getCardHolderByName(firstName, lastName); // Calls service method to fetch CardHolders by name
    }

    // Endpoint to get CardHolder by card number
    @GetMapping("/cardholder/getbycard/{cardNumber}")
    public CardHolderDto getCardHolderByCard(@PathVariable Integer cardNumber) {
        return clientService.getCardHolderByCard(cardNumber); // Calls service method to get CardHolder by card number
    }

    // Endpoint to get a list of all CardHolders
    @GetMapping("/cardholderlist")
    public List<CardHolderDto> cardHolders() {
        return clientService.cardHolders(); // Calls service method to retrieve all CardHolders
    }

    // Endpoint to add a new CardHolder
    @PostMapping("/cardholder/new")
    public boolean addCardHolder(@RequestBody CardHolderDto cardHolder) {
        return clientService.addCardHolder(cardHolder); // Calls service method to add the new CardHolder
    }

    // Endpoint to update an existing CardHolder
    @PutMapping("/cardholder/update")
    public boolean changeCardHolder(@RequestBody CardHolderDto cardHolder) {
        return clientService.changeCardHolder(cardHolder); // Calls service method to update the CardHolder
    }

    // Endpoint to remove a CardHolder by UUID
    @DeleteMapping("/cardholder/remove/{uuid}")
    public CardHolderDto remooveCardHolder(@PathVariable UUID uuid) {
        return clientService.remooveCardHolder(uuid); // Calls service method to remove the CardHolder by UUID
    }
}
