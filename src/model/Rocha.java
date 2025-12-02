package model;
import enums.Cor;

public class Rocha extends Obstaculo {
    public Rocha(int x, int y) {
        super(x, y, "P", Cor.AMARELO); // P de preda :)
    }
    @Override
    public void aplicarEfeito(Robo robo) {
        // a rocha não faz nada no método, o Controller gerencia o recuo (fica bem mais organizado com MVC)
    }
}