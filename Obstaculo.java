public abstract class Obstaculo {
    private final int id; // id para os tipos de obstáculo
    private final int x;
    private final int y;

    public Obstaculo(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {return id;}
    public int getX() {return x;}
    public int getY() {return y;}

    public abstract void colisao(Robo robo) throws MovimentoInvalidoException;
    public abstract String getSimbolo();
}
