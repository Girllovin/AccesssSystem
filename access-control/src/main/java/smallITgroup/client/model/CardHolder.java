package smallITgroup.client.model;

import java.time.LocalDate;
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

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class CardHolder {	
	@Id
	UUID uuid;
	String firstName;
	String lastName;
	String company;
	Map<Integer, Card> cards;
	HashSet<String> permissions;
	public CardHolder(UUID uuid, String firstName, String lastName, String company) {
		super();		
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		Map<Integer, Card> cards = new HashMap<Integer, Card>();
		HashSet<String> permissions = new HashSet<String>();
	}
	
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
