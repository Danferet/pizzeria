package View;

import Controller.ClientesRepositorio;
import Models.Cliente;
import Validaciones.Validar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class VentanaAgregarCliente extends JFrame {

    //Creo el panel que dará forma al contenido de la ventana,
    //los elementos los añado con el metodo agregarComponentes();
    JPanel panel = new JPanel();

    //Constructor de la ventana que recibe el nombre, la configuración y los elementos
    public VentanaAgregarCliente() {

        super("Agregar nuevo cliente");
        configurarVentana();
        agregarComponentes();
    }

    //Configuración de la ventana, con tamaños, posición, visibilidad y acción al cerrar.
    private void configurarVentana() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
        pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4, alto / 4, 455, 250);
    }

    //Agrego los elementos
    public void agregarComponentes() {

        //Se añade el panel y se le asigna un layout nulo para colocar los elementos a mi gusto.
        add(panel);
        panel.setLayout(null);

        //Una etiqueta de "nombre"
        JLabel nombre = new JLabel("Nombre", SwingConstants.CENTER);
        nombre.setBounds(10, 10, 300, 30);

        //Campo para introducir el nombre del cliente
        JTextField nombreInput = new JTextField(10);
        nombreInput.setBounds(10, 40, 300, 30);

        //Etiqueta con la palabra dirección.
        JLabel direccion = new JLabel("Dirección", SwingConstants.CENTER);
        direccion.setBounds(10, 70, 300, 30);

        //Campo para introducir la dirección del cliente.
        JTextField direccionInput = new JTextField(10);
        direccionInput.setBounds(10, 100, 300, 30);

        //Etiqueta con la palabra teléfono
        JLabel telefono = new JLabel("Teléfono", SwingConstants.CENTER);
        telefono.setBounds(330, 10, 100, 30);

        //Campo para introducir el teléfono del cliente.
        JTextField telefonoInput = new JTextField(10);
        telefonoInput.setBounds(330, 40, 100, 30);

        //Evento para recoger lo introducido en el campo telefonoInput y validarlo visualmente.
        //Además, si esta ventana está en rojo significa que el teléfono no es válido
        //y al clicar el botón "Agregar" no ocurrirá el resultado esperado.
        telefonoInput.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                Validar.colorearTelefonoErroneo(telefonoInput.getText(), telefonoInput);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validar.colorearTelefonoErroneo(telefonoInput.getText(), telefonoInput);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validar.colorearTelefonoErroneo(telefonoInput.getText(), telefonoInput);
            }
        });

        //Creo y sitúo el botón
        JButton agregar = new JButton("Agregar");
        agregar.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        agregar.setBackground(Color.gray);
        agregar.setBounds(330, 100, 100, 30);

        //Un apartado mensaje que nos dará información sobre si los campos están bien rellenos,
        //sobre si se ha podido insertar el cliente o si no se ha podido y por qué.
        JLabel mensaje = new JLabel("Rellena todos los datos", SwingConstants.CENTER);
        mensaje.setBounds(10, 150, 430, 30);

        //Añado la acción del botón
        agregar.addActionListener(event -> {

            //Creo un objeto de tipo Cliente con sus atributos necesario: nombre, teléfono y dirección.
            Cliente cliente = new Cliente(
                    nombreInput.getText(),
                    telefonoInput.getText(),
                    direccionInput.getText());

            //Creo una instancia del repositorio de clientes y relleno una lsita de clientes
            //Esto será para constrastar los teléfonos,
            //y así no poder poner a dos clientes con el mismo teléfono
            ClientesRepositorio<Cliente> cr = new ClientesRepositorio<>();
            List<Cliente> listaClientes = cr.listar();

            //Agrego un mensaje en caso de que el nombreInput se quede vacío.
            //El mensaje retornará al mensaje original pasados dos segundos.
            if (nombreInput.getText().equalsIgnoreCase("")) {

                mensaje.setForeground(Color.RED);
                mensaje.setText("Te falta rellenar el nombre.");

                Timer timer = new Timer(2000, o -> {

                    mensaje.setForeground(Color.BLACK);
                    mensaje.setText("Rellena todos los datos.");
                });

                timer.setRepeats(false);
                timer.start();
                return;
            }

            //Creo unas variables booleanas para asegurarme de que si los datos introducidos en los campos
            //no poder pasar el cliente a la base de datos.
            boolean nombreValido = Validar.validarString(cliente.getNombre());
            boolean telefonoValido = Validar.validarNumeroTelefono(cliente.getTelefono());
            boolean existe = false;

            //Recorremos la lista para saber si el teléfono ya está asignado a algún cliente.
            for (Cliente listaCliente : listaClientes) {

                if (listaCliente.getTelefono().equalsIgnoreCase(cliente.getTelefono())) {

                    existe = true;
                    break;
                }
            }

            //Si todas nuestras condiciones son correctas, el programa procede a insertar el cliente.
            if ((nombreValido &&
                    telefonoValido &&
                    !cliente.getDireccion().equalsIgnoreCase("")) &&
                    !existe) {

                //Utilizo el método crear de la clas ClienteRepositorio para introducir el cliente
                cr.crear(cliente);
                nombre.setText("");
                telefono.setText("");
                direccion.setText("");

            //Si alguna de las condiciones no es true:
            } else {

                //Cambio el color de la letra a rojo
                mensaje.setForeground(Color.RED);

                //Si el teléfono ya existe en la base de datos:
                if (existe) {
                    mensaje.setText("El teléfono ya tiene un cliente asignado.");
                    Timer timer = new Timer(2000, ee -> {
                        mensaje.setForeground(Color.black);
                        mensaje.setText("Debes rellenar todos los campos.");

                    });
                    timer.setRepeats(false);
                    timer.start();
                    //Si es un error derivado de que faltan datos o hay datos erróneos:
                } else {
                    mensaje.setText("Debes rellenar todos los campos con datos válidos.");
                }
            }
        });

        //Se agregan todos los componentes al panel.
        panel.add(nombre);
        panel.add(telefono);
        panel.add(direccion);
        panel.add(nombreInput);
        panel.add(telefonoInput);
        panel.add(direccionInput);
        panel.add(mensaje);
        panel.add(agregar);
    }
}