/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programa��o II
Concluido em: 09/05/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************/

package app.models;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Representa os itens que ser�o cadastrados.
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
	 * Hash Map com o nome do grupo que comp�e o item e a quantidade de 
	 * produtos necessarias para fazer um item 
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

		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryItems = categoryItems;
		this.composition = composition;
		generatorCode("I");
	}

	/**
	 * Adiciona um produto a composi��o do item.
	 * @param quantity: Quantidade do produto para fazer um item.
	 * @param product: Nome do grupo ao qual o produto pertence.
	 */
	public void addProduct(int quantity, String product) {
		this.composition.put(product, quantity);
	}
	
	/**
	 * Remove um produto da composicao do item.
	 * @param prod: Nome do grupo que ser� removido.
	 */
	public void deleteProduct(String prod) {
		this.composition.remove(prod);
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
