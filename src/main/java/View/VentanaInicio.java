package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Esta ventana será la primera del programa y la que muestra los botones para ir a las otras ventanas donde realizar
//las diferentes acciones del programa
public class VentanaInicio extends JFrame {

    //Panel y botones. Cada botón abre una ventana distinta
    JPanel panel = new JPanel();
    JButton agregarProducto = new JButton("NUEVO PRODUCTO");
    JButton agregarIngrediente = new JButton("NUEVO INGREDIENTE");
    JButton eliminarIngrediente = new JButton("ELIMINAR INGREDIENTE");
    JButton listaIngredientes = new JButton("LISTA DE INGREDIENTES");

    public VentanaInicio() {

        setLayout(null);
        setTitle("Pizzería!!");
        setIconImage(new ImageIcon("src/assets/img/icono.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4, alto / 4, ancho / 2, alto / 2);
        agregarComponentes();

    }

    public void agregarComponentes() {

        add(panel);
        panel.setLayout(null);
        panel.setBounds(0,0,getWidth(),getHeight());

        agregarProducto.setBounds(50,50,200,50);
        agregarIngrediente.setBounds(300,50,200,50);
        eliminarIngrediente.setBounds(550,50,200,50);
        listaIngredientes.setBounds(50,150,200,50);

        panel.add(agregarProducto);
        panel.add(agregarIngrediente);
        panel.add(eliminarIngrediente);
        panel.add(listaIngredientes);

        agregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaElegirProducto vep = new VentanaElegirProducto();
                vep.setVisible(true);

            }
        });

        agregarIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaAgregarIngrediente v2 = new VentanaAgregarIngrediente();
                v2.setVisible(true);

            }
        });

        eliminarIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaEliminarIngrediente vei = new VentanaEliminarIngrediente();
                vei.setVisible(true);

            }
        });

        listaIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaListaIngredientes vli = new VentanaListaIngredientes();
                vli.setVisible(true);

            }
        });
    }
}