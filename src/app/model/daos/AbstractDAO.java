package app.model.daos;

import java.util.ArrayList;

import app.model.models.Entity;
import app.model.exceptions.*;


public abstract class AbstractDAO {

	private ArrayList<Entity> listEntity = new ArrayList<>();
	
	/**
	 * Metodo geral para adicionar a entidade cadastrada na ArrayList.
	 *@param element: Entidade a ser cadastrada
	 */
	public void add(Entity element) {
		listEntity.add(element);
	}
	
	/**
	 * Procura e retorna uma Entidade a partir do seu ID, que sera convertida na subclasse que a chamar.
	 * 
	 * @param id: Id da entidade a ser procurada
	 * @return o id
	 * 
	 * @throws IdDoesntExist: para quando id nao existe
	 * @throws EntitiesNotRegistred: para quando entidade nao foi registrada
	 */
	
	public Entity searchEntities(String id) throws IdDoesntExist, EntitiesNotRegistred{
		
		if (listEntity.size() == 0) {
			throw new EntitiesNotRegistred();
		}
		
		for (int i = 0; i < listEntity.size(); i++) {
			String currentEntities = listEntity.get(i).getId();
			if (id.equals(currentEntities)) {
				return listEntity.get(i);
			}
		}
		throw new IdDoesntExist();
	}
	
	/**
	 * Converte o codigo hashcode do objeto numa string, adiciona o 
	 * preFixo da classe, fornecido no construtor de cada uma delas, e retorna.
	 * @param preFixo: Letra que sera usada para identificacao da classe da Entidade
	 */
	public String generatorCode(String prefix, String hashcode) {
		String id;
		String hash = prefix + "-" + hashcode;
		id = validateCode(hash);
		return id;
	}

	/**
	 * Verifica se o ID passado ja existe e se sim, gera um novo a partir do primeiro.
	 * @param cod: ID que sera verificado.
	 * @return O proprio ID caso nao exista, um ID novo caso ele ja exista.
	 */
	public String validateCode(String cod) {
		int cont = 0;
		String newCod = cod;
		
		try {
			this.searchEntities(newCod);
			cont++;
			String[] codSeparate = cod.split("-");
			newCod = codSeparate[0] + "-" + String.valueOf(cont) + codSeparate[1];
			newCod = validateCode(newCod);
			return newCod;
		} catch (IdDoesntExist e) {
			return newCod;
		} catch (EntitiesNotRegistred e) {
			return newCod;
		}
	};
	
	/**
	 * Responsavel por mostrar a lista
	 * 
	 * @return lista
	 */
	public ArrayList<Entity> getList(){
		return listEntity;
	}
	
	/**
	 * Responsavel por mostrar o tamanho da lista
	 * 
	 * @return tamanho da lista
	 */
	public int sizeList() {
		return listEntity.size();
	}
	
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> castList(ArrayList<?> list){
		return (ArrayList<T>) list;
	}
	
	/**
	 * Metodo responsavel por apagar da ArrayList
	 * 
	 * @param idEntities: id da entidade que sera deletada
	 * 
	 * @throws IdDoesntExist: para quando id nao existe
	 * @throws EntitiesNotRegistred: para quando entidade nao foi registrada
	 */
	public void delete(String idEntities) throws IdDoesntExist, EntitiesNotRegistred {
		try {
			Entity element = searchEntities(idEntities);
			listEntity.remove(element);
		} catch(IdDoesntExist eId) {
			throw new IdDoesntExist();
		}
	}
}
