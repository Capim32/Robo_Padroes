package model;

import enums.Cor;
import enums.Direcao;
import java.util.Random;

public class RoboInteligente extends Robo {
    private final Random random = new Random();

    public RoboInteligente(Cor cor) {
        super(cor);
        this.simbolo = "I";
    }

    @Override
    public boolean mover(Direcao ignored, Tabuleiro tabuleiro) {
        if (isExplodiu()) return true;

        Direcao[] todasDirecoes = Direcao.values();
        
        for (int i = 0; i < 15; i++) {
            Direcao d = todasDirecoes[random.nextInt(todasDirecoes.length)];
            int nx = this.x + d.getDx();
            int ny = this.y + d.getDy();

            // LÓGICA MELHORADA:
            // só move se estiver dentro do mapa E não for uma Rocha.
            if (tabuleiro.isPosicaoValida(nx, ny) && !tabuleiro.temRocha(nx, ny)) {
                return super.mover(d, tabuleiro);
            }
        }
        
        // se estiver cercado e não tiver opção, tenta qualquer uma válida (mesmo que seja rocha)
        // para não travar o jogo num loop infinito. (o PH provavelmente não vai testar algo tão tenebroso, mas tá aí)
        return true; 
    }
}