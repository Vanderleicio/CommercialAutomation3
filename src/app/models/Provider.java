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
		super();
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;

		this.productsProvided = new ArrayList<Product>();
		generatorCode("F");
	}
	
	/**
	 * Lista no terminal os produtos fornecidos por esse fornecedor.
	 */
	public void listProdProvided() {
		if (getProductsProvided() != null) {
		this.getProductsProvided().forEach(product -> {
			System.out.println("\tID: " + product.getId() +
							   "\tNome: " + product.getName());
			System.out.println("\n");
		});
		}
	}
	
	/**
	 * Adiciona um produto na lista de produtos fornecidos.
	 * @param prod: Produto a ser adicionado
	 */
	public void addProduct(Product prod) {
		this.productsProvided.add(prod);	
	}
	
	
	/**
	 * Remove um produto dos produtos fornecidos
	 * @param idProd: Id do produto a ser removido
	 */
	public void removeProduct(String idProd) {
		for (int i = 0; i < this.productsProvided.size(); i++) {
			String currentProd = (this.productsProvided.get(i)).getId();
			if (idProd.equals(currentProd)) {
				this.productsProvided.remove(i);
				}
			}
	}
	
	/**
	 * @return o name
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

}
