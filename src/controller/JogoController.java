package controller;

import enums.Cor;
import enums.Direcao;
import factory.EntidadeFactory;
import model.*;
import view.ConsoleView;

// classe "maestro", gerencia o fluxo do jogo, (Controller no padrão MVC)

public class JogoController {
    private final Tabuleiro tabuleiro;
    private final ConsoleView view;
    private boolean jogando = true;
    private static final int TAMANHO_MAPA = 4;
    private static final int TICKRATE_MS = 1000; 

    public JogoController() {
        this.view = new ConsoleView();
        this.tabuleiro = new Tabuleiro(TAMANHO_MAPA); 
    }

    public void iniciar() {
        while (true) {
            int opcao = mostrarMenuPrincipal();
            if (opcao == 0) break;
            
            tabuleiro.getRobos().clear();
            tabuleiro.getObstaculos().clear();
            jogando = true;
            
            configurarModoDeJogo(opcao);
            loopPrincipal();
            
            mostrarRelatorioFinal();
        }
        view.mostrarMensagem("Saindo... Obrigado por jogar!");
    }

    private void mostrarRelatorioFinal() {
        view.mostrarMensagem("\n=== RELATÓRIO DA PARTIDA ===");
        for (Robo r : tabuleiro.getRobos()) {
            String nome = r.getClass().getSimpleName().replace("Robo", "Robô ");
            view.mostrarMensagem(r.getCor().getAnsiCode() + nome + " (" + r.getSimbolo() + ")" + Cor.RESET.getAnsiCode());
            view.mostrarMensagem("   -> Movimentos Válidos: " + r.getMovimentosValidos());
            view.mostrarMensagem("   -> Movimentos Inválidos/Bloqueados: " + r.getMovimentosInvalidos());
            if (r.isExplodiu()) view.mostrarMensagem("   -> Status: EXPLODIU");
            else if (tabuleiro.verificarVitoria(r)) view.mostrarMensagem("   -> Status: VENCEDOR");
            else view.mostrarMensagem("   -> Status: Normal");
        }
        view.mostrarMensagem("============================");
    }

    private int mostrarMenuPrincipal() {
        view.mostrarMensagem("\n=================================================");
        view.mostrarMensagem("       MENU PRINCIPAL - JOGO DO ROBÔ (TARTARUGA)");
        view.mostrarMensagem("===================================================");
        view.mostrarMensagem("1 - Usuário x Robô Randômico");
        view.mostrarMensagem("2 - Usuário x Robô Inteligente");
        view.mostrarMensagem("3 - Robô Randômico x Robô Inteligente");
        view.mostrarMensagem("4 - Robô Inteligente x Robô Inteligente");
        view.mostrarMensagem("0 - Sair");
        return view.lerInteiro("Escolha uma opção");
    }

    private void configurarModoDeJogo(int modo) {
        int tipoRobo1 = 1;
        int tipoRobo2 = 1;
        switch (modo) {
            case 1: tipoRobo1 = 1; tipoRobo2 = 3; break; 
            case 2: tipoRobo1 = 1; tipoRobo2 = 2; break; 
            case 3: tipoRobo1 = 3; tipoRobo2 = 2; break; 
            case 4: tipoRobo1 = 2; tipoRobo2 = 2; break; 
            default: view.mostrarErro("Modo inválido.");
        }
        tabuleiro.adicionarRobo(EntidadeFactory.criarRobo(tipoRobo1, 1)); 
        tabuleiro.adicionarRobo(EntidadeFactory.criarRobo(tipoRobo2, 2)); 
        configurarAmbiente();
    }

    private void configurarAmbiente() {
        // config frutinha
        boolean frutaOk = false;
        while (!frutaOk) {
            int x = view.lerInteiro("X da Fruta (0-3)");
            int y = view.lerInteiro("Y da Fruta (0-3)");
            if (tabuleiro.isPosicaoValida(x, y) && !(x==0 && y==0)) {
                tabuleiro.setFruta(x, y);
                frutaOk = true;
            } else {
                view.mostrarErro("Posição inválida para fruta.");
            }
        }

        // configuração de obstáculos
        boolean continuar = true;
        while (continuar) {
            view.mostrarMensagem("\n[1] Add Bomba | [2] Add Rocha | [0] Iniciar Jogo");
            int op = view.lerInteiro("Opção");
            if (op == 0) {
                continuar = false;
            } else if (op == 1 || op == 2) {
                int x = view.lerInteiro("X Obstáculo");
                int y = view.lerInteiro("Y Obstáculo");
                if (tabuleiro.isPosicaoValida(x, y) && 
                    !(x==0 && y==0) && 
                    !(x==tabuleiro.getFrutaX() && y==tabuleiro.getFrutaY()) &&
                    tabuleiro.getObstaculoEm(x, y) == null) {
                        
                    Obstaculo obs = (op == 1) ? new Bomba(x, y) : new Rocha(x, y);
                    tabuleiro.adicionarObstaculo(obs);
                    view.mostrarMensagem("Obstáculo adicionado.");
                } else {
                    view.mostrarErro("Posição inválida ou ocupada.");
                }
            }
        }
    }

    private void loopPrincipal() {
        view.desenharTabuleiro(tabuleiro);

        while (jogando) {
            for (Robo robo : tabuleiro.getRobos()) {
                if (robo.isExplodiu()) continue;

                int oldX = robo.getX();
                int oldY = robo.getY();
                boolean turnoRealizado = false;
                boolean isCPU = (robo instanceof RoboInteligente || robo instanceof RoboRandomico);

                while (!turnoRealizado) {
                    if (isCPU) {
                        aplicarTickrate();
                        boolean moveu = robo.mover(null, tabuleiro);
                        String tipoBot = (robo instanceof RoboInteligente) ? "Inteligente" : "Randômico";
                        if (moveu) view.mostrarMensagem(">> " + tipoBot + " moveu.");
                        else view.mostrarMensagem(">> " + tipoBot + " bateu/inválido!");
                        turnoRealizado = true;
                    } else {
                        String prompt = "Sua vez (" + robo.getCor().getAnsiCode() + robo.getSimbolo() + Cor.RESET.getAnsiCode() + ") [1-UP, 2-DOWN, 3-LEFT, 4-RIGHT]:";
                        Direcao dir = parseDirecao(view.lerString(prompt));
                        if (dir != null) {
                            if (robo.mover(dir, tabuleiro)) turnoRealizado = true;
                            else view.mostrarErro("Movimento inválido!");
                        } else view.mostrarErro("Direção inválida.");
                    }
                }

                verificarColisoes(robo, oldX, oldY);
                view.desenharTabuleiro(tabuleiro);

                if (tabuleiro.verificarVitoria(robo)) {
                    view.mostrarMensagem("\nVITÓRIA do " + robo.getSimbolo() + "!");
                    jogando = false;
                    return;
                }
            }
            if (tabuleiro.getRobos().stream().allMatch(Robo::isExplodiu)) {
                view.mostrarMensagem("GAME OVER.");
                jogando = false;
            }
        }
    }

    private void aplicarTickrate() {
        try { Thread.sleep(TICKRATE_MS); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    private void verificarColisoes(Robo robo, int oldX, int oldY) {
        Obstaculo obs = tabuleiro.getObstaculoEm(robo.getX(), robo.getY());
        if (obs != null) {
            view.mostrarMensagem("!!! COLISÃO " + obs.getSimbolo() + " !!!");
            obs.aplicarEfeito(robo);
            if (obs instanceof Rocha) {
                robo.setX(oldX);
                robo.setY(oldY);
                // o controller lida com o recuo, mas a inteligência do robô já evitou cair aqui na maioria das vezes.
            } else if (obs instanceof Bomba) {
                tabuleiro.removerObstaculo(obs);
            }
        }
    }

    private Direcao parseDirecao(String entrada) {
        switch (entrada.toLowerCase()) {
            case "1": case "up": case "w": return Direcao.UP;
            case "2": case "down": case "s": return Direcao.DOWN;
            case "3": case "left": case "a": return Direcao.LEFT;
            case "4": case "right": case "d": return Direcao.RIGHT;
            default: return null;
        }
    }
}