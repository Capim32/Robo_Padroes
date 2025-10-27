/*pra falar a verdade, não vi direito a parte de tratamento de exceçoes
 ainda, mas se tiver algo errado meu fi ajeita.*/

public class MovimentoInvalidoException extends Exception{
	private static final long serialVersionUID = 8;
	
	public MovimentoInvalidoException(String aviso) {super(aviso);} //construtor que recebe aviso, ou seja, uma string que pega 'X' ou 'Y' como causa do erro.
	
	public MovimentoInvalidoException() {super(" Movimento invalido, pois a posição irá ser negativa.");}
	
	public String toString() {return "movimento invalido.";}
}
