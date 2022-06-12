package app.model.daos;

import java.util.ArrayList;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Provider;

public class ProviderDAO extends AbstractDAO{
	
	public Provider getOneProvider(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Provider) this.searchEntities(id);
	}
	
	public ArrayList <Provider> getProvidersList(){
		ArrayList<Provider> providersList = castList(this.getList());
		return providersList;
	}
}
