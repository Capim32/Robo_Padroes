/*
 4) Crie uma classe abstrata Osbtaculo que possui como atributo um id e o método
 abstrato bater.
 */
public abstract class Obstaculo {
    private final int id;
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

    public abstract void bater(Robo robo, Tabuleiro tab, int oldX, int oldY);

    public abstract String getSimbolo();
}
