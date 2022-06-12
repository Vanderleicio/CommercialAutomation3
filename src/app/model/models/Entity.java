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

package app.model.models;

/**
 * Classe das entidades presentes no sistema.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Entity {
	
	private static int lastIdAdded = 0;
	/**
	 * Codigo inico para identificacao de cada entidade criada.
	 */
	private String id;
	
	public Entity(String prefix) {
		this.id = prefix + String.valueOf(lastIdAdded++);
	}
	/**
	 * @return ID
	 */
	public String getId() {
		return id;
	}
}
