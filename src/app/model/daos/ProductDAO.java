package app.model.daos;

import java.util.ArrayList;
import java.util.HashMap;

import app.model.exceptions.*;
import app.model.models.Product;

public class ProductDAO extends AbstractDAO{
	
	public Product getOneProduct(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Product) this.searchEntities(id);
	}
	
	public ArrayList <Product> getProductsList(){
		ArrayList<Product> productsList = castList(this.getList());
		return productsList;
	}
	
	/**
	 * Metodo responsavel por remover grupo de itens
	 * 
	 * @param nameGroup: nome do item
	 * @param quant: quantidade do item
	 * 
	 * @throws NotEnoughStock: para quando a quantidade de produtos nao forem suficientes
	 * @throws IdDoesntExist: para quando id nao existe
	 * @throws EntitiesNotRegistred: para quando entidade nao for encontrada
	 */
	public void removeQuantProd(String idProd, int quant) throws IdDoesntExist, EntitiesNotRegistred{
		// Remove a quantidade "quant" passada dos produtos do conjunto.
		Product prod = this.getOneProduct(idProd);
		int qntBefore = prod.getQuantity();
		int left = qntBefore - quant;
		prod.setQuantity(left);
	}
	
	/**
	 * Metodo responsavel por verificar se há produtos suficientes para efetuar a venda.
	 * 
	 * @param groupsNeed: lista contendo os produtos
	 * @throws NotEnoughStock: para quando a quantidade de produtos não forem suficientes
	 * @throws EntitiesNotRegistred 
	 * @throws IdDoesntExist 
	 */
	public void checkAllProductsEnough(HashMap<String, Integer> prodsUsed)throws NotEnoughStock, IdDoesntExist, EntitiesNotRegistred{
		for (HashMap.Entry<String,Integer> idQuant : prodsUsed.entrySet()) {
			String prodId = idQuant.getKey();
			
			Product prod = this.getOneProduct(prodId);
			Integer quantUsed = idQuant.getValue();
			if(prod.getQuantity() < quantUsed) {
				throw new NotEnoughStock();
			}
		};
	}

}
