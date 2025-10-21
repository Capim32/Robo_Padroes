public class Rocha extends Obstaculo{
    public Rocha(int id, int x, int y) {super(id, x, y);}

    @Override
    public void colisao(Robo robo) throws MovimentoInvalidoException {
        robo.colidiuRocha();
        throw new MovimentoInvalidoException("O robô colidiu com uma rocha na posicao (" + this.getX() + ", " + this.getY() + ")" +
        "e voltou para a posicao anterior.");
    }

    @Override
    public String getSimbolo() {return "🪨";}
    
}
