package app.model.models;

public class Client extends Entity{

	private String name;

	private String cpf;

	private String email;
	
	private String phoneNumber;
	
	/**
	 * @param nickname: Nickname do usuario
	 * @param password: Senha do usuario
	 * @param name: Nome do usuario
	 * @param category: Cargo do usuario
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
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return this.getId() + " - " + this.getName();
	}
}
