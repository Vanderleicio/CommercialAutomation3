package app.model.facades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.ItemDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class MenuFacade {
	
	private static ItemDAO itemData = new ItemDAO();
	
	public static void createItem(String name, String description, BigDecimal price, String category, HashMap<String, Integer> composition) {
		Item newItem = new Item(name, description, price, category, composition);
		itemData.add(newItem);
	}
	
	public static void editItem(String id, String newName, String newDescription, BigDecimal newPrice, String newCategory, HashMap<String, Integer> composition) throws IdDoesntExist, EntitiesNotRegistred {
		Item itemEdit = itemData.getOneItem(id);
		
		itemEdit.setName(newName);
		itemEdit.setDescription(newDescription);
		itemEdit.setCategoryItems(newCategory);
		itemEdit.setPrice(newPrice);
		itemEdit.setComposition(composition);
	}

	public void addProductsItems(String idPEdit, Product productPAdd, int quantity) throws IdDoesntExist, EntitiesNotRegistred{
		
		Item itemPEdit;
		itemPEdit = (Item) itemData.searchEntities(idPEdit);
		itemPEdit.addProduct(quantity, productPAdd.getName());
	}
	
	public void removeProductFromItem(String idPEdit, Product produtoPRemover) throws IdDoesntExist, EntitiesNotRegistred {

		Item itemPEdit = (Item) itemData.searchEntities(idPEdit);
		itemPEdit.deleteProduct(produtoPRemover.getName());
		int size = itemPEdit.getCompositionSize();
		if (size == 0) {
			itemData.delete(idPEdit);
		}
	}
	
	public static void chooseAItem(String id){
		itemData.setChosenEntityId(id);
	}
	
	public static String chosenItem() {
		return itemData.getChosenEntityId();
	}
}
