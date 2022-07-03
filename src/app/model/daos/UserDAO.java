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

import app.model.exceptions.*;
import app.model.models.User;
/** Classe respondevel pelo DAO do usuario
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class UserDAO extends AbstractDAO{
	
	/**
	 * Procura e retorna um usuário a partir do seu nickname
	 * @param nick: nickname 
	 * @return item da lista
	 * @throws NickNonexistent 
	 */
	public User searchNick(String nick) throws NickNonexistent {
		for (int i = 0; i < this.getList().size(); i++) {
			String currentNick = ((User) this.getList().get(i)).getNickname();
			if (nick.equals(currentNick)) {
				return (User) this.getList().get(i);
			}
		}
		throw new NickNonexistent();
	}
	/** Procura e retorna uma Entidade a partir do seu ID
	 * 
	 * @param id: id do usuario
	 * @return id da entidade
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public User getOneUser(String id) throws IdDoesntExist, EntitiesNotRegistred {
		return (User) this.searchEntities(id);
	}
	/** Metodo mostra lista de usuarios
	 * 
	 * @return lista de usuarios
	 */
	public ArrayList <User> getUsersList(){
		ArrayList<User> usersList = castList(this.getList());
		return usersList;
	}
}
