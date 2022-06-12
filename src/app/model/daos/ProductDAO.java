package app.model.daos;

import java.util.ArrayList;
import java.util.HashMap;

import app.model.exceptions.*;
import app.model.models.Product;

public class ProductDAO extends AbstractDAO{
	
	private HashMap<String, ArrayList<Product>> stock = new HashMap<String, ArrayList<Product>>();
	
	public Product getOneProduct(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Product) this.searchEntities(id);
	}
	
	public ArrayList <Product> getProductsList(){
		ArrayList<Product> productsList = castList(this.getList());
		return productsList;
	}
	
	/**
	 * Metodo responsavel em adicionar produto no estoque
	 * 
	 * @param newProd: produto a ser inserido no estoque
	 */
	public void addProductStock(Product newProd) {
		boolean nameExist = this.stock.containsKey(newProd.getName());
		if (nameExist) {
			this.stock.get(newProd.getName()).add(newProd);
		} else {
			this.stock.put(newProd.getName(), new ArrayList<Product>());
			this.stock.get(newProd.getName()).add(newProd);
		}
		
	}
	
	/**
	 * Metodo responsavel por apagar item do estoque
	 * 
	 * @param prod: produto a ser apagado
	 */
	public void deleteProductStock(Product prod){
		boolean nameExist = this.stock.containsKey(prod.getName());
		if (nameExist) {
			this.stock.get(prod.getName()).remove(prod);
		}
	}
	
	/**
	 * 
	 * @param prod: produto a ser usado como busca
	 * @param quant: quantidade a ser removida
	 * 
	 * @throws NotEnoughProduct: para quando a quantidade de produtos nao forem suficientes
	 * @throws IdDoesntExist: para quando id nao existir
	 * @throws EntitiesNotRegistred: para quando entidade nao for encontrada
	 */
	public void removeQuantProd(Product prod, int quant) throws NotEnoughProduct, IdDoesntExist, EntitiesNotRegistred{
		// Remove a quantidade passada da quantidade do produto. Se o produto for zerado ele é deletado.
		int quantBefore = prod.getQuantity();
		if (quantBefore <= quant) {
			this.deleteProductStock(prod);
			this.delete(prod.getId());
			throw new NotEnoughProduct(quant - quantBefore);
		} else {
			prod.setQuantity(quantBefore - quant);
		}
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
	public void removeQuantGroup(String nameGroup, int quant) throws NotEnoughStock, IdDoesntExist, EntitiesNotRegistred{
		// Remove a quantidade "quant" passada dos produtos do conjunto.
		ArrayList<Product> prods = this.stock.get(nameGroup);
		int left = quant;
		while (left > 0) {
			try {
				this.removeQuantProd(prods.get(0), left);
				left = 0;
			} catch(NotEnoughProduct eProd) {
				left = eProd.getQuantLeft();
			}
		}
	}
	
	/**
	 * Metodo responsavel por mostrar a quantidade de itens
	 * 
	 * @param prodName: nome do produto
	 * @return a quantidade do produto
	 */
	public int getGroupQuantity(String prodName) {
		// Recebe o nome de um conjunto de produtos e retorna a 
		//quantidade total (soma da quantidade de todos os produtos desse conjunto).
		int totalQuantity = 0;
		ArrayList<Product> prods = this.stock.get(prodName);
		for(Product prod : prods) {
			totalQuantity += prod.getQuantity();
		};
		return totalQuantity;
	}
	
	/**
	 * Metodo responsavel por verificar se todos os produtos sao o suficiente
	 * 
	 * @param groupsNeed: lista contendo os produtos
	 * @throws NotEnoughStock: para quando a quantidade de produtos nao forem suficientes
	 */
	public void checkAllProductsEnough(HashMap<String, Integer> groupsNeed)throws NotEnoughStock{
		for (HashMap.Entry<String,Integer> nameQuant : groupsNeed.entrySet()) {
			String prodName = nameQuant.getKey();
			Integer quantUsed = nameQuant.getValue();
			if(this.getGroupQuantity(prodName) < quantUsed) {
				throw new NotEnoughStock();
			}
		};
	}
	
	public HashMap<String, ArrayList<Product>> getStock() {
		return this.stock;
	}
}
