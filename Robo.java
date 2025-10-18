
public class Robo {
	public static void movimentoValidade(int posicao, char eixo) throws MovimentoInvalidoException {
		if(posicao < 0) {
			String aviso = "Movimento invalido, "+eixo+" seria negativo.";
			throw new MovimentoInvalidoException(aviso);
		}
	}

	protected int x;
	protected int y;
	protected int xAnterior;
	protected int yAnterior;

	protected String corRobo;
	protected boolean explodiu = false;	

	protected int movimentosRealizados = 0;
	protected int movimentosInvalidos = 0;

	
	public Robo(String corRobo) {
		super();
		this.corRobo = corRobo;
		this.x = 0;
		this.y = 0;
		this.xAnterior = 0;
		this.yAnterior = 0;
	}

	public int getX() {return x;}
	public void setX(int x) {this.x = x; this.xAnterior = this.x;}

	public int getY() {return y;}	
	public void setY(int y) {this.y = y; this.yAnterior = this.y;}

	public String getCorRobo() {return corRobo;}
	public void setCor(String corRobo) {this.corRobo = corRobo;}

	public boolean isExplodiu() {return explodiu;}
	public void setExplodiu(boolean explodiu) {this.explodiu = explodiu;}

	
	
	public void mover(String movimento) {
		if(movimento.equalsIgnoreCase("up")) {
			int up = getY() + 1;
			setY(up);
		}
		else if(movimento.equalsIgnoreCase("down")) {
			int down = getY() - 1;
			try {
				movimentoValidade(down, 'Y');
				setY(down);
			}
			catch (MovimentoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(movimento.equalsIgnoreCase("right")) {
			int right = getX() + 1;
			setX(right);
		}
		else if(movimento.equalsIgnoreCase("left")) {
			int left = getX() - 1;
			try {
				movimentoValidade(left, 'X');
				setX(left);
			}
			catch (MovimentoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			
		}
	}
	
	public void mover(int movimento) throws MovimentoInvalidoException {
		if(movimento == 1) {
			int up = getY() + 1;
			setY(up);
		}
		else if(movimento == 2) {
			int down = getY() - 1;
			try {
				movimentoValidade(down, 'Y');
				setY(down);
			}
			catch (MovimentoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(movimento == 3) {
			int right = getX() + 1;
			setX(right);
		}
		else if(movimento == 4) {
			int left = getX() - 1;
			try {
				movimentoValidade(left, 'X');
				setX(left);
			}
			catch (MovimentoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			
		}
	}
	
	public boolean seAlimentou(int frutaX, int frutaY) {
		if(this.x == frutaX && this.y == frutaY) {
			return true;
		}
		else {return false;}
	}

    
}
