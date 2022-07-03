package app.model.models;

/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programacao II
Concluido em: 02/07/2022
Declaro que este codigo foi elaborado por mim de forma individual e nao contem nenhum
trecho de codigo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletronicos da Internet. Qualquer trecho de codigo
de outra autoria que nao a minha esta destacado com uma citacao para o autor e a fonte
do codigo, e estou ciente que estes trechos nao serao considerados para fins de avaliacao.
******************************/

public class User extends Entity{
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
		super("U");
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.category = category;
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
}
