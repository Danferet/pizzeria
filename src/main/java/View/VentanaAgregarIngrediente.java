package View;

import Controller.IngredienteRepositorio;
import Validaciones.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Models.*;

public class VentanaAgregarIngrediente extends JFrame {

    private Ingredientes ingrediente;


    JPanel panel = new JPanel();
    JLabel titulo = new JLabel("Inserta el ingrediente que quieres añadir a la BBDD.");
    JTextField input = new JTextField();
    JButton aceptar = new JButton("ACEPTAR");

    public VentanaAgregarIngrediente() {
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, 650, 150);

        agregarComponentes();
    }

    public void agregarComponentes() {

        panel.setLayout(null);
        panel.setBounds(0, 0, getWidth(), getHeight());

        titulo.setBounds(20, 30, 320, 30);
        input.setBounds(350, 30, 100, 30);

        aceptar.setBounds(500, 20, 100, 50);

        add(panel);
        panel.add(titulo);
        panel.add(input);
        panel.add(aceptar);

        input.setOpaque(true);
        panel.setOpaque(true);

        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                Validar.validarInputString(input.getText(),input);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                Validar.validarInputString(input.getText(),input);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                Validar.validarInputString(input.getText(),input);
            }


        });

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtengo el valor de lo que escribe el usuario en el input
                String ingrediente = input.getText();

                //Valido si es una palabra válida
                boolean valido = Validar.validarString(input.getText());

                //Si no es válida, mando un mensaje de error
                if (!valido) {

                    JOptionPane.showMessageDialog(
                            VentanaAgregarIngrediente.this,
                            "Tienes que introducir un ingrediente.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);

                //Si es válida, seguimos
                }else{

                    //Creo una instancia del repositorio de ingredientes
                    IngredienteRepositorio ri = new IngredienteRepositorio();

                    //Consigo de la base de datos una lista de los ingredientes que ya tengo
                    List<Ingredientes> lista = ri.listar();

                    //Miro si el ingrediente ya existe en mi lista del programa
                    boolean existe = false;

                    for (Ingredientes ingredientes : lista) {

                        if (ingredientes.getNombre().equalsIgnoreCase(ingrediente)) {

                            existe = true;
                            break;
                        }

                    }

                    //Si no existe:
                    if(!existe) {

                        //Añado el ingrediente a la lista
                        ri.crear(new Ingredientes(ingrediente));

                    //Si existe:
                    }else {

                        //Envío mensaje de error
                        JOptionPane.showMessageDialog(
                                VentanaAgregarIngrediente.this,
                                "El ingrediente ya está en la lista.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);

                    }

                    //vacío el input para que se pueda agregar un nuevo ingrediente más fácilmente
                    input.setText("");
                }
            }
        });
    }
}