/*pra falar a verdade, não vi direito a parte de tratamento de exceçoes
 ainda, mas se tiver algo errado meu fi ajeita.*/

//package tartaruga;

public class MovimentoInvalidoException extends Exception{
	private static final long serialVersionUID = 8;
	
	public MovimentoInvalidoException(String aviso) { //recebe uma string para retorar na exception;
		super(aviso);
	}
	
	public MovimentoInvalidoException() {
		super("Movimento invalido, pois posição irá ser negativa.");
	}
	
	public String toString() {
		return "movimento invalido.";
	}
	
}
