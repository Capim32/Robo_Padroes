package model;

import enums.Cor;

public abstract class Obstaculo extends Entidade {
    public Obstaculo(int x, int y, String simbolo, Cor cor) {
        super(x, y, simbolo, cor);
    }
    public abstract void aplicarEfeito(Robo robo);
}