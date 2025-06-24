package View;

import Controller.ProductoRepositorio;
import Models.Producto;
import Validaciones.Validar;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//Con esta ventana agregaremso el producto específico pizza
public class VentanaAgregarPizza extends JFrame {

    //Loa componenetes de la ventana serán los siguientes:
    JPanel panel = new JPanel();
    JLabel insertarNombre = new JLabel("Nombre nuevo producto:");
    JTextField inputNombre = new JTextField();
    JLabel normal = new JLabel("Normal");
    JTextField inputPrecioNormal = new JTextField();
    JLabel familiar = new JLabel("Familiar");
    JTextField inputPrecioFamiliar = new JTextField();
    JButton aceptar = new JButton("ACEPTAR");
    static JLabel mensajeError = new JLabel("");

    //Características de tamaño y posición de la ventana
    public VentanaAgregarPizza() {

        setLayout(null);
        setTitle("Agregar una pizza.");
        setIconImage(new ImageIcon("src/assets/img/icono.png").getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
        setDefaultLookAndFeelDecorated(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, 460, 200);

        agregarComponentes();

    }

    //Agrego los componentes y les doy posición y las características
    public void agregarComponentes() {

        add(panel);
        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setLayout(null);

        insertarNombre.setBounds(20, 25, 150, 30);
        inputNombre.setBounds(20, 50, 150, 30);
        normal.setBounds(190, 25, 50, 30);
        inputPrecioNormal.setBounds(190, 50, 50, 30);
        familiar.setBounds(260, 25, 50, 30);
        inputPrecioFamiliar.setBounds(260, 50, 50, 30);
        mensajeError.setBounds(20, 90, 400, 60);
        aceptar.setBounds(330, 35, 100, 45);

        //Valorará si el dato introducido en la ventana es de tipo float, tornándose rojo de no ser así.
        inputPrecioFamiliar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecioFamiliar.getText(), inputPrecioFamiliar);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecioFamiliar.getText(), inputPrecioFamiliar);

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecioFamiliar.getText(), inputPrecioFamiliar);
            }
        });

        //Valorará si el dato introducido en al ventana es de tipo float, tornándose rojo de no ser así.
        inputPrecioNormal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecioNormal.getText(), inputPrecioNormal);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecioNormal.getText(), inputPrecioNormal);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Validar.validarInputFloat(inputPrecioNormal.getText(), inputPrecioNormal);
            }
        });

        //Valorará si tiene formato correcto de cadena de texto + números (4 quesos, por ejemplo).
        //Si no lo cumple se tornará rojo. Estos métodos validar también tienen en cuenta los espacios
        inputNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                Validar.validarInputStringPizza(inputNombre.getText(), inputNombre);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                Validar.validarInputStringPizza(inputNombre.getText(), inputNombre);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                Validar.validarInputStringPizza(inputNombre.getText(), inputNombre);
            }
        });

        //Al accionarse, si los datos introducidos en todos los campos tienen el formato correcto,
        //se introducirá un nuevo producto en la base de datos con las características descritas
        //en dichos campos

        aceptar.addActionListener((event) -> {

            //Es necesario validar que los campos son correctos para no introducir datos erróneos
            //Para ello se valida que cada tipo de dato introducido por el usuario corresponde
            //con el dato que espera el programa.
            boolean validarNombre = Validar.validarPizza(inputNombre.getText());
            boolean validarNormal = Validar.validarPrecio(inputPrecioNormal.getText());
            boolean validarFamiliar = Validar.validarPrecio(inputPrecioFamiliar.getText());

            //Ahora en este condicional le pasamos que si algo no es correcto, nos envíe un mensaje de error
            //Así, el usuario podrá cambiar los datos si es necesario
            if (!validarNombre ||
                    !validarNormal ||
                    !validarFamiliar ||
                    inputNombre.getText().trim().isEmpty() ||
                    inputPrecioNormal.getText().trim().isEmpty() ||
                    inputPrecioFamiliar.getText().trim().isEmpty()) {

                //El mensaje de error
                mensajeError.setForeground(Color.RED);
                mensajeError.setText("Todos los campos deben ser válidos");
                return;

            }

            //Si los datos están bien introducidos y hacemos clic, se instanciará un objeto de ProductoRepositorio
            //que llamará al metodo crear, lo que añadirá el producto a la base de datos.
            ProductoRepositorio pr = new ProductoRepositorio();

            //las pizzas no tienen número, por lo que este se quedará como 0
            pr.crear(new Producto("0",
                    inputNombre.getText(),
                    "pizza",
                    0,
                    Float.parseFloat(inputPrecioNormal.getText()),
                    Float.parseFloat(inputPrecioFamiliar.getText())));

            //Si se introduce en la base de datos sale mensaje de insertado correctamente en verde
            //y podemos introducir una nueva pizza, dejando los campos vacíos para que sea más sencillo.
            mensajeError.setForeground(Color.GREEN);
            mensajeError.setText("Elemento insertado correctamente");
            inputNombre.setText("");
            inputPrecioFamiliar.setText("");
            inputPrecioNormal.setText("");

        });

        //Se agregan los componentes al panel.
        panel.add(insertarNombre);
        panel.add(inputNombre);
        panel.add(normal);
        panel.add(inputPrecioNormal);
        panel.add(familiar);
        panel.add(inputPrecioFamiliar);
        panel.add(mensajeError);
        panel.add(aceptar);
    }
}