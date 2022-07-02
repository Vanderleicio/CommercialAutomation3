package app.model.facades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.ProductDAO;
import app.model.exceptions.*;
import app.model.models.Product;
import app.model.models.Provider;

public class ProductFacade {

	private static ProductDAO prodData = new ProductDAO();
	
	public static void createProduct(String name, BigDecimal price, LocalDate validity, int quantity, Provider provider) throws InvalidDateException, InvalidQuantityException, EmptyStringException {
		if (name.equals("")){
			throw new EmptyStringException();
		}
		if (validity.isAfter(LocalDate.now()) && (quantity > 0)) {
			Product newProduct = new Product(name, price, validity, quantity, provider);
			prodData.add(newProduct);
		} else if (quantity <= 0){
			throw new InvalidQuantityException();
		} else {
			throw new InvalidDateException();
		}
	}
	
	public static void delProduct(String id) throws IdDoesntExist, EntitiesNotRegistred{
		prodData.delete(id);
	}
	
	public static void editProduct(String id, String newName, BigDecimal newPrice, LocalDate newValidity, int newQuantity, Provider newProvider) throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException{
		if (newName.equals("")){
			throw new EmptyStringException();
		}
		Product productEdit = prodData.getOneProduct(id);
		
		productEdit.setName(newName);
		productEdit.setPrice(newPrice);
		productEdit.setValidity(newValidity);
		productEdit.setQuantity(newQuantity);
		productEdit.setProvider(newProvider);
	}
	
	
	/**
	 * Metodo responsavel por atualizar estoque
	 * 
	 * @param groupsUsed: lista contendo os produtos
	 * 
	 * @throws NotEnoughStock: para quando a quantidade de produtos nao forem suficientes
	 * @throws IdDoesntExist: para quando id nao existe
	 * @throws EntitiesNotRegistred: para quando a entidade nao foi registrada
	 */
	public static void updateStock(HashMap<String, Integer> prodsUsed) throws NotEnoughStock, IdDoesntExist, EntitiesNotRegistred{
		try {
			prodData.checkAllProductsEnough(prodsUsed);
		
			for (HashMap.Entry<String,Integer> idQuant : prodsUsed.entrySet()) {
				prodData.removeQuantProd(idQuant.getKey(), idQuant.getValue());
			};
		} catch(NotEnoughStock e){
				throw new NotEnoughStock();
		}
	}

	
	public static ArrayList<Product> listProduct(){
		return prodData.getProductsList();
	}
	
	public static void chooseAProduct(String id){
		prodData.setChosenEntityId(id);
	}
	
	public static Product chosenProduct() {
		try {
			String id = prodData.getChosenEntityId();
			return prodData.getOneProduct(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			return null;
		}
		
	}
}
