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

/**
 * Classe das entidades presentes no sistema.
 * @author Alana Sampaio
 * @author Vanderleicio Junior
 */
public class Entity {
	/**
	 * atributo estatico para formar id
	 */
	private static int lastIdAdded = 0;
	/**
	 * Codigo inico para identificacao de cada entidade criada.
	 */
	private String id;
	/**Metodo para juntar o prefiro com o valor formando o id
	 * @param prefix
	 */
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
