import controller.JogoController;

// a main se enquadra no padrão de projeto "Facade", fornecendo uma interface simples para iniciar o jogo
// o projeto no geral segue o padrão MVC (Model-View-Controller) e Factory para criação de entidades
public class Main {
    public static void main(String[] args) {
        JogoController jogo = new JogoController();
        jogo.iniciar();
    }
}