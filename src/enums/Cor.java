package enums;

public enum Cor {
    VERMELHO("\u001B[31m"),
    AZUL("\u001B[34m"),
    AMARELO("\u001B[33m"),
    VERDE("\u001B[32m"),
    RESET("\u001B[0m");

    private final String ansiCode;

    Cor(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return ansiCode;
    }
}
