/**
 * 4) Rocha: o método bater faz o robô voltar para a posição anterior.
 */
public class Rocha extends Obstaculo {
    public static final String SIMBOLO = "R";

    public Rocha(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public void bater(Robo robo, Tabuleiro tab, int oldX, int oldY) {
        if (!robo.isExplodiu()) {
            robo.setX(oldX);
            robo.setY(oldY);
            robo.movimentosValidos--; // o movimento valido foi desfeito
            System.out.println( "Atenção: O Robô " + robo.getCorRobo() + " bateu na Rocha (" + getX() + "," + getY() + ") e recuou para (" + oldX + ", " + oldY + ")!");
        }
    }

    @Override
    public String getSimbolo() {return SIMBOLO;}
}
