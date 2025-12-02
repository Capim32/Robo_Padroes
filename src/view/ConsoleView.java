package view;

import enums.Cor;
import java.util.Scanner;
import model.Obstaculo;
import model.Robo;
import model.Tabuleiro;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }

    public void mostrarErro(String msg) {
        System.out.println(Cor.VERMELHO.getAnsiCode() + "ERRO: " + msg + Cor.RESET.getAnsiCode());
    }

    public int lerInteiro(String prompt) {
        System.out.print(prompt + ": ");
        while (!scanner.hasNextInt()) {
            mostrarErro("Entrada inválida. Digite um número.");
            scanner.next(); 
            System.out.print(prompt + ": ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); 
        return valor;
    }

    public String lerString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public void desenharTabuleiro(Tabuleiro tabuleiro) {
        System.out.println("\n--- TABULEIRO ---");
        int tam = tabuleiro.getTamanho();

        for (int y = tam - 1; y >= 0; y--) {
            System.out.print(y + "| ");
            for (int x = 0; x < tam; x++) {
                String simbolo = " . ";

                // 1. verifica frutia
                if (x == tabuleiro.getFrutaX() && y == tabuleiro.getFrutaY()) {
                    simbolo = Cor.VERDE.getAnsiCode() + " F " + Cor.RESET.getAnsiCode();
                }

                // 2. verifica obstáculo
                Obstaculo obs = tabuleiro.getObstaculoEm(x, y);
                if (obs != null) {
                    simbolo = obs.getCor().getAnsiCode() + " " + obs.getSimbolo() + " " + Cor.RESET.getAnsiCode();
                }

                // 3. Verifica Robô (sobrescreve visualmente os outros se estiver em cima pq enfim, não consegui fazer de modo que robôs colidissem entre si)
                for (Robo r : tabuleiro.getRobos()) {
                    if (r.getX() == x && r.getY() == y) {
                        if (r.isExplodiu()) {
                            simbolo = Cor.VERMELHO.getAnsiCode() + " X " + Cor.RESET.getAnsiCode();
                        } else {
                            simbolo = r.getCor().getAnsiCode() + " " + r.getSimbolo() + " " + Cor.RESET.getAnsiCode();
                        }
                    }
                }
                System.out.print(simbolo);
            }
            System.out.println();
        }
        System.out.println("   " + "-".repeat(tam * 3));
    }
}