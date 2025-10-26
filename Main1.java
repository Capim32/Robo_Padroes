import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main1 {
    public static final int TAMANHO_MAPA = 4;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        String corRobo = "";
        int cor;

        Mensagens.boasVindas();
        do {
            Mensagens.perguntaCorRobo();
            System.out.println("1) vermelho \n2) azul \n3) amarelo \n4) verde");
            cor = scanner.nextInt();
            if (cor > 0 && cor < 5) {
                switch (cor) {
                    case 1: corRobo = "VERMELHO"; break;
                    case 2: corRobo = "AZUL"; break;
                    case 3: corRobo = "AMARELO"; break;
                    case 4: corRobo = "VERDE"; break;
                    default: System.out.println("código de cor válido");break;
                }
            }
            else {System.out.println("código de cor inválido, tente novamente");}
            
            
        } while (cor <1 || cor > 4);

        // insere a cor do robo no texto, pretty cool, right?
        Robo robo = new Robo(corRobo);
        switch (corRobo) {
            case "VERMELHO": robo.ANSI_COR = robo.ANSI_RED; break;
            case "AZUL": robo.ANSI_COR = robo.ANSI_BLUE; break;
            case "AMARELO": robo.ANSI_COR = robo.ANSI_YELLOW; break;
            case "VERDE": robo.ANSI_COR = robo.ANSI_GREEN; break;
                
            default:
                throw new AssertionError();
        }


        System.out.println(robo.ANSI_COR  +"robo " + robo.getCorRobo() + " inicializado na posicao (0, 0)" + robo.ANSI_RESET);

        int frutaX = Tabuleiro.obterCoordenadaValida(scanner, "coordenada X da fruta: ");
        int frutaY = Tabuleiro.obterCoordenadaValida(scanner, "coordenada Y da fruta: ");
        tabuleiro.setFruta(frutaX, frutaY);
        System.out.println(robo.ANSI_GREEN + "a fruta foi posicionada na coordenada: (" + frutaX + ", " + frutaY + ")" + robo.ANSI_RESET);

        System.out.println("pressione enter para continuar...");
        scanner.nextLine();
        
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
        System.out.println("\nPARABÉNS! O Robô ["+ robo.ANSI_COR + robo.getCorRobo() + robo.ANSI_RESET + "] encontrou o alimento em ("+ robo.ANSI_GREEN + frutaX + ", " + frutaY + robo.ANSI_RESET +")!");
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
                    simbolo = robo.ANSI_GREEN + "F" + robo.ANSI_RESET; // Simbolo da fruta
                }
                
                // 2. Robô
                if (x == robo.getX() && y == robo.getY()) {
                    // Substitui o Alimento se o robô estiver em cima
                    simbolo = robo.ANSI_COR + robo.toString() + robo.ANSI_RESET; 
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
