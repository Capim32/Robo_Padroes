import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Mensagens.boasVindas();
        Mensagens.perguntaCorRobo();
        String corRobo = scanner.nextLine();
        Robo robo = new Robo(corRobo);
        Mensagens.perguntaPosicaoXFruta();
        int frutaX = scanner.nextInt();
        scanner.nextLine();
        Mensagens.perguntaPosicaoYFruta();
        int frutaY = scanner.nextInt();
        scanner.nextLine();

        System.out.println(frutaX + " " + frutaY);






    }
}
