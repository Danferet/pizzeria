package View;

import javax.swing.*;
import java.awt.*;

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

        agregarProducto.setBorder(BorderFactory.createLineBorder(Color.darkGray,5));
        agregarIngrediente.setBorder(BorderFactory.createLineBorder(Color.darkGray,5));
        eliminarIngrediente.setBorder(BorderFactory.createLineBorder(Color.darkGray,5));
        listaIngredientes.setBorder(BorderFactory.createLineBorder(Color.darkGray,5));

        //Al pulsar este botón se nos abrirá una nueva ventana que nos dará a elegir qué tipo de producto insertar
        agregarProducto.addActionListener(e-> {

                VentanaElegirProducto vep = new VentanaElegirProducto();
                vep.setVisible(true);

        });

        //Abrirá una ventana que nos permitirá agregar un ingrediente.
        agregarIngrediente.addActionListener(event->{

                VentanaAgregarIngrediente v2 = new VentanaAgregarIngrediente();
                v2.setVisible(true);

        });

        //Abrirá una ventana que nos permitirá eliminar un ingrediente.
        eliminarIngrediente.addActionListener(event->{

                VentanaEliminarIngrediente vei = new VentanaEliminarIngrediente();
                vei.setVisible(true);

        });

        //Abrirá una ventana que nos permitirá ver todos los ingredientes.
        listaIngredientes.addActionListener(event->{

                VentanaListaIngredientes vli = new VentanaListaIngredientes();
                vli.setVisible(true);

        });
    }
}