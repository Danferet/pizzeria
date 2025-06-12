package View;

import BBDD.Database;
import Controller.IngredienteRepositorio;
import Models.Ingredientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VentanaEliminarIngrediente extends JFrame {


    //Creo todos los elementos
    JLabel titulo = new JLabel("Busca el ingrediente que quieres eliminar");
    JLabel filtro = new JLabel("Filtro:");
    JTextField input = new JTextField();
    JLabel tituloIngredientes = new JLabel("Ingredientes:");
    JButton aceptar = new JButton("ACEPTAR");
    JPanel panel = new JPanel(null);

    //Desde la BBDD se carga la lista de ingredientes y se llena este array,
    //de forma que no tengamos que volver a la BBDD de nuevo si queremos eliminar 2 o varios elementos
    List<String> listaOriginal = obtenerLista();

    //La lista inicial se rellena con la lista original
    JComboBox<String> ingredientes = new JComboBox<>(listaOriginal.toArray(new String[0]));

    public VentanaEliminarIngrediente() {

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4, alto / 4, 510, 200);
        agregarComponentes();

    }

    public void agregarComponentes() {

        add(panel);
        panel.setBounds(0,0, getWidth(),getHeight());

        panel.add(titulo);
        panel.add(filtro);
        panel.add(input);
        panel.add(tituloIngredientes);
        panel.add(ingredientes);
        panel.add(aceptar);

        titulo.setBounds(20,20,600,30);

        filtro.setBounds(20,70,100,30);
        input.setBounds(20,100,100,30);

        //Aquí creamos un filtro. La lista de ingredientes es basatante larga,
        //así que el usuario podrá introducir una letra o varias,
        //de forma que si algún ingrediente contiene lo que escriba en ese filtro,
        //la lista solo mostrará esos resultados
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            //Al introducir caracteres se actualiza la lista
            public void insertUpdate(DocumentEvent e) {
                filtrarIngredientes();
            }

            @Override
            //al borrar caracteres se actualzia la lista
            public void removeUpdate(DocumentEvent e) {
                filtrarIngredientes();
            }

            @Override
            //al hacer otros cambios también se actualizaría la lista
            public void changedUpdate(DocumentEvent e) {
                filtrarIngredientes();
            }
        });

        tituloIngredientes.setBounds(150,70,200,30);
        ingredientes.setBounds(150,100,200,30);

        aceptar.setBounds(380,80,100,50);
        aceptar.addActionListener(new ActionListener() {
            @Override

            //AL hacer click en aceptar, se eliminará el elemento que esté seleccionado
            public void actionPerformed(ActionEvent e) {

                //Se crea una instancia del repositorio de ingredientes.
                IngredienteRepositorio ir = new IngredienteRepositorio();

                //Capturamos el item
                String seleccionado = String.valueOf(ingredientes.getSelectedItem());

                //Creamos el objeto de tipo Ingrediente
                Ingredientes ingredientes1 = new Ingredientes(seleccionado);

                //Pasamos el objeto por parámetro al metodo eliminar
                ir.eliminar(ingredientes1);

                //Una vez eliminado, remuevo el elemento de la listaOriginal
                listaOriginal.remove(ingredientes1.getNombre());

                //Para que no aparezca, utilizo el metodo actualizarComboBox()
                actualizarComboBox(listaOriginal);
            }
        });

    }

    //Metodo que devuelve mi conexión a la base de datos
    private Connection getConnection() throws SQLException {
        return Database.conectar();
    }

    //obtenemos la lista de ingredientes de la BBDD y la metemos en una lista
    private List<String> obtenerLista(){

        List<String> lista = new ArrayList<>();

        String sentencia = "SELECT nombre FROM ingrediente";

        try(PreparedStatement ps = getConnection().prepareStatement(sentencia)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                lista.add(rs.getString("nombre"));

            }

        }catch (SQLException sql){

            JOptionPane.showMessageDialog(null, "No se pudo acceder a la BBDD.");
        }

        //Coloco la lista en orden alfabético para facilitar la búsqueda de los ingredientes.
        Collections.sort(lista);

        return lista;
    }

    //Este metodo agregará los ingredientes al ComboBox.
    //Al inicio, está rellena con la listaOriginal, obtenida de la base de datos.

    //Cuando escribamos en el filtro, este metodo entrará en acción
    //y creará una lista con los elementos que contengan los caracteres del filtro.

    //Finalmente, habrá que llamar al último metodo, actualizar ComboBox,
    //para vaciar el ComboBox y rellenarlo con los ingredientes que queremos.

    //Esto tiene que ser así porque los ingredientes no se pueden eliminar uno a uno,
    //sino que hay que darle la lista completa de una vez.
    private void filtrarIngredientes() {

        String textoFiltro = input.getText().toLowerCase();

        List<String> listaFiltrada = new ArrayList<>();

        for (String ingrediente : listaOriginal) {
            if (ingrediente.toLowerCase().contains(textoFiltro)) {
                listaFiltrada.add(ingrediente);
            }
        }

        actualizarComboBox(listaFiltrada);
    }

    //El siguiente metodo vacía el ComboBox completo y lo rellena con la lista que hemos filtrado
    private void actualizarComboBox(List<String> filtrados) {
        ingredientes.removeAllItems();

        for (String i : filtrados) {
            ingredientes.addItem(i);
        }
    }
}
