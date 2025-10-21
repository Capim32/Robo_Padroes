
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main2 {
    private static final int TAMANHO_MAPA = 5;
    Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Robo robo1 = new Robo("vermelho");
        Robo robo2 = new Robo("Azul");

        System.out.println("Dois robôs inicializados: " + robo1.getCorRobo() + " (V) e " + robo2.getCorRobo() + " (A).");

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
        System.out.println("a fruta foi posicionada na coordenada: (" + frutaX + ", " + frutaY + ")");
        
        // loop principal do jogo


        

    }
}
