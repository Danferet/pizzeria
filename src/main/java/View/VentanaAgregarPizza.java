package View;

import javax.swing.*;
import java.awt.*;

public class VentanaAgregarPizza extends JFrame {

    JPanel panel = new JPanel();
    JLabel insertarNombre = new JLabel("Nombre nuevo producto:");
    JTextField inputNombre = new JTextField();

    public VentanaAgregarPizza(){

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, ancho / 2, alto / 2);

        agregarComponentes();

    }

    public void agregarComponentes() {

        add(panel);
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);

        insertarNombre.setBounds(20, 25, 150, 30);
        inputNombre.setBounds(20,50,150,30);

        panel.add(insertarNombre);
        panel.add(inputNombre);

    }

    public String[] listaTipos(){

        String[] listaTipos =
                {"-Tipo-", "Bocadillo", "Entrepanes",
                        "Smash", "Burger", "Compartir","Postre"};

        return  listaTipos;
    }
}




