import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Tabuleiro {
    public static final int AREA_TABULEIRO = 4;

    private int frutaX;
    private int frutaY;

    private final List<Obstaculo> obstaculos;
    private int proximoIdObstaculo =1;

    public Tabuleiro() {this.obstaculos = new ArrayList<>();}

    public void setFruta(int x, int y) {this.frutaX = x; this.frutaY = y;}

    public boolean adicionarObstaculo(Obstaculo obstaculo) {
        if (obstaculo.getX() < 0 || obstaculo.getX() >= AREA_TABULEIRO || obstaculo.getY() < 0 || obstaculo.getY() >= AREA_TABULEIRO) {
            System.err.println("Erro: Obstáculo fora da área de jogo (0-" + (AREA_TABULEIRO - 1) + ").");
            return false;
        }
        if (obstaculo.getX() == frutaX && obstaculo.getY() == frutaY) {
            System.err.println("Erro: Obstáculo não pode estar na posição do fruta.");
            return false;
        }
        // for each para checar se ja existe um obstaculo no lugar
        for (Obstaculo o : obstaculos) {
            if (o.getX() == obstaculo.getX() && o.getY() == obstaculo.getY()) {
                System.err.println("Erro: Já existe um obstáculo na posição (" + obstaculo.getX() + ", " + obstaculo.getY() + ").");
                return false;
            }
        }
        this.obstaculos.add(obstaculo);
        proximoIdObstaculo++;
        return true;
    }

    // getter proximoIdObstaculo
    public int getProximoIdObstaculo() {return proximoIdObstaculo;}
    // usado para remover a bomba de campo ao explodir
    public void removerObstaculo(Obstaculo obstaculo) {this.obstaculos.remove(obstaculo);}


    public void verificarColisao(Robo robo, int oldX, int oldY) {
        if (robo.isExplodiu()) return;

        for (int i = 0; i < obstaculos.size(); i++) {
            Obstaculo obs = obstaculos.get(i);
            if (robo.getX() == obs.getX() && robo.getY() == obs.getY()) {
                obs.bater(robo, this, oldX, oldY);
                // caso o robo nao tenha explodido, ele pode ter ido pra posicao anterior
                if (robo.isExplodiu() || (robo.getX() == oldX && robo.getY() == oldY)) {return;}
            }
        }
    }

    public void imprimir(List<Robo> robos) {
        // Limpar tela (não universal, mas ajuda)
        try {
            // Comando para limpar o console (funciona em alguns sistemas/terminais)
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            // Ignora se não conseguir limpar
        }

        System.out.println("\n\n\n\n");
        System.out.println("------------------------------------");
        System.out.println("    TABULEIRO (" + (AREA_TABULEIRO - 1) + "x" + (AREA_TABULEIRO - 1) + ") - fruta em (" + frutaX + ", " + frutaY + ")");
        System.out.println("------------------------------------");

        // desenha a grade do tabuleiro
        for (int y = AREA_TABULEIRO - 1; y >= 0; y--) {
            System.out.print(" " + y + " |"); // Eixo Y
            for (int x = 0; x < AREA_TABULEIRO; x++) {
                String simbolo = "   "; // Padrão: espaço vazio

                // verifica a fruta
                if (x == frutaX && y == frutaY) {
                    simbolo = " F ";
                }

                // verifica os obstaculos
                Obstaculo obsNaPosicao = null;
                for (Obstaculo obs : obstaculos) {
                    if (obs.getX() == x && obs.getY() == y) {
                        obsNaPosicao = obs;
                        break;
                    }
                }
                if (obsNaPosicao != null) {
                    simbolo = obsNaPosicao.getSimbolo() + " ";
                }

                // 3. Verificar Robôs
                Robo roboNaPosicao = null;
                for (Robo r : robos) {
                    if (r.getX() == x && r.getY() == y) {
                        if (r.isExplodiu()) {
                            simbolo = " X "; 
                        } 
                        else {
                            if (roboNaPosicao != null) {
                                // Se há mais de um robô na mesma célula
                                simbolo =" @ "; // explosao cabum cablau 
                            } else {
                                // Se só há um robô
                                simbolo = " R ";
                            }
                        }
                        roboNaPosicao = r;
                    }
                }

                System.out.print(simbolo);
            }
            System.out.println("|");
        }

        // Desenhar eixo X
        System.out.print("   ");
        for (int i = 0; i < AREA_TABULEIRO; i++) {
            System.out.print("----");
        }
        System.out.println("-");
        System.out.print("   ");
        for (int i = 0; i < AREA_TABULEIRO; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.println("\n");
        
        // mostra as legendas
        System.out.println("Legenda:");
        System.out.print(" F  = fruta |");
        System.out.print(" B  = Bomba |");
        System.out.print(" P  = Pedra |");
        System.out.print(" X  = Robô Explodido |");
        System.out.print(" @  = Robôs Na mesma posicao |");
        System.out.println("R = Robô \n");
        
        for (Robo r : robos) {
            System.out.println("Robô " + r.getCorRobo() + ": Posição (" + r.getX() + ", " + r.getY() + ") " + (r.isExplodiu() ? "(EXPLODIDO)" : "") + " | Válidos: " + r.getMovimentosValidos() + ", Inválidos: " + r.getMovimentosInvalidos());
        }
        System.out.println("------------------------------------\n");
    }

    public static void pausar(int milisegundos) {
        try {
            TimeUnit.MILLISECONDS.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int obterCoordenadaValida(Scanner scanner, String mensagem) {
        int coord = -1;
        while (coord < 0 || coord >= AREA_TABULEIRO) {
            System.out.print(mensagem + " (0 a " + (AREA_TABULEIRO - 1) + "): ");
            if (scanner.hasNextInt()) {
                coord = scanner.nextInt();
                if (coord < 0 || coord >= AREA_TABULEIRO) {
                    System.out.println( "Erro: Coordenada fora do limite. Tente novamente.");
                }
            } else {
                System.out.println("Erro: Entrada inválida. Tente novamente.");
                scanner.next();
            }
        }
        return coord;
    }


}
