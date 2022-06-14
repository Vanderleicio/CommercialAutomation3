package app.model.daos;

import java.util.ArrayList;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Item;

public class ItemDAO extends AbstractDAO {
	
	//to vendo pra trazer o hashmap pra ca
	
	public Item getOneItem(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Item) this.searchEntities(id);
	}
	
	public ArrayList <Item> getItemList(){
		ArrayList<Item> itemList = castList(this.getList());
		return itemList;
	}

}
