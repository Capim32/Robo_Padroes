import java.util.Random;
// o movimento aleatorio foi removido, era só sobrescrever o movimento 

public class RoboInteligente extends Robo {
    private final Random random = new Random();

    private int ultimoMovimentoInvalido = 0;

    public RoboInteligente(String corRobo) {super(corRobo);}

    @Override
    public void mover(int movimento) {
        if (explodiu) {return;}

        int tentativa = movimento;
        Random random = new Random();

        do { 
            if (tentativa == ultimoMovimentoInvalido) {
                do { 
                    tentativa = random.nextInt(4) + 1;
                } while (tentativa == ultimoMovimentoInvalido);
            }

            try {
                super.mover(tentativa);
                // se a excessao nao for lancada, o ultimo movimento é válido
                ultimoMovimentoInvalido = 0; // reseta o último inválido
                break;
            } catch (MovimentoInvalidoException e) {
                ultimoMovimentoInvalido = tentativa;

                do { 
                    tentativa = random.nextInt(4) +1;
                } while (tentativa == ultimoMovimentoInvalido);
            }
        } while (true); // continua até sair um movimento válido nessa desgraça
    }
    
}
