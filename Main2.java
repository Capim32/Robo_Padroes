
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main2 {
    private static final int TAMANHO_MAPA = 4;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        Tabuleiro tabuleiro = new Tabuleiro();
        Robo robo1 = new Robo("Vermelho");
        Robo robo2 = new Robo("Azul");
        List<Robo> robos = Arrays.asList(robo1, robo2);

        Mensagens.boasVindas();
        System.out.println("Main 2: 2 robôs randômicos");
        System.out.println("Ambos os robôs foram iniciados nas coordenadas (0,0).");

        /* 
        int frutaX = -1, frutaY = -1;
        while (frutaX < 0 || frutaY < 0 || frutaX >= TAMANHO_MAPA || frutaY >= TAMANHO_MAPA) {
            try {
                System.out.print("\nDefina a posição X do alimento (0 a 4): ");
                frutaX = scanner.nextInt();
                System.out.print("Defina a posição Y do alimento (0 a 4): ");
                frutaY = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite apenas números inteiros.");
                scanner.nextLine();
            }

        }
        */
        int frutaX = Tabuleiro.obterCoordenadaValida(scanner, "coordenada X da fruta: ");
        int frutaY = Tabuleiro.obterCoordenadaValida(scanner, "coordenada Y da fruta: ");
        tabuleiro.setFruta(frutaX, frutaY);
        System.out.println("a fruta foi posicionada na coordenada: (" + frutaX + ", " + frutaY + ")");

        System.out.println("pressione enter para continuar...");
        scanner.nextLine();

        // loop principal do jogo
        Robo vencedor = null;
        int rodada = 0;
        while (vencedor == null) {
            rodada++ ;
            
            Robo roboAtual = robos.get(rodada %2);

            int oldX = roboAtual.getX();
            int oldY = roboAtual.getY();

            int movimentoRand = random.nextInt(4) + 1;

            try {
                roboAtual.mover(movimentoRand);
                tabuleiro.verificarColisao(roboAtual, oldX, oldY);
                tabuleiro.imprimir(robos);

                if (roboAtual.seAlimentou(frutaX, frutaY)) {vencedor = roboAtual;}
            } catch (MovimentoInvalidoException e) {}
            
            if (vencedor != null) {break;}

            Tabuleiro.pausar(500);            
        }

    System.out.println("\n--- Fim do Cenário 2 ---");
        System.out.println("O Robô VENCEDOR é o " + vencedor.getCorRobo() + "!");
        System.out.println("Ele encontrou o alimento em (" + vencedor.getX() + ", " + vencedor.getY() + ").");
        System.out.println("------------------------------------");
        
        for (Robo r : robos) {
            System.out.println("Robô " + r.getCorRobo() +": ");
            System.out.println("   -> Movimentos Válidos: " + r.getMovimentosValidos());
            System.out.println("   -> Movimentos Inválidos: " + r.getMovimentosInvalidos());
        }

        scanner.close();        
        
    }    
}
