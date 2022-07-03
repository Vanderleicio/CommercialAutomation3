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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import app.model.daos.SaleDAO;
import app.model.exceptions.*;
import app.model.models.*;

/**
 * Classe responsavel por gerenciar vendas
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 *
 */
public class SaleFacade {
	
	/**
	 * Atributo estatico para ter acesso ao SaleDAO
	 */
	private static SaleDAO saleData = new SaleDAO();
	
	/**Metodo cria nova venda
	 * 
	 * @param day: dia da venda
	 * @param hour: hora da venda
	 * @param paymentMethod: forma de pagamento
	 * @param itemPurchased: carrinho de compras
	 * @param client: cliente que fez o pedido
	 * @throws EmptyStringException
	 */
	public static void createSale(LocalDate day, LocalTime hour, String paymentMethod, ArrayList<Item> itemPurchased, Client client) throws EmptyStringException {
		if (paymentMethod.equals("")){
			throw new EmptyStringException();
		}
		Sale newSale = new Sale(day, hour, paymentMethod, itemPurchased, client);
		saleData.add(newSale);
	}

	/**Metodo edita venda
	 * 
	 * @param id: id da venda
	 * @param newDay: nova data
	 * @param newHour: novo horario
	 * @param newPaymentMethod: novo metodo de pagamento
	 * @param newItems: novos itens 
	 * @param newClient: novo cliente
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws EmptyStringException
	 */
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
	
	/**Metodo deleta venda
	 * 
	 * @param id: id da venda
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void delSale(String id) throws IdDoesntExist, EntitiesNotRegistred{
		saleData.delete(id);
	}
	
	/**Metodo adiciona item no carrinho de compras
	 * 
	 * @param idPEdit: id da venda
	 * @param itemPAdd: item a ser adicionado
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void addItem(String idPEdit, Item itemPAdd) throws IdDoesntExist, EntitiesNotRegistred {
		Sale saleEdit = (Sale) saleData.searchEntities(idPEdit);
		saleEdit.addItem(itemPAdd);
	}
	
	/**Deleta item do carrinho
	 * 
	 * @param idPEditar: id da venda
	 * @param itemPDeletar: item a ser deletado do carrinho
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void deleteItem(String idPEditar, Item itemPDeletar) throws IdDoesntExist, EntitiesNotRegistred {
		Sale saleEdit = (Sale) saleData.searchEntities(idPEditar);
		int size = saleEdit.deleteItem(itemPDeletar);
		if (size == 0) {
			saleData.delete(idPEditar);
		}
	}
	
	/**Metodo mostra itens do carrinho que foram vendidos
	 * 
	 * @param saleId: id da venda
	 * @return lista
	 */
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
	
	/** Retorna todos os produtos que são usados nessa venda.
	 * 
	 * @param itemsPurchased: carrinho de compras
	 * @return hashmap de produtos
	 */
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
	
	/** 
	 * 
	 * @param id: id da venda
	 */
	public static void chooseASale(String id){
		saleData.setChosenEntityId(id);
	}
	/** Metodo procura id da venda
	 * 
	 */
	public static Sale chosenSale() {
		try {
			String id = saleData.getChosenEntityId();
			return saleData.getOneSale(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			return null;
		}
	}
	/** Metodo mostra a lista de vendas
	 * 
	 * @return lista de vendas
	 */
	public static ArrayList<Sale> listSale(){
		return saleData.getSaleList();
	}
}
