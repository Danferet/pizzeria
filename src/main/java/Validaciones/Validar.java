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

    public static boolean validarPizza(String pizza) {

        boolean correcto = true;

        for (int i = 0; i < pizza.length(); i++) {

            if (!Character.isAlphabetic(pizza.charAt(i))
                    && !Character.isDigit(pizza.charAt(i))
                    && pizza.charAt(i) != ' '
                    && pizza.isBlank()) {

                correcto = false;

            }
        }

        return correcto;
    }

    public static boolean validarPrecio(String precio){

        try{

            Float.parseFloat(precio);
                return true;

        }catch (NumberFormatException nfe){
            return false;
        }
    }
}
