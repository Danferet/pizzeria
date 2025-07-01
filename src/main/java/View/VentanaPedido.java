package View;

import BBDD.Database;
import Controller.ProductoRepositorio;
import Models.*;
import Validaciones.Validar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VentanaPedido extends JFrame {

    //El panel principal que albergará el resto de paneles
    Container contenedor;
    //Una fuente para los botones de los productos
    Font botoncitos = new Font("Arial", Font.PLAIN, 10);
    //El campo para introducir el teléfono del cliente, que será por el que se le busque en la BBDD
    JTextField telefonoCliente = new JTextField(10);
    //El campo que mostrará los datos del cliente al introducir el teléfono en el campo anterior.
    JTextPane datosCliente = new JTextPane();
    //El panel donde estará el campo de los datos del cliente.
    JPanel panelCliente = new JPanel();
    //Los items se iran guardando en esta lista
    List<Item> listaItems = new ArrayList<>();

    //Constructor de la ventana
    public VentanaPedido() {
        super("Pedido");

        configurarVentana();
        agregarComponentes();
    }

    private void agregarComponentes() {

        //Parte de arriba
        contenedor.setLayout(new BorderLayout());
        JPanel panelDeArriba = new JPanel(new GridLayout(1, 2, 5, 5));
        contenedor.add(panelDeArriba, BorderLayout.NORTH);

        JPanel panelTitulo = new JPanel(new BorderLayout(5, 5));
        JLabel carta = new JLabel("CARTA DE PEDIDOS", SwingConstants.CENTER);
        panelTitulo.add(carta, BorderLayout.CENTER);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTitulo.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

        /*
        Los siguientes elementos son del panel de arriba a la izquierda!!
         */

        //Botón que nos mostrará como va el tiket en este momento.
        JButton cesta = new JButton("Cesta");
        panelTitulo.add(cesta, BorderLayout.EAST);
        //Acción del botón:
        cesta.addActionListener(e -> {

            VentanaCesta vc = new VentanaCesta();
            vc.setVisible(true);

        });

        //Creo un botón que me reiniciará la cesta dejándola sin artículos
        JButton vaciarCesta = new JButton("Vaciar Cesta");
        panelTitulo.add(vaciarCesta, BorderLayout.WEST);
        //Acción del botón:
        vaciarCesta.addActionListener(e -> {

            listaItems.clear();
        });


        //Al panel del título, a la izquierda le añado el campo donde introduciremos el teléfono del cliente.
        panelTitulo.add(telefonoCliente, BorderLayout.SOUTH);
        telefonoCliente.setHorizontalAlignment(JTextField.CENTER);
        telefonoCliente.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                pintarCliente();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                pintarCliente();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                pintarCliente();
            }
        });

        //Finalmente Añado el panel del título al panel superior.
        panelDeArriba.add(panelTitulo);

        /*
        Los siguientes elementos son del panel de arriba a la derecha!!!
         */

        //Añado el panel donde se cargarán los datos del cliente si el teléfono está en la base de datos.
        panelDeArriba.add(panelCliente);

        //Añadimos el JTextPane de datos del cliente y le damos formato
        panelCliente.add(datosCliente);
        datosCliente.setFont(new Font("Arial", Font.BOLD, 20));

        /*
        A partir de aquí, el panel donde se van a visualizar las pizzas
         */

        //Cargo desde la base de datos los productos de tipo pizza y los ordeno alfabéticamente.
        List<Producto> listaPizzas = obtenerPizzas();
        listaPizzas.sort(Comparator.comparing(Producto::getNombre));

        //Creo el panel que va a ir en la parte central
        //que tendrá dos partes, una para pizzas y otra para el resto de productos
        JPanel panelCentral = new JPanel(new GridLayout(1, 2));
        contenedor.add(panelCentral);

        //Panel donde van a ir las pizzas, se coloca a la izquierda de la carta y tendrá un BorderLayout
        JPanel panelPizzas = new JPanel(new BorderLayout(5, 5));
        panelCentral.add(panelPizzas);

        //Titulo del panel de las pizzas
        JLabel tituloPizzas = new JLabel("PIZZAS", SwingConstants.CENTER);
        panelPizzas.add(tituloPizzas, BorderLayout.NORTH);

        //En la parte central del panel de las pizzas creo un layout de tipo cuadrícula para
        //ir añadiendo cada pizza con los botones correspondientes para añadirlas al pedido.
        JPanel nombresPizzas = new JPanel(new GridLayout(8, 5, 1, 1));
        panelPizzas.add(nombresPizzas, BorderLayout.CENTER);

        //Con este for relleno el panel con los nombres de las pizzas y los botones de tamaño
        for (int i = 0; i < listaPizzas.size(); i++) {

            //Cada pizza en cada posición de la cuadrícula tendrá su propio panel.
            //Cada pizza tendrá un borderLayout, en NORTH el nombre de la pizza -> nombre,
            //En el CENTER, una nueva cuadrícula con 1 fila y dos columnas, para los botones -> centroItem,

            //Creo el panel de cada pizza
            JPanel panelItemPizza = new JPanel(new BorderLayout(5, 5));
            nombresPizzas.add(panelItemPizza, BorderLayout.CENTER);
            panelItemPizza.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

            //Creo la etiqueta con el nombre de la pizza y la añado a la parte de arriba de su panel
            JLabel nombre = new JLabel(listaPizzas.get(i).getNombre(), SwingConstants.CENTER);
            panelItemPizza.add(nombre, BorderLayout.NORTH);

            //Creo el centro del panel y lo añado al centro del panel de la pizza.
            JPanel centroItem = new JPanel(new GridLayout(1, 2));
            panelItemPizza.add(centroItem, BorderLayout.CENTER);

            //Creo y añado un botón "Normal" y lo añado al primer cuadro de la cuadrícula central.
            JButton normal = new JButton("Normal");
            centroItem.add(normal, BorderLayout.CENTER);

            //Creo y añado el segundo botón, "Familiar" y lo añado al segundo cuadro de la cuadrícula central.
            JButton familiar = new JButton("Familiar");
            centroItem.add(familiar, BorderLayout.CENTER);

            //Doy un formato de fuente a los botones.
            normal.setFont(botoncitos);
            familiar.setFont(botoncitos);

            //Al crearse los botones de tamaño normal de las pizzas
            //se les añade la función de que agregan el elemento correspondiente a la cesta.
            //Si el mismo elemento ya está en la cesta, le añaden 1 a la cantidad del item.
            normal.addActionListener(en -> {

                String nombrePizza = nombre.getText();

                //Recorro la lista de pizzas hasta que encuentra la que queremos añadir
                for (int j = 0; j < listaPizzas.size(); j++) {

                    //Cuando la encuentra, crea un item para el tamaño que queremos: normal.
                    if (listaPizzas.get(j).getNombre().equalsIgnoreCase(nombrePizza)) {

                        //Se crea el Item, que requiere el producto, el tamaño, la cantidad(1) y el precio.
                        Item item = new Item(listaPizzas.get(j),
                                TAMANIO.NORMAL,
                                1,
                                listaPizzas.get(j).getPrecioNormal());

                        boolean existe = false;
                        //Una vez que tenemos el item, verificamos si ya teníamos otro item similar en el pedido
                        for (int k = 0; k < listaItems.size(); k++) {

                            Item comparando = listaItems.get(k);
                            if (comparando.equals(item)) {

                                item.setCantidad(comparando.getCantidad() + 1);

                                listaItems.set(k, item);
                                existe = true;
                            }
                        }
                        //Si el item no existe, lo añadimos
                        if (!existe) {

                            listaItems.add(item);
                        }
                    }
                }
            });

            //Sigue la misma dinámica que el otro botón, solo que utiliza el tamaño Familiar
            //y el precio de la pizza familiar
            familiar.addActionListener(en -> {

                String nombrePizza = nombre.getText();

                //Recorro la lista de pizzas hasta que encuentra la que queremos añadir
                for (int j = 0; j < listaPizzas.size(); j++) {

                    //Cuando la encuentra, crea un item para el tamaño que queremos: familiar.
                    if (listaPizzas.get(j).getNombre().equalsIgnoreCase(nombrePizza)) {

                        //Se crea el Item, que requiere el producto, el tamaño, la cantidad(1) y el precio.
                        Item item = new Item(listaPizzas.get(j),
                                TAMANIO.FAMILIAR,
                                1,
                                listaPizzas.get(j).getPrecioFamiliar());

                        boolean existe = false;
                        //Una vez que tenemos el item, verificamos si ya teníamos otro item similar en el pedido
                        for (int k = 0; k < listaItems.size(); k++) {

                            Item comparando = listaItems.get(k);
                            if (comparando.equals(item)) {

                                item.setCantidad(item.getCantidad() + 1);

                                listaItems.set(k, item);
                                existe = true;
                            }
                        }
                        //Si el item no existe, lo añadimos
                        if (!existe) {

                            listaItems.add(item);
                        }
                    }
                }
            });
        }

        /*
        Hasta aquí es rellenar la parte central de los productos tipo pizza
         */

        //Creo el panel de las hamburguesas, pero en realidad aquí van a ir todos los productos que no sean pizzas
        //Se va a ir rellenando desde la base de datos, además teniendo en cuenta.
        JPanel panelHamburguesas = new JPanel();
        panelHamburguesas.setLayout(new FlowLayout());
        panelCentral.add(panelHamburguesas, BorderLayout.CENTER);

        //Añado el título para el panel de las hamburguesas y lo añado.
        JLabel tituloHamburguesas = new JLabel("HAMBURGUESAS", SwingConstants.CENTER);
        panelHamburguesas.add(tituloHamburguesas);

        //Todos los productos que no son pizzas todavía se tienen que añadir a la BBDD y a su panel
    }

    //Configuración de la VentanaPedido
    private void configurarVentana() {

        contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout(5, 5));

        setTitle("Pizzería!!");
        setIconImage(new ImageIcon("src/assets/img/icono.png").getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setVisible(true);

    }

    //Obtengo todas las pizzas de la base de datos para luego poder mostrarlas en la ventana
    private List<Producto> obtenerPizzas() {

        ProductoRepositorio pr = new ProductoRepositorio();

        return pr.listar("pizza");
    }

    //Obtengo el cliente de la BBDD búscándolo por el teléfono y lo coloco en la parte superior derecha
    //mostrándo sus datos: nombre, teléfono y dirección
    public void pintarCliente() {

        String telefono = telefonoCliente.getText();

        //Si el teléfono que nos proporciona el cliente no existe, habilitará un botón
        //que al pulsarlo abrirá la ventana VentanaAgregarCliente
        JButton botonAgregarClienteNuevo = new JButton("Nuevo cliente");
        panelCliente.add(botonAgregarClienteNuevo);
        botonAgregarClienteNuevo.setVisible(false);
        botonAgregarClienteNuevo.addActionListener(e -> {

            VentanaAgregarCliente vac = new VentanaAgregarCliente();
            vac.setVisible(true);
        });

        //Creo una instancia vacía de cliente
        Cliente cliente = new Cliente();

        //Si el teléfono insertado tiene formato válido se buscará en la base de datos
        if (Validar.validarNumeroTelefono(telefono)) {

            boolean telefonoEncontrado = false;

            String sentencia = "SELECT nombre, telefono, direccion FROM cliente WHERE telefono = ?";

            try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                ps.setString(1, telefono);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    cliente = new Cliente(
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion"));

                    telefonoEncontrado = true;

                }
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());
            }

            //Si encuentra el teléfono, se mostrarán los datos en la parte superior derecha.
            if (telefonoEncontrado) {
                datosCliente.setText(cliente.verDetalle());
                datosCliente.setBackground(Color.GRAY);
                //Si no existe en al base de datos, obtendremos el mensaje oportuno
            } else {
                datosCliente.setText("El teléfono no está registrado");

                //Y se habilitará el botón agregar nuevo cliente.
                botonAgregarClienteNuevo.setVisible(true);
            }

            //Mientras no haya nada escrito o no tenga el formato correcto,
            //no se mostrará nada en la ventana correspondiente
        } else {

            datosCliente.setText("");
            botonAgregarClienteNuevo.setVisible(false);
        }
    }

    //Calculo el total del ticket
    private float calcularTotal(List<Item> lista) {

        float total = 0f;

        for (int i = 0; i < lista.size(); i++) {

            total += lista.get(i).getTotal();
        }
        return total;
    }

    //Implemento una ventana con el resumen del tiket y un botón para confirmar el pedido
    private class VentanaCesta extends JFrame {

        //Creo los atributos necesarios
        Pizzeria pizzeria = new Pizzeria();
        Cliente cliente = obtenerCliente();
        JPanel panelCesta = new JPanel(new BorderLayout());
        JTextPane texto = new JTextPane();
        JButton confirmarPedido = new JButton("Confirmar pedido");

        //Añado configuración y componentes
        public VentanaCesta() {

            configurarVentana();
            agrearComponenetes();
        }

        //Configuración de la ventana
        private void configurarVentana() {

            setTitle("Pizzería!!");
            setIconImage(new ImageIcon("src/assets/img/icono.png").getImage());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(true);

            Toolkit tk = Toolkit.getDefaultToolkit();

            Dimension d = tk.getScreenSize();

            int alto = d.height;
            int ancho = d.width;

            setBounds(ancho / 3, 10, 500, 500);
        }

        //Creo y doy funcionalidad a los componentes de la ventana
        private void agrearComponenetes() {

            //Añadimos el panel donde se escribirá el ticket
            add(panelCesta);

            //Creo un TextPane donde se irán agregando las líneas del tiket
            texto = new JTextPane();

            //Se añade el TextPane al panel y se le da una posición
            panelCesta.add(texto, BorderLayout.CENTER);
            texto.setBounds(0, 0, 500, 500);

            //Agregamos al texto los datos del cliente y de la propia pizzería
            texto.setText(obtenerTiket(cliente, pizzeria));

            //añado al panel el botón de confirmar pedido
            panelCesta.add(confirmarPedido, BorderLayout.SOUTH);

            //Al pulsar en este botón, se insertarán en la base de datos el pedido y cada item asociado al pedido
            //El pedido tendrá el id del cliente, la fecha y el total del ticket.
            //Los items tendrán los atributos necesarios más el id del pedido.
            confirmarPedido.addActionListener(e -> {

                //Obtengo la hora, le doy formato español y lo paso a String
                LocalDateTime ahora = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String fechaFormateada = dtf.format(ahora);

                //Creo un objeto pedido con los datos que tenemos del cliente, la fecha y el total del tiket
                Pedido pedido = new Pedido(cliente, fechaFormateada, calcularTotal(listaItems));

                //Necesitaremos el id del cliente para la base de datos
                int idCliente = obtenerIdCliente(cliente);

                //Sentencia para insertar en la BBDD el nuevo pedido
                String sentencia = "INSERT INTO pedido (id_cliente, fecha, total) VALUES (?,?,?)";

                //Se ejecuta la sentencia
                try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                    ps.setInt(1, idCliente);
                    ps.setString(2, pedido.getFecha());
                    ps.setFloat(3, pedido.getTotal());

                    int resultado = ps.executeUpdate();

                } catch (SQLException sql) {
                    JOptionPane.showMessageDialog(
                            VentanaPedido.this,
                            "No se pudo insertar el pedido",
                            "Error de pedido",
                            JOptionPane.ERROR_MESSAGE);
                }

                //Una vez insertado el pedido, insertaremos los items con el id de ese pedido.
                int idPedido = obtenerIdPedido(fechaFormateada);

                //Como pueden ser varios los items, lo haremos con un bucle for:
                for (int i = 0; i < listaItems.size(); i++) {

                    //Obtendo el id de cada producto en el item, ya que lo necesitaré para obtener diferentes cosas
                    //Como el nombre del producto, su precio...
                    int id = obtenerIdProducto(listaItems.get(i).getProducto());

                    //la sentencia de inserción
                    String sentenciaItem =
                            "INSERT INTO item (id_producto, tamanio, precio, cantidad, precioTotal, id_pedido) " +
                                    "VALUES (?,?,?,?,?,?)";

                    //Se ejecuta la sentencia
                    try (PreparedStatement ps = Database.conectar().prepareStatement(sentenciaItem)) {

                        //Id del producto, lo hemos sacado con el método obtenerIdProducto
                        ps.setInt(1, id);
                        //Tamaño, lo obtenemos de la lista de items. si no tuviera tamaño (si no es pizza), quedará en null
                        ps.setString(2, String.valueOf(listaItems.get(i).getTamanio()));
                        //Precio unitario, lo obtenemos de la lista de items.
                        ps.setFloat(3, listaItems.get(i).getPrecio());
                        //Cantidad, lo obtenemos de la lista de items.
                        ps.setFloat(4, listaItems.get(i).getCantidad());
                        //Total del item, lo obtenemos multiplicando el precio del item por la cantidad del item
                        ps.setFloat(5, (listaItems.get(i).getPrecio() * listaItems.get(i).getCantidad()));
                        //Por último agregamos el id del pedido al que está asociado el item.
                        ps.setInt(6, idPedido);

                        int resultado = ps.executeUpdate();

                    } catch (SQLException sql) {
                        JOptionPane.showMessageDialog(
                                VentanaPedido.this,
                                "No se pudieron inesrtar",
                                "Error de items",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                telefonoCliente.setText("");
                listaItems.clear();
            });
        }

        //Este metodo nos dará la información del tiket, desde los datos de la pizzeria y del cliente
        //hasta cada item y el valor del total.
        private String obtenerTiket(Cliente cliente, Pizzeria pizzeria) {

            DecimalFormat df = new DecimalFormat("#.##");

            StringBuilder sb = new StringBuilder("Resumen del pedido")
                    .append("\n\n")
                    .append(pizzeria.verDetalle())
                    .append("\n\n")
                    .append("Cliente: ")
                    .append("\n")
                    .append(cliente.verDetalle())
                    .append("\n\n")
                    .append("Items:")
                    .append("\n");

            for (int i = 0; i < listaItems.size(); i++) {

                sb.append(listaItems.get(i).verDetalle())
                        .append("\n");
            }

            sb.append("\n")
                    .append("Total factura: ")
                    .append(df.format(calcularTotal(listaItems))).append(" €.");

            return sb.toString();
        }

        //Obtengo el cliente desde la base de datos

        /*
        El método obtener cliente habría que sacarlo a un método externo, ya que se utiliza dos veces
        prácticamente de la misma manera en estas dos ventanas.
         */
        private Cliente obtenerCliente() {

            String telefono = telefonoCliente.getText();

            Cliente cliente = new Cliente();

            //Si el telefono tiene formato válido, lo buscaremos en la BBDD
            if (Validar.validarNumeroTelefono(telefono)) {

                String sentencia = "SELECT nombre, telefono, direccion FROM cliente WHERE telefono = ?";

                try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                    ps.setString(1, telefono);

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        //Damos a la instancia de cliente sus datos
                        cliente = new Cliente(
                                rs.getString("nombre"),
                                rs.getString("telefono"),
                                rs.getString("direccion"));
                    }

                } catch (SQLException sql) {
                    System.out.println(sql.getMessage());
                }
            }
            return cliente;
        }
    }

    //Obtenemos de la BBDD el id del cliente.
    //Este solo lo tenemos en la BBDD por lo que no podemos obtenerlos de la clase
    //Si el cliente no está en la base de datos y no quiere dar sus datos,
    //El id quedará como 0 y contará como "Clientes no registrados" a la hora de hacer estadísticas.
    private int obtenerIdCliente(Cliente cliente) {

        int idCliente = 0;

        String sentencia = "SELECT id FROM cliente where telefono = ?";

        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            ps.setString(1, cliente.getTelefono());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                idCliente = rs.getInt("id");
            }

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
        return idCliente;
    }

    //Igual que con el cliente, tenemos que obtenerlo de la BBDD
    private int obtenerIdPedido(String fecha) {

        int id = 0;

        String sentencia = "SELECT id FROM pedido WHERE fecha = ?";

        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            ps.setString(1, fecha);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                id = rs.getInt("id");
            }

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
        return id;
    }

    //Debe obtenerse de nuevo de la base de datos.
    private int obtenerIdProducto(Producto producto) {

        int id = 0;

        String sentencia = "SELECT id FROM producto WHERE nombre = ?";

        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            ps.setString(1, producto.getNombre());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                id = rs.getInt("id");
            }

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
        return id;
    }
}