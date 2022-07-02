package app.model.facades;

import java.util.ArrayList;

import app.model.daos.ClientDAO;
import app.model.exceptions.*;
import app.model.models.*;

public class ClientFacade {
	
	private static ClientDAO clientData = new ClientDAO();
	
	public static void createClient(String name, String cpf, String email, String phoneNumber) throws EmptyStringException {
		if (name.equals("") | cpf.equals("") | email.equals("") | phoneNumber.equals("")){
			throw new EmptyStringException();
		}
		Client newClient = new Client(name, cpf, email, phoneNumber);
		clientData.add(newClient);
	}
	
	public static void delClient(String id) throws IdDoesntExist, EntitiesNotRegistred{
		clientData.delete(id);
	}
	
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
	
	public static ArrayList<Client> listClient(){
		return clientData.getClientList();
	}
	
	public static void chooseAClient(String id){
		clientData.setChosenEntityId(id);
	}
	
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
