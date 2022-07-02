package app.model.facades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.SaleDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class SaleFacade {

	private static SaleDAO saleData = new SaleDAO();
	
	public static void createSale(LocalDate day, LocalTime hour, String paymentMethod, ArrayList<Item> itemPurchased, Client client) throws EmptyStringException {
		if (paymentMethod.equals("")){
			throw new EmptyStringException();
		}
		Sale newSale = new Sale(day, hour, paymentMethod, itemPurchased, client);
		saleData.add(newSale);
	}

	public static void editSale(String id, LocalDate newDay, LocalTime newHour, String newPaymentMethod, ArrayList<Item> newItems, Client newClient) throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		if (newPaymentMethod.equals("")){
			throw new EmptyStringException();
		}
		Sale saleEdit = saleData.getOneSale(id);
		
		saleEdit.setDay(newDay);
		saleEdit.setHour(newHour);
		saleEdit.setPaymentMethod(newPaymentMethod);
		saleEdit.setItemsPurchased(newItems);
		saleEdit.setClient(newClient);
	}
	
	public static void delSale(String id) throws IdDoesntExist, EntitiesNotRegistred{
		saleData.delete(id);
	}
	
	public static void addItem(String idPEdit, Item itemPAdd) throws IdDoesntExist, EntitiesNotRegistred {
		Sale saleEdit = (Sale) saleData.searchEntities(idPEdit);
		saleEdit.addItem(itemPAdd);
	}
	
	public static void deleteItem(String idPEditar, Item itemPDeletar) throws IdDoesntExist, EntitiesNotRegistred {
		Sale saleEdit = (Sale) saleData.searchEntities(idPEditar);
		int size = saleEdit.deleteItem(itemPDeletar);
		if (size == 0) {
			saleData.delete(idPEditar);
		}
	}
	
	public static ArrayList<Item> getSaleItems(String saleId){
		Sale sale;
		ArrayList<Item> comp = new ArrayList<Item>();
		try {
			sale = (Sale) saleData.searchEntities(saleId);
			comp = sale.getItemsPurchased();
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comp;
	}
	
	public static HashMap<String, Integer> getAllProductsUsed(ArrayList<Item> itemsPurchased){
		HashMap<String, Integer> allProducts = new HashMap<String, Integer>();
		Item item;
		
		for (int i = 0; i < itemsPurchased.size(); i++) {
			item = itemsPurchased.get(i);
			HashMap<String, Integer> composition = item.getComposition();
			
			for (HashMap.Entry<String,Integer> idQuant : composition.entrySet()) {
				String prodId = idQuant.getKey();
				int qnt = idQuant.getValue();
				if (allProducts.containsKey(prodId)) {
					int currentQnt = allProducts.get(prodId);
					allProducts.replace(prodId, currentQnt + qnt);
				} else {
					allProducts.put(prodId, qnt);
				}
			}
		}
		return allProducts;
	}
	
	
	public static void chooseASale(String id){
		saleData.setChosenEntityId(id);
	}
	
	public static Sale chosenSale() {
		try {
			String id = saleData.getChosenEntityId();
			return saleData.getOneSale(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			return null;
		}
	}
	
	public static ArrayList<Sale> listSale(){
		return saleData.getSaleList();
	}
}
