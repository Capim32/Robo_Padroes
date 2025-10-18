import java.util.Random;

public class RoboInteligente extends Robo {
    Random random = new Random();

    private int comidaX;
    private int comidaY;

    public RoboInteligente(int comidaX, int comidaY, String corRobo) {
        super(corRobo);
        this.comidaX = comidaX;
        this.comidaY = comidaY;
    }

    public void mover() {
        if (getX() < comidaX) {
            mover("right");
        } else if (getX() > comidaX) {
            mover("left");
        } else if (getY() < comidaY) {
            mover("up");
        } else if (getY() > comidaY) {
            mover("down");
        }
    }

    
    
}
