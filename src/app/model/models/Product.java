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
import java.time.LocalDate;

/**
 * Classe dos produtos
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
	/**
	 * Quantidade do produto
	 */
	private int quantity;
	
	/**
	 * Construtor do produto com a declaracao de seus atributos
	 * @param name: Nome do produto.
	 * @param price: Preco do produto.
	 * @param validity: Validade do produto.
	 * @param provider: Fornecedor do produto.
	 */
	public Product(String name, BigDecimal price, LocalDate validity, int quantity, Provider provider){
		super("P");
		this.name = name;
		this.price = price;
		this.validity = validity;
		this.quantity = quantity;
		this.provider = provider;
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
	
	@Override
	public String toString() {
		return this.getId() + " - " + this.getName();
	}
}
