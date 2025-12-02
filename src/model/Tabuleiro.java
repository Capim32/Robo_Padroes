package model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private final int tamanho;
    private int frutaX = -1, frutaY = -1;
    private final List<Robo> robos;
    private final List<Obstaculo> obstaculos;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.robos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
    }

    public boolean isPosicaoValida(int x, int y) {
        return x >= 0 && x < tamanho && y >= 0 && y < tamanho;
    }

    public boolean temRocha(int x, int y) {
        Obstaculo obs = getObstaculoEm(x, y);
        return obs instanceof Rocha;
    }

    public void adicionarRobo(Robo robo) { robos.add(robo); }
    public void adicionarObstaculo(Obstaculo obs) { obstaculos.add(obs); }
    public void removerObstaculo(Obstaculo obs) { obstaculos.remove(obs); }

    public void setFruta(int x, int y) {
        if (isPosicaoValida(x, y)) {
            this.frutaX = x;
            this.frutaY = y;
        }
    }

    public Obstaculo getObstaculoEm(int x, int y) {
        for (Obstaculo o : obstaculos) {
            if (o.getX() == x && o.getY() == y) return o;
        }
        return null;
    }

    public boolean verificarVitoria(Robo robo) {
        return robo.getX() == frutaX && robo.getY() == frutaY;
    }

    public int getTamanho() { return tamanho; }
    public int getFrutaX() { return frutaX; }
    public int getFrutaY() { return frutaY; }
    public List<Robo> getRobos() { return robos; }
    public List<Obstaculo> getObstaculos() { return obstaculos; }
}