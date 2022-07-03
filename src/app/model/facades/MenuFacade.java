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
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.ItemDAO;
import app.model.exceptions.*;
import app.model.models.*;
/** Classe gerencia o menu de compras
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class MenuFacade {
	/**
	 * Atributo estatico para ter acesso ao ItemDAO
	 */
	private static ItemDAO itemData = new ItemDAO();
	/** Metodo cria novo item do menu
	 * 
	 * @param name: nome do item
	 * @param description: descrição do item
	 * @param price: preco do item
	 * @param category: categoria do item
	 * @param composition: composicao do item
	 * @throws EmptyStringException
	 */
	public static void createItem(String name, String description, BigDecimal price, String category, HashMap<String, Integer> composition) throws EmptyStringException {
		if (name.equals("") | description.equals("") | category.equals("")){
			throw new EmptyStringException();
		}
		Item newItem = new Item(name, description, price, category, composition);
		itemData.add(newItem);
	}
	
	/** Metodo edita items
	 * 
	 * @param id: id do item
	 * @param newName: novo nome
	 * @param newDescription: nova descricao
	 * @param newPrice: novo preco
	 * @param newCategory: nova categoria
	 * @param newComposition: nova composicao
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws EmptyStringException
	 */
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
	/** Metodo deleta item do menu
	 * 
	 * @param id: id do item
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void delItem(String id) throws IdDoesntExist, EntitiesNotRegistred{
		itemData.delete(id);
	}
	/** Metodo gerencia lista de composicao de items
	 * 
	 * @param prods: lista de produtos
	 * @param qntt: lista de quantidade
	 * @return
	 * @throws InvalidQuantityException
	 */
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
	/** Metodo adiciona produtos aos items do menu
	 * 
	 * @param idPEdit: item a ser inserido produtos
	 * @param productPAdd: produto a ser adicionado
	 * @param quantity: quantidade
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void addProductsItems(String idPEdit, Product productPAdd, int quantity) throws IdDoesntExist, EntitiesNotRegistred{
		Item itemPEdit;
		itemPEdit = (Item) itemData.searchEntities(idPEdit);
		itemPEdit.addProduct(quantity, productPAdd.getId());
	}
	/** Metodo remove produtos aos items do menu
	 * 
	 * @param idPEdit: item a ser removido produtos
	 * @param produtoPRemover: produto a ser removido
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void removeProductFromItem(String idPEdit, Product produtoPRemover) throws IdDoesntExist, EntitiesNotRegistred {

		Item itemPEdit = (Item) itemData.searchEntities(idPEdit);
		itemPEdit.deleteProduct(produtoPRemover.getId());
		int size = itemPEdit.getCompositionSize();
		if (size == 0) {
			itemData.delete(idPEdit);
		}
	}
	/** Metodo edita quantidade de produto dos itens
	 * 
	 * @param idItem: id do item
	 * @param idProd: id do produto
	 * @param newQnt: nova quantidade
	 * @throws InvalidQuantityException
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void editProdQnt(String idItem, String idProd, int newQnt) throws InvalidQuantityException, IdDoesntExist, EntitiesNotRegistred {
		if (newQnt > 0) {
			Item item = (Item) itemData.searchEntities(idItem);
			item.getComposition().replace(idProd, newQnt);
		} else {
			throw new InvalidQuantityException();
		}
	}
	/** Metodo para pegar somente os produtos de um item.
	 * 
	 * @param itemId: ID do item
	 * @return Lista dos produtos do item
	 */
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
	
	/** Metodo para pegar somente a quantidade dos produtos de um item.
	 * 
	 * @param itemId: id do item
	 * @return lista das quantidades
	 */
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
	/** Metodo retorna lista de items
	 * 
	 * @return lista de items
	 */
	public static ArrayList<Item> listItem(){
		return itemData.getItemList();
	}
	/**
	 * 
	 * @param id
	 */
	public static void chooseAItem(String id){
		itemData.setChosenEntityId(id);
	}
	/**
	 * 
	 * @return
	 */
	public static Item chosenItem() {
		try {
			String id = itemData.getChosenEntityId();
			return itemData.getOneItem(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			return null;
		}
		
	}
}
