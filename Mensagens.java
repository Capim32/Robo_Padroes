

public class Mensagens {

    public static void boasVindas() {
        System.out.println("====== Bem vindo ao sistema do robô! ======");
    }
    public static void modoDeJogo() {
        System.out.println("Escolha o modo de jogo: \n 1) 1 robô controlado pelo usuário \n 2) 2 robôs movidos randomicamente");
    }
    public static void perguntaCorRobo() {
        System.out.println("Qual a cor do seu robô? \n1) vermelho \n2) azul \n3) amarelo \n4) verde");
    } 
    public static void posicaoFruta(int frutaX, int frutaY) {
        System.out.println("A fruta foi posicionada na coordenada: (" + frutaX + ", " + frutaY + ")");
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
