package tartaruga;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main1 {
    public static final int TAMANHO_MAPA = 10;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Mensagens.boasVindas();
        Mensagens.perguntaCorRobo();
        String corRobo = scanner.nextLine();
        Robo robo = new Robo(corRobo);
        System.out.println("robo " + robo.getCorRobo() + " inicializado na posicao (0, 0)");
        
        int frutaX = -1, frutaY = -1;
        while (frutaX < 0 || frutaY < 0 || frutaX >= TAMANHO_MAPA || frutaY >= TAMANHO_MAPA) {
            try {
                System.out.print("\nDefina a posição X do alimento (0 a 9): ");
                frutaX = scanner.nextInt();
                System.out.print("Defina a posição Y do alimento (0 a 9): ");
                frutaY = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite apenas números inteiros.");
                scanner.nextLine();
            }

        }
        System.out.println("a fruta foi posicionada na coordenada: (" + frutaX + ", " + frutaY + ")");
        // loop principal do jogo
        while (!robo.seAlimentou(frutaX, frutaY)) {
            mostrarMapa(robo, frutaX, frutaY);

            Mensagens.posicaoRoboAtual(robo.getX(), robo.getY());
            System.out.print("Digite o movimento do robô (up, down, left, right) ou o número correspondente (1: up, 2: down, 3: right, 4: left): ");
            String movimentoInput = scanner.nextLine().trim();

            if (movimentoInput.isEmpty()) {System.out.println("nenhuma entrada.");continue;}

            try {
                try {
                    int movimentoInt = Integer.parseInt(movimentoInput);
                    robo.mover(movimentoInt);
                } catch (NumberFormatException e) {
                    robo.mover(movimentoInput);
                }
            } catch (MovimentoInvalidoException e) {
                    System.out.println("erro:" + e.getMessage());
            }

            try {
                TimeUnit.MILLISECONDS.sleep(50); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }

        mostrarMapa(robo, frutaX, frutaY);
        System.out.println("\nPARABÉNS! O Robô [" + robo.getCorRobo() + "] encontrou o alimento em (" + frutaX + ", " + frutaY + ")!");
        System.out.println("O robô realizou " + robo.getMovimentosValidos() + " movimentos válidos e " + robo.getMovimentosInvalidos() + " movimentos inválidos.");
        scanner.close();

    
    }

    public static void mostrarMapa(Robo robo, int frutaX, int frutaY) {
        System.out.println("\n--- Tabuleiro ---");
        // Loop 'y' de cima para baixo (coordenadas maiores para menores)
        for (int y = TAMANHO_MAPA - 1; y >= 0; y--) {
            System.out.print(y + "| ");
            for (int x = 0; x < TAMANHO_MAPA; x++) {
                String simbolo = "."; // Posição vazia
                
                // 1. Alimento
                if (x == frutaX && y == frutaY) {
                    simbolo = "@"; // Simbolo do alimento
                }
                
                // 2. Robô
                if (x == robo.getX() && y == robo.getY()) {
                    // Substitui o Alimento se o robô estiver em cima
                    simbolo = robo.toString(); 
                }
                
                System.out.print(simbolo + " ");
            }
            System.out.println();
        }
        // Linha de coordenadas X
        System.out.print("   ");
        for (int x = 0; x < TAMANHO_MAPA; x++) {
            System.out.print("-" + " ");
        }
        System.out.println();
        System.out.print("   ");
        for (int x = 0; x < TAMANHO_MAPA; x++) {
            System.out.print(x + " ");
        }
        System.out.println(); 

    }
}
