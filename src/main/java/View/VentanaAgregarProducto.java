package View;

import Controller.ProductoRepositorio;
import Models.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VentanaAgregarProducto extends JFrame {


    public VentanaAgregarProducto() {
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, 635, 150);

        agregarComponentes();


    }

    public void agregarComponentes() {

        add(panel);

        insertarNombre.setBounds(20, 25, 150, 30);
        inputNombre.setBounds(20,50,150,30);
        tipo.setBounds(190,25,105,30);
        listaTipos.setBounds(190,50,105,30);
        numero.setBounds(315,25,55,30);
        inputNumero.setBounds(315,50,55,30);
        precio.setBounds(390,25,55,30);
        inputPrecio.setBounds(390,50,55,30);
        botonAgregar.setBounds(465,30,150,50);

        add(insertarNombre);
        add(inputNombre);
        add(tipo);
        add(listaTipos);
        add(numero);
        add(inputNumero);
        add(precio);
        add(inputPrecio);
        add(botonAgregar);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipo = "";
                Object seleccion = listaTipos.getSelectedItem();

                if(seleccion != null){
                    tipo = seleccion.toString();
                }

                ProductoRepositorio pr = new ProductoRepositorio();

                pr.crear(new Producto(inputNumero.getText(),
                        inputNombre.getText(),
                        tipo,
                        Float.parseFloat(inputPrecio.getText()),
                        0.0f,
                        0.0f));

                inputNombre.setText("");inputPrecio.setText("");inputNumero.setText("");
                listaTipos.setSelectedIndex(0);

            }
        });

    }

    private String[] listaTipos(){
        return new String[]{"-Tipo-", "Bocadillo", "Entrepanes",
                "Smash", "Burger", "Compartir","Postre"};
    }

    JPanel panel = new JPanel();

    JLabel insertarNombre = new JLabel("Nombre nuevo producto:");

    JTextField inputNombre = new JTextField();

    JLabel tipo = new JLabel("Tipo de producto:");

    JComboBox<String> listaTipos = new JComboBox<>(listaTipos());

    JLabel numero = new JLabel("Número:");

    JTextField inputNumero = new JTextField();

    JLabel precio = new JLabel("Precio");

    JTextField inputPrecio = new JTextField();

    JButton botonAgregar = new JButton("Agregar producto");


}




