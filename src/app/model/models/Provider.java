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

import java.util.ArrayList;

/**
 * Classe dos fornecedores dos produtos do sistema.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Provider extends Entity {
	/**
	 * Nome do fornecedor
	 */
	private String name;
	/**
	 * CNPJ do fornecedor
	 */
	private String cnpj;
	/**
	 * Endereco do fornecedor
	 */
	private String address;
	/**
	 * Produtos fornecidos pelo fornecedor
	 */
	private ArrayList<Product> productsProvided;

	/**
	 * @param name: String representando o nome do fornecedor
	 * @param cnpj: String representando o cnpj do fornecedor
	 * @param address: String representando o endereco do fornecedor
	 */
	public Provider(String name, String cnpj, String address) {
		super("F");
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;
		this.productsProvided = new ArrayList<Product>();
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
	 * @return o cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj: Novo cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return o endereco
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address: Novo endereco
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return os produtos fornecidos
	 */
	public ArrayList<Product> getProductsProvided() {
		return productsProvided;
	}

	/**
	 * @param productsProvided: Novos produtos fornecidos
	 */
	public void setProductsProvided(ArrayList<Product> productsProvided) {
		this.productsProvided = productsProvided;
	}
	
	@Override
	public String toString() {
		return this.getId() + " - " + this.getName();
	}
}
