package app.model.facades;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programação II
Concluido em: 02/07/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************/

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.ProductDAO;
import app.model.exceptions.*;
import app.model.models.Product;
import app.model.models.Provider;

/**
 * Classe responsavel por gerenciar produtos
 * @author Alana Sampaio
 *@author Vancerleicio Junior
 */
public class ProductFacade {
	/**
	 *  Atributo estatico para ter acesso ao ProductDAO
	 */
	private static ProductDAO prodData = new ProductDAO();
	/** Metodo responsavel por criar novo produto
	 * 
	 * @param name: nome do produto
	 * @param price: preco do produto
	 * @param validity: validade do produto
	 * @param quantity: quantidade do produto
	 * @param provider: fornecedor que forneceu o produto
	 * @throws InvalidDateException
	 * @throws InvalidQuantityException
	 * @throws EmptyStringException
	 */
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
	/**Metodo deleta produto da lista
	 * 
	 * @param id: id do produto
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void delProduct(String id) throws IdDoesntExist, EntitiesNotRegistred{
		prodData.delete(id);
	}
	/** Metodo edita produto
	 * 
	 * @param id: id do produto
	 * @param newName: novo nome
	 * @param newPrice: novo preco
	 * @param newValidity: nova validade
	 * @param newQuantity: nova quantidade
	 * @param newProvider: novo fornecedor
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws EmptyStringException
	 */
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

	/** Metodo lista produtos
	 * 
	 * @return lista de produtos
	 */
	public static ArrayList<Product> listProduct(){
		return prodData.getProductsList();
	}
	/**
	 * 
	 * @param id
	 */
	public static void chooseAProduct(String id){
		prodData.setChosenEntityId(id);
	}
	/**
	 * 
	 * @return
	 */
	public static Product chosenProduct() {
		try {
			String id = prodData.getChosenEntityId();
			return prodData.getOneProduct(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			return null;
		}
		
	}
}
