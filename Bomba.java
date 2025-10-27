public class Bomba extends Obstaculo {
    public static final String SIMBOLO = Robo.ANSI_RED + "B" + Robo.ANSI_RESET;

    public Bomba(int id, int x, int y) {super(id, x, y);}

    @Override
    public void bater(Robo robo, Tabuleiro tab, int oldX, int oldY) {
        if (!robo.isExplodiu()) { // Garante que só explode uma vez
            robo.setExplodiu(true);
            System.out.println("!!! AVISO: O Robô " + robo.getCorRobo() + " explodiu na Bomba (" + getX() + "," + getY() + ") e não pode mais se mover! !!!");
            tab.removerObstaculo(this); // a bomba some do tabuleiro
        }
    }

    @Override
    public String getSimbolo() {return SIMBOLO;}
}
