package app.model.facades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import app.model.daos.SaleDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class SaleFacade {

	private static SaleDAO saleData = new SaleDAO();
	
	public static void createSale(LocalDate day, LocalTime hour, String paymentMethod, ArrayList<Item> itemPurchased, String clientId) {
		Sale newSale = new Sale(day, hour, paymentMethod, itemPurchased, clientId);
		saleData.add(newSale);
	}

	public static void editSale(String id, LocalDate newDay, LocalTime newHour, String newPaymentMethod, String newClientId) throws IdDoesntExist, EntitiesNotRegistred {
		Sale saleEdit = saleData.getOneSale(id);
		
		saleEdit.setDay(newDay);
		saleEdit.setHour(newHour);
		saleEdit.setPaymentMethod(newPaymentMethod);
		saleEdit.setClientId(newClientId);
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
	
	public static void chooseASale(String id){
		saleData.setChosenEntityId(id);
	}
	
	public static String chosenSale() {
		return saleData.getChosenEntityId();
	}

}
