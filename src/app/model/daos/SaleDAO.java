package app.model.daos;

import java.util.ArrayList;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Sale;

public class SaleDAO extends AbstractDAO {
	
	public Sale getOneSale(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Sale) this.searchEntities(id);
	}
	
	public ArrayList <Sale> getItemList(){
		ArrayList<Sale> saleList = castList(this.getList());
		return saleList;
	}
}
