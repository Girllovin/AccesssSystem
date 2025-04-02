package smallITgroup.client.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class CardHolder {
	int Counter;
	UUID uuid;
	String firstName;
	String lastName;
	String company;
	Map<Integer, Card> cards;
	HashSet<String> permissions;
	public CardHolder(UUID uuid, String firstName, String lastName, String company) {
		super();
		this.Counter = 0;
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		Map<Integer, Card> cards = new HashMap<Integer, Card>();
		HashSet<String> permissions = new HashSet<String>();
	}
	
	public boolean addCard(Card card) {
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
