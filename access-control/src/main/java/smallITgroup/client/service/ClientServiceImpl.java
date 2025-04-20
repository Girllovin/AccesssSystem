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

import lombok.extern.slf4j.Slf4j;

@Slf4j // Enables SLF4J logging via Lombok
@Service // Marks this class as a Spring-managed service
@RequiredArgsConstructor // Automatically generates a constructor for all final fields
public class ClientServiceImpl implements ClientService {

    final ClientRepository clientRepository; // Repository for performing database operations on CardHolders

    // Retrieve a CardHolder by UUID
    @Override
    public CardHolderDto getCardHolderById(UUID uuid) {
        log.info("Fetching CardHolder by UUID: {}", uuid);

        // Attempt to retrieve the CardHolder or throw an exception if not found
        CardHolder cardHolder = clientRepository.findById(uuid)
                .orElseThrow(() -> {
                    log.warn("CardHolder not found with UUID: {}", uuid);
                    return new CardHolderNotFoundExeption();
                });

        log.debug("CardHolder found: {}", cardHolder);

        // Map the card information to a DTO with expiration dates
        CardRecieveDto cards = new CardRecieveDto(
                cardHolder.getCards().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getInactiveDate()
                        ))
        );

        // Return the cardholder details as a DTO
        return new CardHolderDto(
                cardHolder.getUuid(),
                cardHolder.getFirstName(),
                cardHolder.getLastName(),
                cardHolder.getCompany(),
                cards.getDto(),
                cardHolder.getPermissions()
        );
    }

    // Retrieve cardholders by first name and/or last name
    @Override
    public List<CardHolderDto> getCardHolderByName(String firstName, String lastName) {
        log.info("Searching CardHolders by firstName: '{}' and lastName: '{}'", firstName, lastName);

        // Ensure at least one of the name parameters is provided
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            log.warn("Both firstName and lastName are null or blank");
            throw new NameNotCorrectExeption();
        }

        List<CardHolder> matchedCardHolders;

        // Perform different search strategies depending on which name fields are present
        if (firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank()) {
            matchedCardHolders = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null && !firstName.isBlank()) {
            matchedCardHolders = clientRepository.findByFirstNameIgnoreCase(firstName);
        } else {
            matchedCardHolders = clientRepository.findByLastNameIgnoreCase(lastName);
        }

        log.debug("Found {} CardHolder(s)", matchedCardHolders.size());

        // Map the results to DTOs
        return matchedCardHolders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Retrieve a CardHolder by card number
    @Override
    public CardHolderDto getCardHolderByCard(int cardNumber) {
        log.info("Searching CardHolder by card number: {}", cardNumber);

        // Find the cardholder whose cards include the specified card number
        CardHolder cardHolder = clientRepository.findAll().stream()
                .filter(holder -> holder.getCards().containsKey(cardNumber))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("CardHolder not found with card number: {}", cardNumber);
                    return new CardHolderNotFoundExeption();
                });

        log.debug("CardHolder found for card number {}: {}", cardNumber, cardHolder.getUuid());

        return convertToDto(cardHolder);
    }

    // Retrieve all cardholders
    @Override
    public List<CardHolderDto> cardHolders() {
        log.info("Retrieving all CardHolders");

        return clientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Add a new CardHolder to the system
    @Override
    public Boolean addCardHolder(CardHolderDto cardHolderDto) {
        log.info("Attempting to add new CardHolder: {}", cardHolderDto.getUuid());

        // Prevent duplicate entries based on UUID
        if (clientRepository.findById(cardHolderDto.getUuid()).isPresent()) {
            log.warn("CardHolder already exists with UUID: {}", cardHolderDto.getUuid());
            return false;
        }

        // Map DTO to entity
        CardHolder cardHolder = new CardHolder(
                cardHolderDto.getUuid(),
                cardHolderDto.getFirstName(),
                cardHolderDto.getLastName(),
                cardHolderDto.getCompany()
        );

        // Map cards with expiration info
        cardHolder.setCards(
                cardHolderDto.getCards().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> new Card(entry.getKey(), true, entry.getValue())
                        ))
        );

        cardHolder.setPermissions(cardHolderDto.getPermissions());

        // Save the new cardholder
        clientRepository.save(cardHolder);

        log.info("CardHolder successfully added: {}", cardHolder.getUuid());
        return true;
    }

    // Update an existing CardHolder's data
    @Override
    public Boolean changeCardHolder(CardHolderDto cardHolderDto) {
        log.info("Attempting to update CardHolder: {}", cardHolderDto.getUuid());

        if (clientRepository.existsById(cardHolderDto.getUuid())) {
            CardHolder cardHolder = new CardHolder(
                    cardHolderDto.getUuid(),
                    cardHolderDto.getFirstName(),
                    cardHolderDto.getLastName(),
                    cardHolderDto.getCompany()
            );

            // Update cards
            cardHolder.setCards(
                    cardHolderDto.getCards().entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    entry -> new Card(entry.getKey(), true, entry.getValue())
                            ))
            );

            cardHolder.setPermissions(cardHolderDto.getPermissions());

            // Save updated cardholder
            clientRepository.save(cardHolder);

            log.info("CardHolder updated successfully: {}", cardHolder.getUuid());
            return true;
        } else {
            log.warn("Cannot update: CardHolder not found with UUID: {}", cardHolderDto.getUuid());
            throw new CardHolderNotFoundExeption();
        }
    }

    // Remove a CardHolder by UUID and return its DTO
    @Override
    public CardHolderDto remooveCardHolder(UUID uuid) {
        log.info("Attempting to remove CardHolder: {}", uuid);

        CardHolder cardHolder = clientRepository.findById(uuid)
                .orElseThrow(() -> {
                    log.warn("CardHolder not found for removal with UUID: {}", uuid);
                    return new CardHolderNotFoundExeption();
                });

        clientRepository.delete(cardHolder);

        log.info("CardHolder successfully removed: {}", uuid);
        return convertToDto(cardHolder);
    }

    // Convert a CardHolder entity to a DTO representation
    private CardHolderDto convertToDto(CardHolder holder) {
        log.debug("Converting CardHolder to DTO: {}", holder.getUuid());

        // Extract card expiration info into a DTO
        CardRecieveDto cards = new CardRecieveDto(
                holder.getCards().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getInactiveDate()
                        ))
        );

        // Build and return the full CardHolder DTO
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