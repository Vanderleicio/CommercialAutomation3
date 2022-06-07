package app.daos;

import java.util.ArrayList;

import app.exceptions.EntitiesNotRegistred;
import app.exceptions.IdDoesntExist;
import app.models.Product;

public class ProductDAO{
	private static ArrayList<Product> arrayProducts = new ArrayList<Product>();
	
	public static void addProduct(Product product) {
		arrayProducts.add(product);
	}
	
	public static ArrayList<Product> listProduct() {
		return ProductDAO.arrayProducts;
	}
	
	public static void delProduct(Product product) {
		arrayProducts.remove(product);
	}
	
	public Product searchById(String id) throws IdDoesntExist, EntitiesNotRegistred {
		
		if (arrayProducts.size() == 0) {
			throw new EntitiesNotRegistred();
		}
		
		for (int i = 0; i < arrayProducts.size(); i++) {
			String currentProduct = (arrayProducts.get(i)).getId();
			if (id.equals(currentProduct)) {
				return arrayProducts.get(i);
			}
		}
		throw new IdDoesntExist();
	}
}
