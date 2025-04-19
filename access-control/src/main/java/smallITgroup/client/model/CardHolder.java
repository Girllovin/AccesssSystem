package smallITgroup.client.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

import org.springframework.data.annotation.Id;

@ToString // Lombok annotation to generate toString() method
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
@Getter             // Lombok annotation to generate getters for all fields
@Setter             // Lombok annotation to generate setters for all fields
@EqualsAndHashCode(of = "uuid") // Equality based on uuid field
public class CardHolder {	

	@Id // Marks this field as the ID in MongoDB
	UUID uuid; // Unique identifier for the card holder
	
	String firstName; // First name of the card holder
	String lastName;  // Last name of the card holder
	String company;   // Company name the card holder is associated with
	
	Map<Integer, Card> cards; // Map of card ID to Card object
	HashSet<String> permissions; // Set of permissions associated with the card holder

	// Constructor initializing uuid, name, company, and empty collections
	public CardHolder(UUID uuid, String firstName, String lastName, String company) {
		super();		
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;

		// These local variables shadow the instance variables and will not initialize them properly
		Map<Integer, Card> cards = new HashMap<Integer, Card>();
		HashSet<String> permissions = new HashSet<String>();
	}

	// Adds a new card if it doesn't already exist in the map
	public Boolean addCard(Card card) {
		try {
			if (!cards.containsKey(card.cardId)) {
				this.cards.put(card.cardId, card);
				return true;
			} else {
				System.out.println("Enter original card number!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	// Adds a permission string to the set
	public boolean addPermission(String permission) {
		try {
			this.permissions.add(permission);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
}

