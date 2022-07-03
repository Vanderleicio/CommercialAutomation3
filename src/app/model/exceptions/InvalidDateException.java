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

package app.model.exceptions;

/**
 * Exception gerada quando o Nickname que esta tentando
 * ser cadastrado ja existe
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class InvalidDateException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidDateException() {
		super("Essa data já passou.");
	}
}
