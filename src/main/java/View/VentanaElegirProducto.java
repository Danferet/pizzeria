package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaElegirProducto extends JFrame {

    public VentanaElegirProducto() {
        setLayout(null);
        setTitle("Elige entre pizza u otro tipo de producto");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, 390, 130);

        agregarComponentes();

    }

    public void agregarComponentes() {

        add(panel);
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);

        botonPizza.setBounds(30,30,150,30);
        botonOtros.setBounds(200,30,150,30);

        panel.add(botonPizza);
        panel.add(botonOtros);

        botonOtros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaAgregarProducto vap = new VentanaAgregarProducto();
                vap.setVisible(true);

                setVisible(false);

            }
        });

        botonPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaAgregarPizza vaPizza = new VentanaAgregarPizza();
                vaPizza.setVisible(true);

                setVisible(false);

            }
        });

    }

    JPanel panel = new JPanel();

    JButton botonPizza = new JButton("PIZZA");
    JButton botonOtros = new JButton("OTROS");


}






