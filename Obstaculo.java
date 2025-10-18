public abstract class Obstaculo {
    int x;
    int y;

    public Obstaculo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean colisao(int ProxRoboX, int ProxRoboY) {
        if (ProxRoboX == x && ProxRoboY == y) {return true;}
        else {return false;}
    }
    
}
