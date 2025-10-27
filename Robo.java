
public class Robo {
	public static void movimentoValidade(int posicao, char eixo) throws MovimentoInvalidoException {
		if(posicao < 0 || posicao > 3) {
			String aviso = Robo.ANSI_RED + " erro: Movimento invalido, "+eixo+" estara fora do mapa" + Robo.ANSI_RESET;
			throw new MovimentoInvalidoException(aviso);
		}
	}

	 // Constantes de cores ANSI para visualização no console (opcional, para estética)
	public String ANSI_COR = "";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

	private int x;
	private int y;
	private int xAnterior;
	private int yAnterior;


	private String corRobo;
	protected  boolean explodiu = false;	

	protected int movimentosRealizados;
	protected int movimentosValidos;
	protected int movimentosInvalidos;


	public Robo(String corRobo) {
		super();
		this.corRobo = corRobo;
		this.x = 0;
		this.y = 0;
		this.xAnterior = 0;
		this.yAnterior = 0;
	}

	public int getX() {return x;}
	public void setX(int x) {this.x = x; this.xAnterior = x;}

	public int getY() {return y;}	
	public void setY(int y) {this.y = y; this.yAnterior = y;}

	public String getCorRobo() {return corRobo;}
	public void setCor(String corRobo) {this.corRobo = corRobo;}

	public boolean isExplodiu() {return explodiu;}
	public void setExplodiu(boolean explodiu) {this.explodiu = explodiu;}

	public int getMovimentosValidos() { return movimentosValidos; }
    public int getMovimentosInvalidos() { return movimentosInvalidos; }

	// método para pegar as cores ANSI 
	public String getCorANSI() {
        switch (corRobo.toUpperCase()) {
            case "VERMELHO": return ANSI_RED;
            case "AZUL": return ANSI_BLUE;
            case "VERDE": return ANSI_GREEN;
            case "AMARELO": return ANSI_YELLOW;
            default: return ANSI_RESET;
        }
	}

	
	public void mover(String movimento) throws MovimentoInvalidoException {
		if (this.explodiu) {return;} // caso tenha explodido, nao se move no turno

		int novoX = this.x;
		int novoY = this.y;

		switch (movimento.toLowerCase()) {
			case "up":
				novoY ++;
				break;
			case "right":
				novoX ++;
				break;
			case "down":
				novoY --;
				break;
			case "left":
				novoX --;
				break;
			default:
				this.movimentosInvalidos ++;
				throw new MovimentoInvalidoException(movimento + " nao e um movimento valido. Use: up, right, down ou left.");
		}

		if (novoX < 0 || novoY < 0) {
			this.movimentosInvalidos ++;
			throw new MovimentoInvalidoException(" Movimento invalido, o robo nao pode sair do limite de 0 em ambos os eixos.");
		}

		/*
		// execução do movimento
		this.xAnterior = this.x; // armazena posicao anterior
		this.yAnterior = this.y;
		this.x = novoX; // atualiza posicao
		this.y = novoY;
		this.movimentosRealizados++;
		this.movimentosValidos++;
		System.out.println("O robo " + this.corRobo + " moveu para a posicao (" + this.x + ", " + this.y + ").");
		*/

		try {
			movimentoValidade(novoX, 'X');
			movimentoValidade(novoY, 'Y');
			this.xAnterior = this.x;
			this.yAnterior = this.y;
			this.x = novoX;
			this.y = novoY;
			this.movimentosRealizados++;
			this.movimentosValidos++;

			System.out.println("O robo " + this.corRobo + " moveu para a posicao (" + this.x + ", " + this.y + ").");
			

		} catch (MovimentoInvalidoException e) {
			System.out.println(e.getMessage());
			this.movimentosInvalidos++;
		}
	}

	// overload do metodo mover para movimentos com int
	public void mover(int intMover) throws MovimentoInvalidoException {
		if (this.explodiu) {return;}

		switch(intMover) {
			case 1: mover("up"); break;
			case 2: mover("down"); break;
			case 3: mover("right"); break;
			case 4: mover("left"); break;
			default:
				throw new MovimentoInvalidoException("codigo de movimento invalido, tente novamente.");
		}
		
	}
	
	public boolean seAlimentou(int frutaX, int frutaY) {
		if(this.x == frutaX && this.y == frutaY) {return true;}
		else {return false;}
	}

	public void colidiuRocha() {
		this.x = this.xAnterior;
        this.y = this.yAnterior;
	}

	public void explodiu() {
		this.explodiu = true;
		System.out.println("O robo " + this.corRobo + " encontrou um obstaculo e explodiu na posicao (" + this.x + ", " + this.y + ").");
	}
    
    // toString para fácil impressão
    @Override
    public String toString() {
        // Usado para representar o robô no tabuleiro
        return corRobo.substring(0, 1).toUpperCase(); 
    }  
}
