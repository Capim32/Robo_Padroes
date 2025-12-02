package factory;

import enums.Cor;
import model.Robo;
import model.RoboInteligente;
import model.RoboRandomico;

public class EntidadeFactory {
    public static Robo criarRobo(int tipo, int codigoCor) {
        Cor corSelecionada;
        switch (codigoCor) {
            case 1: corSelecionada = Cor.VERMELHO; break;
            case 2: corSelecionada = Cor.AZUL; break;
            case 3: corSelecionada = Cor.AMARELO; break;
            case 4: corSelecionada = Cor.VERDE; break;
            default: corSelecionada = Cor.AZUL;
        }

        switch (tipo) {
            case 2: return new RoboInteligente(corSelecionada);
            case 3: return new RoboRandomico(corSelecionada);
            default: return new Robo(corSelecionada);
        }
    }
}