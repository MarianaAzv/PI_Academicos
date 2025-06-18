
package util;


public class Apenasletras {
    public static boolean isLetras(String text) {
        if (text.matches("[\\p{L}\\s'-]+")) {
            System.out.println("Contém apenas letras, espaços, hífens e apóstrofos");
            return true;
        } else {
            System.out.println("Tem caracteres inválidos");
            return false;
        }
    }
}
