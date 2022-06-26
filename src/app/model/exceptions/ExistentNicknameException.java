/***************************
Autores: Alana Sampaio e Vanderleicio Junior
Componente Curricular: Programa��o II
Concluido em: 09/05/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************/

package app.model.exceptions;

/**
 * Exception gerada quando o Nickname que est� tentando
 * ser cadastrado j� existe
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class ExistentNicknameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExistentNicknameException() {
		super("Nickname ja cadastrado. Tente outro.");
	}
}
