package app.model.daos;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e paginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

import java.util.ArrayList;

import app.model.exceptions.EntitiesNotRegistred;
import app.model.exceptions.IdDoesntExist;
import app.model.models.Item;
/** Classe responsavel pelo DAO de items
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ItemDAO extends AbstractDAO {
	
	/** Procura e retorna uma Entidade a partir do seu ID
	 * 
	 * @param id: id do item
	 * @return entidade
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public Item getOneItem(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (Item) this.searchEntities(id);
	}
	/** Listar items do menu
	 * 
	 * @return lista de items
	 */
	public ArrayList <Item> getItemList(){
		ArrayList<Item> itemList = castList(this.getList());
		return itemList;
	}

}
