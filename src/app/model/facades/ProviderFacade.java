package app.model.facades;

import java.util.ArrayList;

import com.itextpdf.text.Paragraph;

import app.model.daos.ProviderDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class ProviderFacade {
	
	private static ProviderDAO provData = new ProviderDAO();
	
	public static void createProvider(String name, String cnpj, String address) throws EmptyStringException {
		if (name.equals("") | cnpj.equals("") | address.equals("")){
			throw new EmptyStringException();
		}
		Provider newProvider = new Provider(name, cnpj, address);
		provData.add(newProvider);
	}
	
	public static void delProvider(String id) throws IdDoesntExist, EntitiesNotRegistred{
		provData.delete(id);
	}
	
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
	
	public static void updateProductsProvided(Provider prov) {
		ArrayList<Product> prods = new ArrayList<Product>();
		for (Product prod : ProductFacade.listProduct()) {
			if (prod.getProvider().equals(prov)) {
				prods.add(prod);
			}
    	}
	}
	
	public static void updateAllProducstProvided() {
		for (Provider prov: listProvider()) {
			updateProductsProvided(prov);
		}
	}
	
	public static ArrayList<Provider> listProvider(){
		return provData.getProvidersList();
	}
	
	public static void chooseAProvider(String id){
		provData.setChosenEntityId(id);
	}
	
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
