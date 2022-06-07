package app.daos;

import java.util.ArrayList;

import app.exceptions.EntitiesNotRegistred;
import app.exceptions.IdDoesntExist;
import app.models.Item;

public class ItemDAO{
	private static ArrayList<Item> arrayItems = new ArrayList<Item>();
	
	public static void addItem(Item item) {
		arrayItems.add(item);
	}
	
	public static ArrayList<Item> listItems() {
		return ItemDAO.arrayItems;
	}
	
	public static void delItem(Item item) {
		arrayItems.remove(item);
	}
	
	public Item searchById(String id) throws IdDoesntExist, EntitiesNotRegistred {
		
		if (arrayItems.size() == 0) {
			throw new EntitiesNotRegistred();
		}
		
		for (int i = 0; i < arrayItems.size(); i++) {
			String currentItem = (arrayItems.get(i)).getId();
			if (id.equals(currentItem)) {
				return arrayItems.get(i);
			}
		}
		throw new IdDoesntExist();
	}
	
	
}
