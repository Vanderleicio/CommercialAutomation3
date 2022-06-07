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
import java.time.LocalDate;

/**
 * Classe dos produtos . Se relaciona com a classe Fornecedores, por um 
 * dos atributos ser o fornecedor do item.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Product extends Entity{
	/**
	 * Nome do produto.
	 */
	private String name;
	/**
	 * Preco do produto.
	 */
	private BigDecimal price;
	/**
	 * Validade do produto. 
	 */
	private LocalDate validity;
	
	/**
	 * Atributos do fornecedor do produto.
	 */
	private Provider provider;
	
	private int quantity;
	
	/**
	 * Construtor do produto com a declaracao de seus atributos
	 * @param name: Nome do produto.
	 * @param price: Preco do produto.
	 * @param validity: Validade do produto.
	 * @param provider: Fornecedor do produto.
	 */
	public Product(String name, BigDecimal price, LocalDate validity,int quantity, Provider provider){
		this.name = name;
		this.price = price;
		this.validity = validity;
		this.quantity = quantity;
		this.provider = provider;
		generatorCode("P");
	}
	
	/**
	 * @return o nome
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name: Novo nome
	 */

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return o preco
	 */
	public BigDecimal getPrice() {
		return price;
	}


	/**
	 * @param price: Novo preco
	 */

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	/**
	 * @return a validade
	 */
	public LocalDate getValidity() {
		return validity;
	}

	/**
	 * @param validity: Nova validade
	 */
	public void setValidity(LocalDate validity) {
		this.validity = validity;
	}

	/**
	 * @return o fornecedor
	 */

	public Provider getProvider() {
		return provider;
	}


	/**
	 * @param provider: Novo fornecedor.
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	/**
	 * @return a quantidade
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity: Nova quantidade.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
