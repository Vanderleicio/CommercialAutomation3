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

import java.util.ArrayList;

import com.itextpdf.text.Paragraph;

import app.model.daos.ProviderDAO;
import app.model.exceptions.*;
import app.model.models.*;

/** Classe gerencia fornecedores
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ProviderFacade {
	/**
	 * Atributo estatico para ter acesso ao ProviderAO
	 */
	private static ProviderDAO provData = new ProviderDAO();
	/**Metodo cria novo fornecedor
	 * 
	 * @param name: nome do fornecedor
	 * @param cnpj: cnpj do fornecedor
	 * @param address: endereço do fornecedor
	 * @throws EmptyStringException
	 */
	public static void createProvider(String name, String cnpj, String address) throws EmptyStringException {
		if (name.equals("") | cnpj.equals("") | address.equals("")){
			throw new EmptyStringException();
		}
		Provider newProvider = new Provider(name, cnpj, address);
		provData.add(newProvider);
	}
	/**Deleta fornecedor
	 * 
	 * @param id: id do fornecedor
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void delProvider(String id) throws IdDoesntExist, EntitiesNotRegistred{
		provData.delete(id);
	}
	/**Metodo edita fornecedor
	 * 
	 * @param id: id do fornecedor
	 * @param newName: novo nome 
	 * @param newCNPJ: novo cnpj
	 * @param newAddres: novo endereço
	 * @param newProdList: nova lista de produtos
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws EmptyStringException
	 */
	public static void editProvider(String id, String newName, String newCNPJ, String newAddres, ArrayList<Product> newProdList) throws IdDoesntExist, EntitiesNotRegistred, EmptyStringException {
		if (newName.equals("") | newCNPJ.equals("") | newAddres.equals("")){
			throw new EmptyStringException();
		}
		Provider providerEdit = provData.getOneProvider(id);
		
		providerEdit.setName(newName);
		providerEdit.setCnpj(newCNPJ);
		providerEdit.setAddress(newAddres);
		providerEdit.setProductsProvided(newProdList);
	}
	/**Metodo atualiza fornecedores na lista de produtos
	 * 
	 */
	public static void updateProductsProvided(Provider prov) {
		ArrayList<Product> prods = new ArrayList<Product>();
		for (Product prod : ProductFacade.listProduct()) {
			if (prod.getProvider().equals(prov)) {
				prods.add(prod);
			}
    	}
	}
	/**
	 * Metodo atualiza lista de fornecores chamando metodo anterior
	 */
	public static void updateAllProducstProvided() {
		for (Provider prov: listProvider()) {
			updateProductsProvided(prov);
		}
	}
	/** Metodo mostra lista de fornecedores
	 * 
	 * @return lista de fornecedores
	 */
	public static ArrayList<Provider> listProvider(){
		return provData.getProvidersList();
	}
	/**
	 * 
	 * @param id: id do fornecedor
	 */
	public static void chooseAProvider(String id){
		provData.setChosenEntityId(id);
	}
	/**
	 * 
	 * @return
	 */
	public static Provider chosenProvider() {
		try {
			String id = provData.getChosenEntityId();
			return provData.getOneProvider(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
