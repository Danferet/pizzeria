package View;

import Controller.ProductoRepositorio;
import Models.Producto;
import Validaciones.Validar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

//Esta ventana aparecerá si en la ventana de inicio pulsamos el botón "NUEVO PRODUCTO"
//y después, al pulsar el botón "OTROS"
public class VentanaAgregarProducto extends JFrame {

    //Los diferentes componentes de la ventana.
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
    JLabel mensaje = new JLabel("");

    //Con esta ventana vamos a agregar todos los productos que no son pizzas
    public VentanaAgregarProducto() {

        //Características, tamaño y posición de la ventana
        setLayout(null);
        setTitle("Agregar un producto (no pizzas)");
        setIconImage(new ImageIcon("src/assets/img/icono.png").getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, 635, 200);

        agregarComponentes();
    }

    public void agregarComponentes() {

        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setLayout(null);
        add(panel);

        //Posición y tamaño de cada componente
        insertarNombre.setBounds(20, 25, 150, 30);
        inputNombre.setBounds(20, 50, 150, 30);
        tipo.setBounds(190, 25, 105, 30);
        listaTipos.setBounds(190, 50, 105, 30);
        numero.setBounds(315, 25, 55, 30);
        inputNumero.setBounds(315, 50, 55, 30);
        precio.setBounds(390, 25, 55, 30);
        inputPrecio.setBounds(390, 50, 55, 30);
        botonAgregar.setBounds(465, 30, 150, 50);
        mensaje.setBounds(20, 100, 400, 30);

        //Si en la ventana que recoge el nombre del producto no tiene el formato correcto,
        //la ventana se mostrará de color rojo.
        inputNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //Valida que lo que le estamos pasando es una cadena de caracteres alfabéticos.
                Validar.validarInputString(inputNombre.getText(), inputNombre);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validar.validarInputString(inputNombre.getText(), inputNombre);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validar.validarInputString(inputNombre.getText(), inputNombre);
            }
        });

        //Si el precio no tiene formato float, la ventana se pondrá de color rojo.
        inputPrecio.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecio.getText(), inputPrecio);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecio.getText(), inputPrecio);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecio.getText(), inputPrecio);
            }
        });

        //Si el numero no tiene formato integer, la ventana se pondrá de color rojo.
        inputNumero.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Validar.validarInputNumero(inputNumero.getText(), inputNumero);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validar.validarInputNumero(inputNumero.getText(), inputNumero);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validar.validarInputNumero(inputNumero.getText(), inputNumero);
            }
        });

        //El botón pondrá un elemento nuevo en la base de datos
        //si los campos están correctamente rellenos.
        botonAgregar.addActionListener(event -> {

            //El tipo lo vamos a obtener del desplegable. Hay que pasar de object a String
            String tipo = Objects.requireNonNull(listaTipos.getSelectedItem()).toString();

            //Validamos que el desplegable no pueda ser -Tipo-
            if (tipo.equals("-Tipo-")) {
                mensaje.setForeground(Color.RED);
                mensaje.setText("Tienes que elegir un tipo válido.");
                return;
            }

            //Valido que el resto de campos no esté vacío o que tengan un tipo de dato que no sea correcto.
            if (inputNombre.getText().isEmpty() ||
                    inputNumero.getText().isEmpty() ||
                    inputPrecio.getText().isEmpty() ||
                    !Validar.validarPrecio(inputPrecio.getText()) ||
                    !Validar.validarNumero(inputNumero.getText())) {

                //Mostrará un mensaje de error.
                mensaje.setForeground(Color.RED);
                mensaje.setText("Todos los campos son obligatorios. Observa el formato.");
                return;
            }

            //Una vez que tengo el tipo, ya solo es necesario llamar al repositorio de producto
            ProductoRepositorio pr = new ProductoRepositorio();

            //Como no es una pizza, tenemos que pasarle el producto que escogimos en el desplegable.
            //El resto de campos necesarios son el número, nombre y precio.
            pr.crear(new Producto(inputNumero.getText().trim(),
                    inputNombre.getText().trim(),
                    tipo,
                    Float.parseFloat(inputPrecio.getText().trim()),
                    0.0f,
                    0.0f));
            //PrecioNormal y precioFamiliar son solo para las pizzas, así que quedan en 0.0f.
            //Cuando se describan estos productos, esos valores no aparecerán.

            //limpio los campos de inputs para que sea más fácil añadir nuevos datos
            inputNombre.setText("");
            inputPrecio.setText("");
            inputNumero.setText("");
            listaTipos.setSelectedIndex(0);
            mensaje.setForeground(Color.GREEN);
            mensaje.setText("Producto insertado correctamente");

        });

        //Agrego los componentes al panel
        panel.add(insertarNombre);
        panel.add(inputNombre);
        panel.add(listaTipos);
        panel.add(numero);
        panel.add(inputNumero);
        panel.add(precio);
        panel.add(inputPrecio);
        panel.add(botonAgregar);
        panel.add(mensaje);
    }

    //Los tipos de productos que pueden tener los diferentes platos de la pizzería
    private String[] listaTipos() {
        return new String[]{"-Tipo-", "Bocadillo", "Entrepanes",
                "Smash", "Burger", "Compartir", "Postre"};
    }
}




