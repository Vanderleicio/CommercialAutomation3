package app.daos;

import java.util.ArrayList;

import app.exceptions.EntitiesNotRegistred;
import app.exceptions.IdDoesntExist;
import app.models.Sale;

public class SaleDAO{
	private static ArrayList<Sale> arraySales = new ArrayList<Sale>();
	
	public static void addSale(Sale user) {
		arraySales.add(user);
	}
	
	public static ArrayList<Sale> listSale() {
		return SaleDAO.arraySales;
	}
	
	public static void delSale(Sale user) {
		arraySales.remove(user);
	}
	
	public Sale searchById(String id) throws IdDoesntExist, EntitiesNotRegistred {
		
		if (arraySales.size() == 0) {
			throw new EntitiesNotRegistred();
		}
		
		for (int i = 0; i < arraySales.size(); i++) {
			String currentSale = (arraySales.get(i)).getId();
			if (id.equals(currentSale)) {
				return arraySales.get(i);
			}
		}
		throw new IdDoesntExist();
	}
	
}
