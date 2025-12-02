package model;

import enums.Cor;
import enums.Direcao;
import java.util.Random;

public class RoboRandomico extends Robo {
    private final Random random = new Random();

    public RoboRandomico(Cor cor) {
        super(cor);
        this.simbolo = "A"; // R de random ficaria estranho, vai A de aleatório
    }

    @Override
    public boolean mover(Direcao ignored, Tabuleiro tabuleiro) {
        if (isExplodiu()) return true;

        Direcao[] todasDirecoes = Direcao.values();
        Direcao d = todasDirecoes[random.nextInt(todasDirecoes.length)];

        int nx = this.x + d.getDx();
        int ny = this.y + d.getDy();

        // tenta mover. Se for inválido, o método da classe pai retorna false e conta como erro.
        // diferente do Inteligente, ele NÃO TENTA DE NOVO NO MESMO TURNO.
        if (tabuleiro.isPosicaoValida(nx, ny)) {
            return super.mover(d, tabuleiro);
        } else {
            // computa o erro mas não move
            super.mover(d, tabuleiro); 
            return false;
        }
    }
}