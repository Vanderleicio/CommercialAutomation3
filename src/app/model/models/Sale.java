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

package app.model.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classe das vendas a serem realizadas.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Sale extends Entity {
	/**
	 * Dia de realização da venda
	 */
	private LocalDate day;
	/**
	 * Hora da realização da venda
	 */
	private LocalTime hour;
	/**
	 * Itens comprados nesta venda
	 */
	private ArrayList<Item> itemsPurchased;
	/**
	 * Preco total da venda
	 */
	private BigDecimal priceTotal;
	/**
	 * Metodo de pagamento da venda
	 */
	private String paymentMethod;
	
	private Client client;
	
	/**
	 * @param day: LocalDate representando o dia da venda
	 * @param hour: LocalTime representando a hora da venda
	 * @param paymentMethod: String representando o método de pagamento da venda
	 * @param itemsPurchased: ArrayList de Items representando os itens comprados
	 */
	public Sale(LocalDate day, LocalTime hour, String paymentMethod, ArrayList<Item> itemsPurchased, Client client) {
		super("V");
		this.day = day;
		this.hour = hour;
		this.itemsPurchased = itemsPurchased;
		this.paymentMethod = paymentMethod;
		this.client = client;
		updatePrice();
	}

	/**
	 * Atualiza o preco total da venda somando o preco de todos os itens
	 */
	public void updatePrice() {
		this.priceTotal = new BigDecimal("0");
		if (this.itemsPurchased.size() > 0) {
			itemsPurchased.forEach(item -> priceTotal = priceTotal.add(item.getPrice()));
		}
	}

	/**
	 * Adiciona um novo item a lista de itens comprados
	 * @param item: item a ser adicionado
	 */
	public void addItem(Item item) {
		this.itemsPurchased.add(item);
		updatePrice();
	}
	
	/**
	 * Deleta um item da lista de itens comprados.
	 * @param item: item a ser deletado
	 * @return o tamanho atual da lista de itens
	 */
	public int deleteItem(Item item) {
		this.itemsPurchased.remove(item);
		updatePrice();
		return this.itemsPurchased.size();
	}

	/**
	 * @return o dia
	 */
	public LocalDate getDay() {
		return day;
	}

	/**
	 * @param day: Novo dia
	 */
	public void setDay(LocalDate day) {
		this.day = day;
	}

	/**
	 * @return a hora
	 */
	public LocalTime getHour() {
		return hour;
	}

	/**
	 * @param hour: Nova hora
	 */
	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	/**
	 * @return a lista de itens comprados
	 */
	public ArrayList<Item> getItemsPurchased() {
		return itemsPurchased;
	}

	/**
	 * @param itemsPurchased: Nova lista de itens comprados
	 */
	public void setItemsPurchased(ArrayList<Item> itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
		updatePrice();
	}

	/**
	 * @return preco total
	 */
	public BigDecimal getPriceTotal() {
		return priceTotal;
	}

	/**
	 * @param priceTotal: Novo preco total
	 */
	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
	}

	/**
	 * @return metodo de pagamento
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod: Novo metodo de pagamento
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return o cliente
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param novo cliente
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
