package View;

import javax.swing.*;
import java.awt.*;

//Esta ventana ha cambiado para probar algunas cosas, pero volverá a ser cambiada para tener una posición fija
//para los botones que tiene, ya que en futuras implementaciones estos serán más y tendrá nuevas funcionalidades.

//Esta ventana será la primera del programa y la que muestra los botones para ir a las otras ventanas donde realizar
//las diferentes acciones del programa
public class VentanaInicio extends JFrame {

    Container contenedor;
    JButton agregarProducto = new JButton("NUEVO PRODUCTO");
    JButton agregarIngrediente = new JButton("NUEVO INGREDIENTE");
    JButton eliminarIngrediente = new JButton("ELIMINAR INGREDIENTE");
    JButton listaIngredientes = new JButton("LISTA DE INGREDIENTES");
    JButton agregarCliente = new JButton("AGREGAR CLIENTE");
    JButton pedido = new JButton("PEDIDO");

    public VentanaInicio() {
        super("Pizzería");

        //Agrego la configuración de la ventana y los componenetes
        configurarVentana();
        agregarComponentes();
        //La ventana se hará tan grande como necesite para que todos los elementos quepan en ella.
        pack();

    }

    private void agregarComponentes() {

        //Añado al contenedor los botones
        contenedor.add(agregarProducto);
        contenedor.add(agregarIngrediente);
        contenedor.add(eliminarIngrediente);
        contenedor.add(listaIngredientes);
        contenedor.add(agregarCliente);
        contenedor.add(pedido);

        //Doy algún formato a los botones
        agregarProducto.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        agregarIngrediente.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        eliminarIngrediente.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        listaIngredientes.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        agregarCliente.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        pedido.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));

        //Al pulsar este botón se nos abrirá una nueva ventana que nos dará a elegir qué tipo de producto insertar
        agregarProducto.addActionListener(e -> new VentanaElegirProducto().setVisible(true));

        //Abrirá una ventana que nos permitirá agregar un ingrediente.
        agregarIngrediente.addActionListener(event -> new VentanaAgregarIngrediente().setVisible(true));

        //Abrirá una ventana que nos permitirá eliminar un ingrediente.
        eliminarIngrediente.addActionListener(event -> new VentanaEliminarIngrediente().setVisible(true));

        //Abrirá una ventana que nos permitirá ver todos los ingredientes.
        listaIngredientes.addActionListener(event -> new VentanaListaIngredientes().setVisible(true));

        agregarCliente.addActionListener(event -> new VentanaAgregarCliente().setVisible(true));

        pedido.addActionListener(event -> new VentanaPedido().setVisible(true));
    }

    private void configurarVentana() {

        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        setTitle("Pizzería!!");
        setIconImage(new ImageIcon("src/assets/img/icono.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setLocation(ancho / 4, alto / 4);
    }
}