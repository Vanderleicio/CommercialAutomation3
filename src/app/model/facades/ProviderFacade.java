package app.model.facades;

import java.util.ArrayList;

import app.model.daos.ProviderDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class ProviderFacade {
	
	private static ProviderDAO provData = new ProviderDAO();
	
	public static void createProvider(String name, String cnpj, String address) {
		Provider newProvider = new Provider(name, cnpj, address);
		provData.add(newProvider);
	}
	
	public static void delProvider(String id) throws IdDoesntExist, EntitiesNotRegistred{
		provData.delete(id);
	}
	
	public static void editProvider(String id, String newName, String newCNPJ, String newAddres, ArrayList<Product> newProdList) throws IdDoesntExist, EntitiesNotRegistred, ExistentNicknameException {
		Provider providerEdit = provData.getOneProvider(id);
		
		providerEdit.setName(newName);
		providerEdit.setCnpj(newCNPJ);
		providerEdit.setAddress(newAddres);
		providerEdit.setProductsProvided(newProdList);
	}
	
	public static ArrayList<Provider> listProvider(){
		return provData.getProvidersList();
	}
	
	public static void chooseAProvider(String id){
		provData.setChosenEntityId(id);
	}
	
	public static String chosenProvider() {
		return provData.getChosenEntityId();
	}
}
