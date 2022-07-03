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

package app.model.exceptions;


/**
 * Exception gerada quando nao ha quantidade de produto suficiente para reduzi-lo.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class NotEnoughProduct extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Quantidade que faltou para o produto conseguir suprir o que foi pedido
	 */
	private int quantLeft;

	/**
	 * @param quantLeft: int quantidade que falta.
	 */
	public NotEnoughProduct(int quantLeft) {
		super("Produto n�o tem a quantidade suficiente.");
		this.quantLeft = quantLeft;
	}
	
	/**
	 * 
	 * @return a quantidade faltante do produto.
	 */
	public int getQuantLeft() {
		return this.quantLeft;
	}
}
