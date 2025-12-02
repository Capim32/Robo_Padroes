package model;

import enums.Cor;
import enums.Direcao;

public class Robo extends Entidade {
    private boolean explodiu = false;
    private int movimentosValidos = 0;
    private int movimentosInvalidos = 0;

    public Robo(Cor cor) {
        super(0, 0, "R", cor); 
    }

    public boolean mover(Direcao direcao, Tabuleiro tabuleiro) {
        if (explodiu) return true; // se explodiu, passa a vez (retorna true para não papocar o jogo)

        int novoX = this.x + direcao.getDx();
        int novoY = this.y + direcao.getDy();

        // verifica se está dentro do mapa antes de mover
        if (!tabuleiro.isPosicaoValida(novoX, novoY)) {
            this.movimentosInvalidos++;
            return false;
        }

        // realiza o movimento
        this.x = novoX;
        this.y = novoY;
        this.movimentosValidos++;
        return true;
    }

    public boolean isExplodiu() { return explodiu; }
    public void setExplodiu(boolean explodiu) { this.explodiu = explodiu; }
    public int getMovimentosValidos() { return movimentosValidos; }
    public int getMovimentosInvalidos() { return movimentosInvalidos; }
}