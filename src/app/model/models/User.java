package app.model.models;

import java.util.ArrayList;

public class User extends Entity{
	
	static private ArrayList<User> users = new ArrayList<User>();
	/**
	 * Nickname para login no sistema
	 */
	private String nickname;
	/**
	 * Senha para login no sistema
	 */
	private String password;
	/**
	 * Nome do usuario
	 */
	private String name;
	/**
	 * Cargo do usuario
	 */
	private String category;
	
	/**
	 * @param nickname: Nickname do usuario
	 * @param password: Senha do usuario
	 * @param name: Nome do usuario
	 * @param category: Cargo do usuario
	 */
	public User(String nickname, String password, String name, String category) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.category = category;
		generatorCode("U");
	}
	
	
	static public User searchNick(String nick) {
		for (int i = 0; i < users.size(); i++) {
			String currentNick = users.get(i).getNickname();
			if (nick.equals(currentNick)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	/**
	 * @return nome
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name: Novo nome
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return cargo
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category: Novo cargo 
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return nickname 
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname: Novo nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return senha
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password: Nova senha
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	static public void addUser(User user) {
		users.add(user);
	}
	
	static public void removeUser(User user) {
		users.remove(user);
	}
}
