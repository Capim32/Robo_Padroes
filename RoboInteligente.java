//package tartaruga;

import java.util.Random;

public class RoboInteligente extends Robo {
    private final Random random = new Random();

    private String ultimoMovimentoInvalido = "";

    public RoboInteligente(String corRobo) {super(corRobo);}

    private void moverAleatoriamente() {
       String novaDirecao;
       boolean movido = false;

        while(!movido) {
            int direcaoInt = random.nextInt(4) + 1;
            switch (direcaoInt) {
                case 1: novaDirecao = "up"; break;
                case 2: novaDirecao = "down"; break;
                case 3: novaDirecao = "left"; break;
                case 4: novaDirecao = "right"; break;
                default: novaDirecao = "up"; break;
            
            }
            if (novaDirecao.equalsIgnoreCase(this.ultimoMovimentoInvalido)) {continue;} // evitar repetir o movimento inválido

            try {
                System.out.println("O robô inteligente " + this.getCorRobo() + " está tentando mover para " + novaDirecao);
                super.mover(novaDirecao);
                movido = true;
                this.ultimoMovimentoInvalido = "";

            } catch (MovimentoInvalidoException ignored) {
                // se o movimento falhar (entrar em zona negativa ou colidir), tentar outro movimento

                System.out.println("movimento aleatório falhou, tentando novamente...");
            }

        }
    }

    @Override
    public void mover(String movimento) throws MovimentoInvalidoException {
        try {
            // se a direção atual for igual ao último movimento inválido, escolher outra direção
            if (movimento.equalsIgnoreCase(this.ultimoMovimentoInvalido)) {
                System.out.println("O robô inteligente " + this.getCorRobo() + " escolheu uma nova direção para evitar o obstáculo.");
                moverAleatoriamente();
                return; // sair do método após mover aleatoriamente            
            }
            super.mover(movimento);
            this.ultimoMovimentoInvalido = ""; // resetar o último movimento inválido se o movimento for bem-sucedido
        } catch(MovimentoInvalidoException e) {
            System.out.println("O robô inteligente " + this.getCorRobo() + " não conseguiu mover para " + movimento + " devido a um obstáculo ou limite.");
            this.ultimoMovimentoInvalido = movimento; // armazenar o movimento inválido
            moverAleatoriamente(); // tentar mover aleatoriamente
        }
    }
    
}
