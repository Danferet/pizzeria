package Validaciones;

public class Validar {

    public static boolean validarString(String palabra) {

        boolean correcto = true;

        for (int i = 0; i < palabra.length(); i++) {

            if (!Character.isLetter(palabra.charAt(i)) && palabra.charAt(i) != ' ' && palabra.isBlank()) {
                correcto = false;
                break;
            }


        }

        return correcto;
    }

}
