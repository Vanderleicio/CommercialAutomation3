/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

package app.model.models;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Representa os itens que serao cadastrados.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 * 
 */
public class Item extends Entity {
	/**
	 * Nome do item.
	 */
	private String name;
	/**
	 * Descricao do item.
	 */
	private String description;
	/**
	 * Preco do item
	 */
	private BigDecimal price;
	/**
	 * Categoria do item
	 */
	private String categoryItems;
	/**
	 * Hash Map com o id do produto que compõe o item e a quantidade dele.
	 *  
	 */
	private HashMap<String, Integer> composition = new HashMap<String, Integer>();

	
	/**
	 * @param name: String representando o nome do item.
	 * @param description: String representando a descri��o do item
	 * @param price: BigDecimal representando o preco do item.
	 * @param categoryItems: String representando a categoria do item
	 * @param composition: HashMap representando a composi��o do item. 
	 */
	public Item(String name, String description, BigDecimal price, String categoryItems,
			HashMap<String, Integer> composition) {

		super("I");
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryItems = categoryItems;
		this.composition = composition;
	}

	/**
	 * Adiciona um produto a composi��o do item.
	 * @param quantity: Quantidade do produto para fazer um item.
	 * @param product: Nome do grupo ao qual o produto pertence.
	 */
	public void addProduct(int quantity, String idProd) {
		this.composition.put(idProd, quantity);
	}
	
	/**
	 * Remove um produto da composicao do item.
	 * @param prod: Nome do grupo que ser� removido.
	 */
	public void deleteProduct(String idProd) {
		this.composition.remove(idProd);
	}
	
	/**
	 * 
	 * @return composicao do item
	 */
	public int getCompositionSize() {
		return this.composition.size();
	}
	
	/**
	 * @return nome do item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name: novo nome do item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return descricao do item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description: nova descricao do item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return preco do item
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price: novo preco do item
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return categoria do item
	 */
	public String getCategoryItems() {
		return categoryItems;
	}

	/**
	 * @param categoryItems: nova categoria do item
	 */
	public void setCategoryItems(String categoryItems) {
		this.categoryItems = categoryItems;
	}


	/**
	 * @return composicao do item
	 */
	public HashMap<String, Integer> getComposition() {
		return composition;
	}

	/**
	 * @param composition: nova composicao do item
	 */
	public void setComposition(HashMap<String, Integer> composition) {
		this.composition = composition;
	}
}
