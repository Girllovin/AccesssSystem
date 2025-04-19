package smallITgroup.client.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.client.dao.ClientRepository;
import smallITgroup.client.dao.exeptions.CardHolderNotFoundExeption;
import smallITgroup.client.dao.exeptions.NameNotCorrectExeption;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.dto.CardRecieveDto;
import smallITgroup.client.model.Card;
import smallITgroup.client.model.CardHolder;

@Service // Marks this class as a Spring Service component
@RequiredArgsConstructor // Automatically injects required dependencies via constructor
public class ClientServiceImpl implements ClientService {

    final ClientRepository clientRepository; // Injecting the ClientRepository for database operations

    // Get a CardHolder by UUID
    @Override
    public CardHolderDto getCardHolderById(UUID uuid) {
        // Fetch the CardHolder from the repository or throw exception if not found
        CardHolder cardHolder = clientRepository.findById(uuid).orElseThrow(() -> new CardHolderNotFoundExeption());

        // Mapping CardHolder's card information to CardRecieveDto
        CardRecieveDto cards = new CardRecieveDto(cardHolder.getCards().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getInactiveDate())));

        // Return a CardHolderDto with necessary details
        return new CardHolderDto(cardHolder.getUuid(), cardHolder.getFirstName(), cardHolder.getLastName(),
                cardHolder.getCompany(), cards.getDto(), cardHolder.getPermissions());
    }

    // Get a list of CardHolders by their first and last names
    @Override
    public List<CardHolderDto> getCardHolderByName(String firstName, String lastName) {
        // Validate that at least one of the names is provided
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            throw new NameNotCorrectExeption(); // Throw exception if no valid name is provided
        }

        List<CardHolder> matchedCardHolders;

        // Find cardholders based on first and last name
        if (firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank()) {
            matchedCardHolders = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null && !firstName.isBlank()) {
            matchedCardHolders = clientRepository.findByFirstNameIgnoreCase(firstName);
        } else {
            matchedCardHolders = clientRepository.findByLastNameIgnoreCase(lastName);
        }

        // Map CardHolders to CardHolderDto and return as list
        return matchedCardHolders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get a CardHolder by card number
    @Override
    public CardHolderDto getCardHolderByCard(int cardNumber) {
        // Find the first cardholder that contains the specified card number
        CardHolder cardHolder = clientRepository.findAll().stream()
                .filter(holder -> holder.getCards().containsKey(cardNumber))
                .findFirst()
                .orElseThrow(() -> new CardHolderNotFoundExeption());

        // Return the found CardHolder as CardHolderDto
        return convertToDto(cardHolder);
    }

    // Get a list of all CardHolders
    @Override
    public List<CardHolderDto> cardHolders() {
        // Map all CardHolders to CardHolderDto and return as list
        return clientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Add a new CardHolder
    @Override
    public Boolean addCardHolder(CardHolderDto cardHolderDto) {
        // Check if a CardHolder with the same UUID already exists
        if (clientRepository.findById(cardHolderDto.getUuid()).isPresent()) {
            return false; // Return false if a CardHolder already exists with this UUID
        }

        // Create a new CardHolder and map data from the DTO
        CardHolder cardHolder = new CardHolder(cardHolderDto.getUuid(), cardHolderDto.getFirstName(),
                cardHolderDto.getLastName(), cardHolderDto.getCompany());
        cardHolder.setCards(cardHolderDto.getCards().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> new Card(entry.getKey(), true, entry.getValue()))));
        cardHolder.setPermissions(cardHolderDto.getPermissions());

        // Save the new CardHolder in the repository
        clientRepository.save(cardHolder);
        return true;
    }

    // Change an existing CardHolder's data
    @Override
    public Boolean changeCardHolder(CardHolderDto cardHolderDto) {
        // Check if a CardHolder with the given UUID exists
        if (clientRepository.existsById(cardHolderDto.getUuid())) {
            // Update the CardHolder with new details
            CardHolder cardHolder = new CardHolder(cardHolderDto.getUuid(), cardHolderDto.getFirstName(),
                    cardHolderDto.getLastName(), cardHolderDto.getCompany());
            cardHolder.setCards(cardHolderDto.getCards().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> new Card(entry.getKey(), true, entry.getValue()))));
            cardHolder.setPermissions(cardHolderDto.getPermissions());

            // Save the updated CardHolder
            clientRepository.save(cardHolder);
            return true;
        } else {
            throw new CardHolderNotFoundExeption(); // Throw exception if CardHolder not found
        }
    }

    // Remove a CardHolder by UUID
    @Override
    public CardHolderDto remooveCardHolder(UUID uuid) {
        // Fetch the CardHolder and delete it from the repository
        CardHolder cardHolder = clientRepository.findById(uuid).orElseThrow(() -> new CardHolderNotFoundExeption());
        clientRepository.delete(cardHolder);

        // Return the removed CardHolder as CardHolderDto
        return convertToDto(cardHolder);
    }

    // Convert a CardHolder object to CardHolderDto
    private CardHolderDto convertToDto(CardHolder holder) {
        // Map CardHolder's card information to CardRecieveDto
        CardRecieveDto cards = new CardRecieveDto(
            holder.getCards().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getInactiveDate()))
        );

        // Return a new CardHolderDto with the mapped data
        return new CardHolderDto(
            holder.getUuid(),
            holder.getFirstName(),
            holder.getLastName(),
            holder.getCompany(),
            cards.getDto(),
            holder.getPermissions()
        );
    }
}