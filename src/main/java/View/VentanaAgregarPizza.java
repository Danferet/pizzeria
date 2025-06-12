package View;

import BBDD.Database;
import Excepctions.NombreNoValidoException;
import Validaciones.Validar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaAgregarPizza extends JFrame {

    JPanel panel = new JPanel();
    JLabel insertarNombre = new JLabel("Nombre nuevo producto:");
    JTextField inputNombre = new JTextField();
    JLabel normal = new JLabel("Normal");
    JTextField inputPrecioNormal = new JTextField();
    JLabel familiar = new JLabel("Familiar");
    JTextField inputPrecioFamiliar = new JTextField();
    JButton aceptar = new JButton("ACEPTAR");
    JLabel mensajeError = new JLabel("");

    public VentanaAgregarPizza(){

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int alto = d.height;
        int ancho = d.width;

        setBounds(ancho / 4 + 20, alto / 4 + 20, 460, 200);

        agregarComponentes();

    }

    public void agregarComponentes() {

        add(panel);
        panel.setBounds(0,0,getWidth(),getHeight());
        panel.setLayout(null);

        insertarNombre.setBounds(20, 25, 150, 30);
        inputNombre.setBounds(20,50,150,30);
        normal.setBounds(190,25,50,30);
        inputPrecioNormal.setBounds(190,50,50,30);
        familiar.setBounds(260,25,50,30);
        inputPrecioFamiliar.setBounds(260,50,50,30);
        mensajeError.setBounds(20,90,400,60);
        aceptar.setBounds(330,35,100,45);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean validarNombre = Validar.validarPizza(inputNombre.getText());
                boolean validarNormal = Validar.validarPrecio(inputPrecioNormal.getText());
                boolean validarFamiliar = Validar.validarPrecio(inputPrecioFamiliar.getText());

                String insert = "INSERT INTO producto (numero, nombre, tipo, " +
                        "precio, precioNormal,precioFamiliar) VALUES (?,?,?,?,?,?)";

                try(PreparedStatement ps = Database.conectar().prepareStatement(insert)){

                    if(!validarNombre || !validarNormal || !validarFamiliar){

                        throw new NombreNoValidoException("Debes introducir datos válidos");
                    }

                    ps.setString(1,"0");
                    ps.setString(2,inputNombre.getText());
                    ps.setString(3,"pizza");
                    ps.setFloat(4,0.0f);
                    ps.setFloat(5,Float.parseFloat(inputPrecioNormal.getText()));
                    ps.setFloat(6,Float.parseFloat(inputPrecioFamiliar.getText()));

                    int resultado = ps.executeUpdate();

                    if(resultado>0){
                        JOptionPane.showMessageDialog(
                                VentanaAgregarPizza.this,
                                "Producto insertado correctamente");
                    }

                    inputNombre.setText("");
                    inputPrecioFamiliar.setText("");
                    inputPrecioNormal.setText("");
                    mensajeError.setText("");

                }catch (SQLException sql){
                    JOptionPane.showMessageDialog(
                            VentanaAgregarPizza.this,
                            "Ocurrió un error al insertar.");
                }catch (NombreNoValidoException nnve){
                    mensajeError.setText(nnve.getMessage());

                }

            }
        });

        panel.add(insertarNombre);
        panel.add(inputNombre);
        panel.add(normal);
        panel.add(inputPrecioNormal);
        panel.add(familiar);
        panel.add(inputPrecioFamiliar);
        panel.add(aceptar);
        panel.add(mensajeError);

    }

    public String[] listaTipos(){

        String[] listaTipos =
                {"-Tipo-", "Bocadillo", "Entrepanes",
                        "Smash", "Burger", "Compartir","Postre"};

        return  listaTipos;
    }


}




