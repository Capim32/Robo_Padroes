package model;
import enums.Cor;

public class Bomba extends Obstaculo {
    public Bomba(int x, int y) {
        super(x, y, "B", Cor.VERMELHO);
    }
    @Override
    public void aplicarEfeito(Robo robo) {
        robo.setExplodiu(true);
    }
}