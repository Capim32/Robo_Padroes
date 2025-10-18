

public class Mensagens {

    public static void boasVindas() {
        System.out.println("====== Bem vindo ao sistema do robô! ======");
    }
    public static void perguntaCorRobo() {
        System.out.println("Qual a cor do seu robô?");
    }
    public static void perguntaPosicaoXFruta() {
        System.out.println("Qual a posição X da fruta?");
    }
    public static void perguntaPosicaoYFruta() {
        System.out.println("Qual a posição Y da fruta?");
    }
    public static void posicaoFrutaInvalida() {
        System.out.println("Posição inválida! A fruta deve estar entre 0 e 10 em ambos os eixos.");
    }
    public static void posicaoRoboInvalida() {
        System.out.println("Posição inválida! O robô não pode sair do limite de 0 a 10 em ambos os eixos.");
    }
    public static void posicaoRoboAtual(int x, int y) {
        System.out.println("A posição atual do robô é: (" + x + ", " + y + ")");
    }
}
