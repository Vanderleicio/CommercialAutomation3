package app.model.daos;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e paginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

import java.util.ArrayList;
import java.util.HashMap;

import app.model.exceptions.*;
import app.model.models.Product;
/** Classe responsavel pelo DAO de produtos
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ProductDAO extends AbstractDAO{
	/** Procura e retorna uma Entidade a partir do seu ID
	 * 
	 * @param id: id do produto
	 * @return entidade
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public Product getOneProduct(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Product) this.searchEntities(id);
	}
	/** Listar produtos
	 * 
	 * @return lista de produtos
	 */
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
