package Validaciones;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    private static Color inputError = new Color(193, 104, 123);

    //Validación de nombres de productos, que no tienen numeros; o si está vacía.
    public static boolean validarString(String imput) {

        Pattern cadena = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+(\\s[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*$");

        Matcher m = cadena.matcher(imput);

        return m.matches();
    }

    //Toma el valor de validar Strings
    // y dependiendo del caso colorea la ventana de imput de blanco (true) o de rojo (false)
    public static void validarInputString(String imput, JTextField jtext) {

        if (!Validar.validarString(imput)) {
            jtext.setBackground(inputError);
        } else {
            jtext.setBackground(Color.WHITE);
        }
    }

    //Valida un String + numeros 0-9 y campo vacío
    public static boolean validarPizza(String pizza) {

        Pattern patronPizza = Pattern.compile("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]+(\\s[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]+)*$");

        Matcher m = patronPizza.matcher(pizza);

        return pizza.isEmpty() || m.matches();
    }

    //Torna rojo el campo si el formato no es válido, lo deja blanco si sí lo és, para cadena de validarPizza
    public static void validarInputStringPizza(String imput, JTextField jtext) {

        if (!Validar.validarPizza(imput)) {
            jtext.setBackground(inputError);
        } else {
            jtext.setBackground(Color.WHITE);
        }
    }

    //Valida si es un float o vacío
    public static boolean validarPrecio(String precio) {

        boolean floatCorrecto = false;

        try {

            //Trata de pasar a float, si lo consigue, el metodo retornará true,
            //de lo contrario, caerá en el catch y devolverá false
            Float.parseFloat(precio);
            floatCorrecto = true;

        } catch (NumberFormatException nfe) {
            System.out.println("Error al convertir el input a tipo float.");
        }

        //También valorará que el campo no esté vacío
        return floatCorrecto || precio.isEmpty();

    }

    //Torna rojo el campo si no es formato float
    public static void validarInputFloat(String imput, JTextField jtext) {

        if (!Validar.validarPrecio(imput)) {
            jtext.setBackground(inputError);
        } else {
            jtext.setBackground(Color.WHITE);
        }
    }

    //Valorará si lo que se introduce es un numero con formato Integer
    public static boolean validarNumero(String numero) {

        Pattern patronNumero = Pattern.compile("^(\\d+)*$");

        Matcher m = patronNumero.matcher(numero);

        return m.matches();
    }

    //Si no es el formato correcto tornará el campo rojo, si lo es, será blanco, para numero entero
    public static void validarInputNumero(String imput, JTextField jtext) {

        if (!Validar.validarNumero(imput)) {
            jtext.setBackground(inputError);
        } else {
            jtext.setBackground(Color.WHITE);
        }
    }
}