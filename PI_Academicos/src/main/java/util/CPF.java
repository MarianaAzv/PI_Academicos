package util;

//Falta verificar se esse cpf ja existe no banco de dados
public class CPF {

    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }
        cpf = cpf.replace(".", "").replace("-", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int d1 = 0, d2 = 0;
            for (int i = 0; i < 9; i++) {
                int dig = cpf.charAt(i) - '0';
                d1 += (10 - i) * dig;
                d2 += (11 - i) * dig;

            }
            int rest1 = d1 % 11;
            int dig1 = (rest1 < 2) ? 0 : 11 - rest1;

            d2 += 2 * dig1;
            int rest2 = d2 % 11;
            int dig2 = (rest2 < 2) ? 0 : 11 - rest2;

            String dv = cpf.substring(9);
            String calculated = String.valueOf(dig1) + String.valueOf(dig2);

            return dv.equals(calculated);

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
