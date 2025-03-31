package smallITgroup.client.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "UUID")
public class CardHolder {
	int Counter;
	String UUID;
	String firstName;
	String lastName;
	String company;
	Map<Integer, Card> cards;
	ArrayList<String> permissions;
	public CardHolder(String uUID, String firstName, String lastName, String company) {
		super();
		this.Counter = 0;
		UUID = uUID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		Map<Integer, Card> cards = new HashMap<Integer, Card>();
		Set<String> permissions = new HashSet<String>();
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
