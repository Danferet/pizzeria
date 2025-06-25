package View;

import BBDD.Database;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VentanaListaIngredientes extends JFrame {

    Container container;
    JPanel panelTitulo = new JPanel();
    JPanel panel = new JPanel();
    JLabel titulo = new JLabel("Lista de ingredientes");

    public VentanaListaIngredientes() {

        super("Lista de ingredientes");
        configurarVentana();
        agregarComponentes();

    }

    public void agregarComponentes() {

        int totalIngredientes = obtenerLista().size();
        int ladoTabla = obtenerlado(totalIngredientes);

        panelTitulo.add(titulo);
        titulo.setFont(new Font("Serif", Font.PLAIN,25));
        darTexto(obtenerLista());

        panel.setLayout(new GridLayout(ladoTabla,4,10,10));
        Border bordePanelCentral = panel.getBorder();
        Border margenes = new EmptyBorder(10,10,10,10);
        panel.setBorder(new CompoundBorder(bordePanelCentral,margenes));

    }

    private List<String> obtenerLista() {

        List<String> lista = new ArrayList<>();
        String sentencia = "SELECT nombre FROM ingrediente";

        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString("nombre"));
            }

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los datos.");
        }

        Collections.sort(lista);

        return lista;
    }

    public void darTexto(List<String> lista){

        for(int i = 0; i<lista.size();i++){

            JLabel item = new JLabel(lista.get(i), SwingConstants.CENTER);
            item.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            item.setFont(new Font("Arial", Font.BOLD,12));
            item.setBackground(Color.blue);

            panel.add(item);

        }
    }

    private int obtenerlado(int total){

        int lado;

        if(total / 5 == 0){

            lado = total / 5;

        }else{

            lado = (total / 5) +1;

        }

        return lado;
    }

    private void configurarVentana(){

        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel,BorderLayout.CENTER);
        container.add(panelTitulo, BorderLayout.NORTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(false);
        pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4, alto / 4, ancho / 2, alto / 2);

    }



}
