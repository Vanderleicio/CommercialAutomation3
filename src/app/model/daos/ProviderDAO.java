package app.model.daos;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Provider;
import app.model.models.User;

public class ProviderDAO extends AbstractDAO{
	
	public Provider getOneProvider(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Provider) this.searchEntities(id);
	}
}
