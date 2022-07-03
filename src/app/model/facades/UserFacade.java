package app.model.facades;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programação II
Concluido em: 02/07/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************/

import java.util.ArrayList;

import app.model.daos.UserDAO;
import app.model.exceptions.*;
import app.model.models.*;
/**
 * Classe gerencia o usuario
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 *
 */
public class UserFacade {
	/**
	 * Atributo estatico para ter acesso ao UserDAO
	 */
	private static UserDAO userData = new UserDAO();
	/**
	 * Atributo estatico do id atual
	 */
	private static String idCurrentUser = "0";
	
	/**Metodo responsavel por criar um novo usuario
	 * 
	 * @param nickName: apelido do usuario
	 * @param password: senha do usuario
	 * @param name: nome do usuario
	 * @param category: categoria do usuario
	 * @throws ExistentNicknameException
	 * @throws EmptyStringException
	 */
	public static void create(String nickName, String password, String name, String category) throws ExistentNicknameException, EmptyStringException{
		if (nickName.equals("") | name.equals("") | password.equals("")){
			throw new EmptyStringException();
		}
		try {
			userData.searchNick(nickName);
			throw new ExistentNicknameException();
		} catch(NickNonexistent eNick) {
			User newUser = new User(nickName, password, name, category);
			userData.add(newUser);
		}
	}
	/**Metodo responsavel por fazer login no sistema
	 * 
	 * @param nickname: apelido do usuario
	 * @param password: senha do usuario
	 * @throws LoginDoesntMatch
	 * @throws NickNonexistent
	 */
	public static void login(String nickname, String password) throws LoginDoesntMatch, NickNonexistent {
		try {
			User userAttempt = (User) userData.searchNick(nickname);
			if (password.equals(userAttempt.getPassword())) {
				String idUser = userData.searchNick(nickname).getId();
				idCurrentUser = idUser;
			} else {
				throw new LoginDoesntMatch();
			}
		} catch(NickNonexistent eNick) {
			throw new NickNonexistent();
		}
	}
	/**Metodo responsavel por deletar um usuario do sistema
	 * 
	 * @param id: id do usuario
	 * @throws CurrentUserException
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void delUser(String id) throws CurrentUserException, IdDoesntExist, EntitiesNotRegistred {
		if (id.equals(idCurrentUser)) {
			throw new CurrentUserException();
		} else {
			userData.delete(id);
		}
	}
	/**Metodo responsavel por editar um usuario do sistema
	 * 
	 * @param id: id do usuario
	 * @param newNick: novo apelido do usuario
	 * @param newPass: nova senha do usuario
	 * @param newName: novo nome do usuario
	 * @param newCategory: nova categoria do usuario
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws ExistentNicknameException
	 * @throws EmptyStringException
	 */
	public static void editUser(String id, String newNick, String newPass, String newName, String newCategory) throws IdDoesntExist, EntitiesNotRegistred, ExistentNicknameException, EmptyStringException {
		if (newNick.equals("") | newName.equals("") | newPass.equals("")){
			throw new EmptyStringException();
		}
		User userEdit = userData.getOneUser(id);
		
		try {
			if (!userEdit.getNickname().equals(newNick)) {
				userData.searchNick(newNick);
				throw new ExistentNicknameException();
			}
			userEdit.setName(newName);
			userEdit.setPassword(newPass);
			userEdit.setCategory(newCategory);
		} catch(NickNonexistent eNick) {
			userEdit.setNickname(newNick);
			userEdit.setName(newName);
			userEdit.setPassword(newPass);
			userEdit.setCategory(newCategory);
		}
	}
	/**Metodo responsavel por retornar a lista de usuarios
	 * 
	 * @return lista de usuarios
	 */
	public static ArrayList<User> listUser(){
		return userData.getUsersList();
	}

	/**
	 * @return o  id atual
	 */
	public static String getIdCurrentUser() {
		return idCurrentUser;
	}
	/** Escolhe um usuario
	 * 
	 * @param id: id do usuario
	 */
	public static void chooseAUser(String id){
		userData.setChosenEntityId(id);
	}
	/**Metodo responsavel por encontrar usuario
	 * 
	 * @return id do usuario
	 */
	public static User chosenUser() {
		try {
			String id = userData.getChosenEntityId();
			return userData.getOneUser(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
