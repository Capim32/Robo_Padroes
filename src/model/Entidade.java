package model;

import enums.Cor;

public abstract class Entidade {
    protected int x;
    protected int y;
    protected String simbolo;
    protected Cor cor;

    public Entidade(int x, int y, String simbolo, Cor cor) {
        this.x = x;
        this.y = y;
        this.simbolo = simbolo;
        this.cor = cor;
    }

    // Getters e Setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public Cor getCor() { return cor; }
    public String getSimbolo() { return simbolo; }
}