package View;

import BBDD.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VentanaListaIngredientes extends JFrame {

    JPanel panel = new JPanel();
    JTextPane texto = new JTextPane();


    public VentanaListaIngredientes() {

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
        pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4, alto / 4, ancho / 2, alto / 2);
        agregarComponentes();

    }

    public void agregarComponentes() {

        add(panel);

        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setLayout(null);
        panel.add(texto);


        texto.setText(darTexto(obtenerLista()));
        texto.setBounds(10,10,getWidth(),getHeight());
        texto.setFont(new Font("Arial", Font.PLAIN,20));

    }

    private List<String> obtenerLista() {

        List<String> lista = new ArrayList<>();
        String sentencia = "SELECT nombre FROM ingrediente";

        try (PreparedStatement ps = getConnection().prepareStatement(sentencia)) {

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

    public String darTexto(List<String> lista){

        StringBuilder sb = new StringBuilder("Ingredientes disponibles para añadir a los productos:")
                .append("\n\n");

        for(int i = 0; i<lista.size();i++){

            if(i == 0){

                sb.append(lista.get(i))
                        .append(", ");

            }else if(i == lista.size()-1){

                sb.append(lista.get(i).toLowerCase())
                        .append(".");

            }else{

                sb.append(lista.get(i).toLowerCase())
                        .append(", ");
            }
        }

        return sb.toString();
    }

    private Connection getConnection() throws SQLException {
        return Database.getConnection();
    }

}
