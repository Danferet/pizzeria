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

        panel.add(titulo);

        panel.add(input);
        panel.add(aceptar);
        add(panel);
        input.setOpaque(true);
        panel.setOpaque(true);

        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarInput();
            }

            private void validarInput() {

                String palabra = input.getText();

                boolean validado = Validar.validarString(palabra);

                if (!validado) {
                    input.setBackground(new Color(193,104,123));
                } else {
                    input.setBackground(Color.WHITE);
                }
            }
        });

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ingrediente = input.getText();

                boolean valido = Validar.validarString(ingrediente);

                if (!valido) {

                    JOptionPane.showMessageDialog(
                            VentanaAgregarIngrediente.this,
                            "Tienes que introducir un ingrediente.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);

                }else{

                    IngredienteRepositorio ri = new IngredienteRepositorio();

                    List<Ingredientes> lista = ri.listar();

                    boolean existe = false;

                    for(int i = 0; i<lista.size(); i++){

                        if(lista.get(i).getNombre().equalsIgnoreCase(ingrediente)){

                            existe = true;
                            break;
                        }

                    }

                    if(!existe) {

                        ri.crear(new Ingredientes(ingrediente));

                    }else {

                        JOptionPane.showMessageDialog(
                                VentanaAgregarIngrediente.this,
                                "El ingrediente ya está en la lista.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);

                    }
                    input.setText("");
                }
            }
        });
    }

    JPanel panel = new JPanel();
    JLabel titulo = new JLabel("Inserta el ingrediente que quieres añadir a la BBDD.");
    JTextField input = new JTextField();
    JButton aceptar = new JButton("ACEPTAR");
}