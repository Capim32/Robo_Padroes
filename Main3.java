import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Tabuleiro tabuleiro = new Tabuleiro();
        Robo roboNormal = new Robo("azul");
        RoboInteligente roboInteligente = new RoboInteligente("vermelho");
        List<Robo> robos = Arrays.asList(roboNormal,roboInteligente);

        System.out.println("cenário 3: Robô normal vs Robô inteligente");

        int frutaX = Tabuleiro.obterCoordenadaValida(scanner, "Digite a coordenada X da fruta");
        int frutaY = Tabuleiro.obterCoordenadaValida(scanner, "Digite a coordenada Y da fruta");
        tabuleiro.setFruta(frutaX, frutaY);
        
        System.out.println(" Pressione Enter para continuar...");
        scanner.nextLine();

        boolean rNaoAchou = true;
        boolean iNaoAchou = true;
        int turno = 0;
        
        while (rNaoAchou || iNaoAchou) {
            turno++;
            Robo roboAtual = robos.get(turno % 2); // Alterna entre os robôs
            
            if ((roboAtual == roboNormal && rNaoAchou) || (roboAtual == roboInteligente && iNaoAchou)) {
                
                int oldX = roboAtual.getX();
                int oldY = roboAtual.getY();
                int direcao = random.nextInt(4) + 1; // range de 1 a 4

                if (roboAtual instanceof RoboInteligente) {
                    ((RoboInteligente)roboAtual).mover(direcao); // o robo inteligente trata a excessao internamente por causa do metodo de movimento
                }
                else {
                    try{ // o robo normal lanca a excessao aqui
                        roboAtual.mover(direcao);
                    } catch (MovimentoInvalidoException e) {}
                }
                
                // não há colisão nesse cenário, mas para manter a uniformidade do código...
                tabuleiro.verificarColisao(roboAtual, oldX, oldY);

                tabuleiro.imprimir(robos);

                if (roboAtual.seAlimentou(frutaX, frutaY)) {
                    if (roboAtual == roboNormal) {
                        rNaoAchou = false;
                        System.out.println("robô normal encontrou a fruta!");
                    }
                    else if (roboAtual == roboInteligente) {
                        iNaoAchou = false;
                        System.out.println("robô inteligente encontrou a fruta!");
                    }
                }
            }

            Tabuleiro.pausar(1000);
        }

        System.out.println("fim do jogo!");

        int totalMovNormal = roboNormal.getMovimentosInvalidos() + roboNormal.getMovimentosInvalidos();
        int totalMovInteligente = roboInteligente.getMovimentosInvalidos() + roboInteligente.getMovimentosInvalidos();

        System.out.println("Robô Normal (" + roboNormal.getCorRobo() + ") Total de Tentativas (V+I): " + totalMovNormal);
        System.out.println("Robô Inteligente (" + roboInteligente.getCorRobo() + ") Total de Tentativas (V+I): " + totalMovInteligente);

        if (totalMovNormal < totalMovInteligente) {
            System.out.println("O Robô Normal foi o mais eficiente (menor número de tentativas).");
        } else if (totalMovInteligente < totalMovNormal) {
            System.out.println("O Robô Inteligente foi o mais eficiente (menor número de tentativas).");
        } else {
            System.out.println("Ambos empataram em número de tentativas.");
        }
        
        scanner.close();
    }
}
