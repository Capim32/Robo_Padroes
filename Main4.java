import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Tabuleiro tabuleiro = new Tabuleiro();
        Robo roboNormal = new Robo("azul");
        RoboInteligente roboInteligente = new RoboInteligente("vermelho");
        List<Robo> robos = Arrays.asList(roboNormal,roboInteligente);

        System.out.println("cenário 4: Robô normal vs Robô inteligente (com obstáculos)\n");

        int frutaX = Tabuleiro.obterCoordenadaValida(scanner, "Digite a coordenada X da fruta");
        int frutaY = Tabuleiro.obterCoordenadaValida(scanner, "Digite a coordenada Y da fruta");
        tabuleiro.setFruta(frutaX, frutaY);

        inserirObstaculos(scanner, tabuleiro, frutaX, frutaY);
        
        System.out.println(" Pressione Enter para continuar...");
        scanner.nextLine();

        Robo vencedor = null;
        int turno = 0;

        while (vencedor == null && !roboNormal.isExplodiu() || !roboInteligente.isExplodiu()) {
            turno++;

            Robo roboAtual = robos.get(turno % 2); 
            
            if (!roboAtual.isExplodiu() && !roboAtual.seAlimentou(frutaX, frutaY)) {
                
                int oldX = roboAtual.getX();
                int oldY = roboAtual.getY();
                int direcao = random.nextInt(4) + 1;
                
                if (roboAtual instanceof RoboInteligente) {
                    ((RoboInteligente) roboAtual).mover(direcao);
                } else {
                    try {
                        roboAtual.mover(direcao);
                    } catch (MovimentoInvalidoException e) {}
                }
                
                // verifica a colisão APÓS a tentativa de movimento
                tabuleiro.verificarColisao(roboAtual, oldX, oldY);

                tabuleiro.imprimir(robos);

                // verifica se encontrou o alimento APÓS a colisão (pois a colisão pode ter revertido o movimento)
                if (roboAtual.seAlimentou(frutaX, frutaY)) {
                    vencedor = roboAtual;
                }
            }

            Tabuleiro.pausar(500);
        }
        System.out.println("fim do jogo!");

        if (vencedor != null) {
            System.out.println("O Robô VENCEDOR é o " + vencedor.getCorRobo() + "!");
            System.out.println("Ele encontrou o alimento em (" + vencedor.getX() + ", " + vencedor.getY() + ").");
        } else {
            System.out.println("Nenhum robô encontrou o alimento. Ambos explodiram! Fim do jogo.");
        }
        System.out.println("------------------------------------");
        
        for (Robo r : robos) {
            int totalTentativas = r.getMovimentosValidos() + r.getMovimentosInvalidos();
            System.out.println("Robô " + r.getCorRobo() + ":");
            //System.out.println("   -> Encerrou " + (r.isExplodiu() ? Robo.ANSI_RED + "EXPLODIDO" + Robo.ANSI_RESET : "ao encontrar o alimento."));
            System.out.println("   -> Total de Tentativas (V+I): " + totalTentativas);
        }
        scanner.close();
    }
    
    private static void inserirObstaculos(Scanner scanner, Tabuleiro tabuleiro, int frutaX, int frutaY) {
        System.out.println("\n--- Inserção de Obstáculos ---");
        String continuar = "S";
        scanner.nextLine();

        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("\nEscolha o tipo de obstáculo: 1) Bomba, 2) Rocha, 3) Parar");
            System.out.print("Opção: ");
            String tipo = scanner.nextLine().trim();
            
            if (tipo.equals("3")) {
                continuar = "N";
                break;
            } else if (tipo.equals("1") || tipo.equals("2")) {
                int obsX = -1, obsY = -1;
                boolean posicaoValida = false;
                
                while (!posicaoValida) {
                    try {
                        System.out.print("Digite a coordenada X do obstáculo (0 a " + (Tabuleiro.AREA_TABULEIRO - 1) + "): ");
                        obsX = scanner.nextInt();
                        System.out.print("Digite a coordenada Y do obstáculo (0 a " + (Tabuleiro.AREA_TABULEIRO - 1) + "): ");
                        obsY = scanner.nextInt();
                        scanner.nextLine(); // Limpa buffer
                        
                        // Garante que não está na posição (0,0) (inicial) ou alimento
                        if ((obsX == 0 && obsY == 0) || (obsX == frutaX && obsY == frutaY)) {
                            System.out.println("Obstáculo não pode estar na posição inicial (0,0) ou na posição do alimento.");
                            continue;
                        }
                        
                        Obstaculo novoObs = null;
                        if (tipo.equals("1")) {
                            novoObs = new Bomba(tabuleiro.getProximoIdObstaculo(), obsX, obsY);
                        } else {
                            novoObs = new Rocha(tabuleiro.getProximoIdObstaculo(), obsX, obsY);
                        }
                        
                        if (tabuleiro.adicionarObstaculo(novoObs)) {
                            posicaoValida = true;
                        } else {
                            // Se a adição falhou (posição inválida/ocupada), repete a leitura de coordenadas
                        }
                        
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Entrada inválida. Tente novamente.");
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("Opção inválida. Digite 1, 2 ou 3.");
            }
        }
    }

}
