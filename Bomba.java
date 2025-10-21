public class Bomba extends Obstaculo {

    public Bomba(int id, int x, int y) {super(id, x, y);}

    @Override
    public void colisao(Robo robo) throws MovimentoInvalidoException {
        throw new MovimentoInvalidoException("O robô colidiu com uma bomba na posição (" + this.getX() + ", " + this.getY() + ") e não pode se mover nesta rodada.");
    }
    @Override
    public String getSimbolo() {return "X";}
}
