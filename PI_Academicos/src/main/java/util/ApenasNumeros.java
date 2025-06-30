package util;

public class ApenasNumeros {

    public static boolean isNumeros(String texto) {
        return texto != null && texto.matches("\\d+");

    }
}
