
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
        Robo robo1 = new Robo("VERMELHO");
        Robo robo2 = new Robo("AZUL");
        List<Robo> robos = Arrays.asList(robo1, robo2);

        Mensagens.boasVindas();
        
        System.out.println("Main 2: 2 robôs randômicos");
        System.out.println( Robo.ANSI_BLUE + "Ambos os robôs foram iniciados nas coordenadas (0,0)." + Robo.ANSI_RESET);


        int frutaX = Tabuleiro.obterCoordenadaValida(scanner, "coordenada X da fruta: ");
        int frutaY = Tabuleiro.obterCoordenadaValida(scanner, "coordenada Y da fruta: ");
        tabuleiro.setFruta(frutaX, frutaY);
        System.out.println(Robo.ANSI_GREEN + "a fruta foi posicionada na coordenada: (" + frutaX + ", " + frutaY + ")" + Robo.ANSI_RESET);

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

            Tabuleiro.pausar(1000);            
        }

    System.out.println("\n--- Fim do Cenário 2 ---");
        System.out.println( Robo.ANSI_GREEN +"O Robô VENCEDOR é o " + vencedor.getCorRobo() + Robo.ANSI_RESET + "!");
        System.out.println("Ele encontrou o alimento em (" + vencedor.getX() + ", " + vencedor.getY() + ").");
        System.out.println("------------------------------------");
        
        for (Robo r : robos) {
            System.out.println("Robô " + r.ANSI_COR +  r.getCorRobo() + r.ANSI_RESET +": ");
            System.out.println("   -> Movimentos Válidos: " + r.getMovimentosValidos());
            System.out.println("   -> Movimentos Inválidos: " + r.getMovimentosInvalidos());
        }

        scanner.close();        
        
    }    
}
