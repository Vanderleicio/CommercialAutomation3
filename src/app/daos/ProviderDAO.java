package app.daos;

import java.util.ArrayList;

import app.exceptions.EntitiesNotRegistred;
import app.exceptions.IdDoesntExist;
import app.models.Provider;

public class ProviderDAO{
	private static ArrayList<Provider> arrayProviders = new ArrayList<Provider>();
	
	public static void addProvider(Provider Provider) {
		arrayProviders.add(Provider);
	}
	
	public static ArrayList<Provider> listProvider() {
		return ProviderDAO.arrayProviders;
	}
	
	public static void delProvider(Provider Provider) {
		arrayProviders.remove(Provider);
	}
	
	public Provider searchById(String id) throws IdDoesntExist, EntitiesNotRegistred {
		
		if (arrayProviders.size() == 0) {
			throw new EntitiesNotRegistred();
		}
		
		for (int i = 0; i < arrayProviders.size(); i++) {
			String currentProvider = (arrayProviders.get(i)).getId();
			if (id.equals(currentProvider)) {
				return arrayProviders.get(i);
			}
		}
		throw new IdDoesntExist();
	}
}
