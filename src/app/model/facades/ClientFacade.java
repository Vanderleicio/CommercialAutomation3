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

import app.model.daos.ClientDAO;
import app.model.exceptions.*;
import app.model.models.*;

/** Classe gerencia clientes
 * 
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ClientFacade {
	/**
	 * Atributo estatico para ter acesso ao ClientDAO
	 */
	private static ClientDAO clientData = new ClientDAO();
	/** Metodo cria novo cliente
	 * 
	 * @param name: nome do cliente
	 * @param cpf: cpf do cliente
	 * @param email: email do cliente
	 * @param phoneNumber: numero de telefone do cliente
	 * @throws EmptyStringException
	 */
	public static void createClient(String name, String cpf, String email, String phoneNumber) throws EmptyStringException {
		if (name.equals("") | cpf.equals("") | email.equals("") | phoneNumber.equals("")){
			throw new EmptyStringException();
		}
		Client newClient = new Client(name, cpf, email, phoneNumber);
		clientData.add(newClient);
	}
	/**Metodo deleta cliente da lista
	 * 
	 * @param id: id do cliente
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 */
	public static void delClient(String id) throws IdDoesntExist, EntitiesNotRegistred{
		clientData.delete(id);
	}
	/** Metodo edita cliente
	 * 
	 * @param id: id do cliente
	 * @param newName: novo nome
	 * @param newCPF: novo cpf
	 * @param newEmail: novo email
	 * @param newPhoneNumber: novo numero de telefone
	 * @throws IdDoesntExist
	 * @throws EntitiesNotRegistred
	 * @throws ExistentNicknameException
	 * @throws EmptyStringException
	 */
	public static void editClient(String id, String newName, String newCPF, String newEmail, String newPhoneNumber) throws IdDoesntExist, EntitiesNotRegistred, ExistentNicknameException, EmptyStringException {
		if (newName.equals("") | newCPF.equals("") | newEmail.equals("") | newPhoneNumber.equals("")){
			throw new EmptyStringException();
		}
		Client ClientEdit = clientData.getOneClient(id);
		
		ClientEdit.setName(newName);
		ClientEdit.setCpf(newCPF);
		ClientEdit.setEmail(newEmail);
		ClientEdit.setPhoneNumber(newPhoneNumber);
	}
	/** Metodo lista clientes
	 * 
	 * @return lista de clientes
	 */
	public static ArrayList<Client> listClient(){
		return clientData.getClientList();
	}
	/**
	 * 
	 * @param id
	 */
	public static void chooseAClient(String id){
		clientData.setChosenEntityId(id);
	}
	/**
	 * 
	 * @return
	 */
	public static Client chosenClient() {
		try {
			String id = clientData.getChosenEntityId();
			return clientData.getOneClient(id);
		} catch (IdDoesntExist | EntitiesNotRegistred e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
