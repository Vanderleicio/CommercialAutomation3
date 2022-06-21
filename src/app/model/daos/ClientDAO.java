package app.model.daos;

import java.util.ArrayList;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Client;
import app.model.models.Sale;

public class ClientDAO extends AbstractDAO {
	
	public Client getOneClient(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Client) this.searchEntities(id);
	}
	
	public ArrayList <Client> getClientList(){
		ArrayList<Client> clientList = castList(this.getList());
		return clientList;
	}
}
