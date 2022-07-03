package app.model.models;

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

/**
 *Classe responsavel pela entidade cliente
 *@author Alana Sampaio
 *@author Vanderleicio Junior
 *
 */

public class Client extends Entity{
	/**
	 * Nome do cliente
	 */
	private String name;
	/**
	 * cpf do cliente
	 */
	private String cpf;
	/**
	 * email do cliente
	 */
	private String email;
	/**
	 * telefone do cliente
	 */
	private String phoneNumber;
	
	/**
	 * @param nickname: Nickname do usuario
	 * @param cpf: cpf do cliente
	 * @param email: email do cliente
	 * @param phoneNumber: telefone do cliente
	 */
	public Client(String name, String cpf, String email, String phoneNumber) {
		super("C");
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phoneNumber = phoneNumber;
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
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param novo cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return o email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param novo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return telefone
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param novo numero de telefone
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return this.getId() + " - " + this.getName();
	}
}
