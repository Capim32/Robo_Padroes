import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Tabuleiro tabuleiro = new Tabuleiro();
        String corRobo1 = "", corRobo2 = "";
        int cor1, cor2;

        Mensagens.boasVindas();

        // infelizmente lembrei de fazer isso de ultima hora, tá meio porco
        do {
            Mensagens.perguntaCorRobo();
            System.out.print("robô Normal: ");
            cor1 = scanner.nextInt();
            if (cor1 > 0 && cor1 < 5) {
                switch (cor1) {
                    case 1: corRobo1 = "VERMELHO"; break;
                    case 2: corRobo1 = "AZUL"; break;
                    case 3: corRobo1 = "AMARELO"; break;
                    case 4: corRobo1 = "VERDE"; break;
                    default: System.out.println("código de cor válido");break;
                }
            }
            else {System.out.println("código de cor inválido, tente novamente");}
            
            
        } while (cor1 <1 || cor1 > 4);

        do {
            Mensagens.perguntaCorRobo();
            System.out.print("robô Inteligente: ");
            cor2 = scanner.nextInt();
            if (cor2 > 0 && cor2 < 5) {
                switch (cor2) {
                    case 1: corRobo2 = "VERMELHO"; break;
                    case 2: corRobo2 = "AZUL"; break;
                    case 3: corRobo2 = "AMARELO"; break;
                    case 4: corRobo2 = "VERDE"; break;
                    default: System.out.println("código de cor válido");break;
                }
            }
            else {System.out.println("código de cor inválido, tente novamente");}
            
            
        } while (cor2 <1 || cor2 > 4);

        Robo roboNormal = new Robo(corRobo1);
        Robo roboInteligente = new Robo(corRobo2);
        List<Robo> robos = Arrays.asList(roboNormal, roboInteligente);
        tabuleiro.adicionarRobo(roboNormal);
        tabuleiro.adicionarRobo(roboInteligente);

        System.out.println("Main 3: Robô normal vs Robô inteligente");

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
                tabuleiro.isPosicaoOcupadaPorRobo(roboAtual, oldX, oldY);

                tabuleiro.imprimir(robos);

                if (roboAtual.seAlimentou(frutaX, frutaY)) {
                    if (roboAtual == roboNormal) {
                        rNaoAchou = false;
                        System.out.println(Robo.ANSI_GREEN + "robô normal encontrou a fruta!" + Robo.ANSI_RESET);
                    }
                    else if (roboAtual == roboInteligente) {
                        iNaoAchou = false;
                        System.out.println(Robo.ANSI_GREEN + "robô inteligente encontrou a fruta!" + Robo.ANSI_RESET);
                    }
                }
            }

            Tabuleiro.pausar(500);
        }

        System.out.println(Robo.ANSI_YELLOW + "fim do jogo!" + Robo.ANSI_RESET);

        int totalMovNormal = roboNormal.getMovimentosInvalidos() + roboNormal.getMovimentosInvalidos();
        int totalMovInteligente = roboInteligente.getMovimentosInvalidos() + roboInteligente.getMovimentosInvalidos();

        System.out.println("Robô Normal [" + roboNormal.getCorANSI() + roboNormal.getCorRobo() + roboNormal.ANSI_RESET +"] Total de Tentativas (V+I): " + totalMovNormal);
        System.out.println("Robô Inteligente [" + roboInteligente.getCorANSI() + roboInteligente.getCorRobo() + roboInteligente.ANSI_RESET +"] Total de Tentativas (V+I): " + totalMovInteligente);

        if (totalMovNormal < totalMovInteligente) {
            System.out.println(Robo.ANSI_GREEN + "O Robô Normal foi o mais eficiente (menor número de tentativas)." + Robo.ANSI_RESET);
        } else if (totalMovInteligente < totalMovNormal) {
            System.out.println(Robo.ANSI_GREEN + "O Robô Inteligente foi o mais eficiente (menor número de tentativas)." + Robo.ANSI_RESET);
        } else {
            System.out.println("Ambos empataram em número de tentativas.");
        }
        
        scanner.close();
    }
}
