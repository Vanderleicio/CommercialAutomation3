package app.model.facades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.ItemDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class MenuFacade {
	
	private static ItemDAO itemData = new ItemDAO();
	
	public static void createItem(String name, String description, BigDecimal price, String category, HashMap<String, Integer> composition) throws EmptyStringException {
		if (name.equals("") | description.equals("") | category.equals("")){
			throw new EmptyStringException();
		}
		Item newItem = new Item(name, description, price, category, composition);
		itemData.add(newItem);
	}
	
	public static void editItem(String id, String newName, String newDescription, BigDecimal newPrice, String newCategory, HashMap<String, Integer> newComposition) throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		if (newName.equals("") | newDescription.equals("") | newCategory.equals("")){
			throw new EmptyStringException();
		}
		
		Item itemEdit = itemData.getOneItem(id);
		
		itemEdit.setName(newName);
		itemEdit.setDescription(newDescription);
		itemEdit.setCategoryItems(newCategory);
		itemEdit.setPrice(newPrice);
		itemEdit.setComposition(newComposition);
	}
	
	public static void delItem(String id) throws IdDoesntExist, EntitiesNotRegistred{
		itemData.delete(id);
	}
	
	public static HashMap<String, Integer> doNewComposition(ArrayList<Product> prods, ArrayList<Integer> qntt) throws InvalidQuantityException{
		HashMap<String, Integer> newComp = new HashMap<String, Integer>();
		
		for (int i = 0; i < prods.size(); i++) {
			if (qntt.get(i) > 0) {
				newComp.put(prods.get(i).getId(), qntt.get(i));
			} else {
				throw new InvalidQuantityException();
			}
		}
		
		return newComp;
	}

	public static void addProductsItems(String idPEdit, Product productPAdd, int quantity) throws IdDoesntExist, EntitiesNotRegistred{
		Item itemPEdit;
		itemPEdit = (Item) itemData.searchEntities(idPEdit);
		itemPEdit.addProduct(quantity, productPAdd.getId());
	}
	
	public static void removeProductFromItem(String idPEdit, Product produtoPRemover) throws IdDoesntExist, EntitiesNotRegistred {

		Item itemPEdit = (Item) itemData.searchEntities(idPEdit);
		itemPEdit.deleteProduct(produtoPRemover.getId());
		int size = itemPEdit.getCompositionSize();
		if (size == 0) {
			itemData.delete(idPEdit);
		}
	}
	
	public static void editProdQnt(String idItem, String idProd, int newQnt) throws InvalidQuantityException, IdDoesntExist, EntitiesNotRegistred {
		if (newQnt > 0) {
			Item item = (Item) itemData.searchEntities(idItem);
			item.getComposition().replace(idProd, newQnt);
		} else {
			throw new InvalidQuantityException();
		}
	}
	
	public static ArrayList<Product> getItemProds(String itemId){
		Item item;
		ArrayList<Product> comp = new ArrayList<Product>();
		try {
			item = (Item) itemData.searchEntities(itemId);
			for (HashMap.Entry<String,Integer> idQuant : item.getComposition().entrySet()) {
				ProductFacade.chooseAProduct(idQuant.getKey());
				Product product = ProductFacade.chosenProduct();
				comp.add(product);
			};
			
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comp;
	}
	
	
	public static ArrayList<Integer> getItemQntt(String itemId){
		Item item;
		ArrayList<Integer> compQntt = new ArrayList<Integer>();
		
		try {
			item = (Item) itemData.searchEntities(itemId);
			for (HashMap.Entry<String,Integer> idQuant : item.getComposition().entrySet()) {
				compQntt.add(idQuant.getValue());
			};
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compQntt;
	}
	
	public static ArrayList<Item> listItem(){
		return itemData.getItemList();
	}
	
	public static void chooseAItem(String id){
		itemData.setChosenEntityId(id);
	}
	
	public static Item chosenItem() {
		try {
			String id = itemData.getChosenEntityId();
			return itemData.getOneItem(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			return null;
		}
		
	}
}
