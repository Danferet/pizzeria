package Controller;

import BBDD.Database;
import Models.Producto;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements CrudRepositorio<Producto> {

    //Este metodo devolverá un mensaje por ventana emergente
    // dependiendo de si en los insert se puede o no insertar el dato en la BBDD
    private int resultado(int rs) {

        if (rs > 0) {
            JOptionPane.showMessageDialog(null,
                    "El producto se insertó correctamente");
        } else {
            JOptionPane.showMessageDialog(null,
                    "El producto no se añadió a la base de datos.");
        }
        return rs;
    }

    @Override
    public void crear(Producto producto) {

        String sentencia = "INSERT INTO producto " +
                "(numero, nombre, tipo, precio, precioNormal, precioFamiliar) " +
                "VALUES (?,?,?,?,?,?)";

        if (producto.getTipo().equalsIgnoreCase("pizza")) {

            try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                ps.setString(1, "0");
                ps.setString(2, producto.getNombre());
                ps.setString(3, producto.getTipo());
                ps.setFloat(4, 0.0f);
                ps.setFloat(5, producto.getPrecioNormal());
                ps.setFloat(6, producto.getPrecioFamiliar());

                int resutlado = ps.executeUpdate();

            } catch (SQLException sql) {
                //System.err.println(sql.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Error al insertar el producto",
                        "Error de inserción",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {

            try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                ps.setString(1, producto.getNumero());
                ps.setString(2, producto.getNombre());
                ps.setString(3, producto.getTipo());
                ps.setFloat(4, producto.getPrecio());
                ps.setFloat(5, 0.0f);
                ps.setFloat(6, 0.0f);

                int resultado = ps.executeUpdate();

            } catch (SQLException sql) {
                //System.err.println(sql.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Error al insertar el producto",
                        "Error de inserción",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public List<Producto> listar() {
        return List.of();
    }

    public List<Producto> listar(String tipo){

        List<Producto> lista = new ArrayList<>();

        String sentencia = "SELECT numero, nombre, tipo, precio, precioNormal, precioFamiliar " +
                "FROM producto WHERE tipo = ?";

        try(PreparedStatement ps = Database.conectar().prepareStatement(sentencia)){

            ps.setString(1,tipo);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Producto producto = new Producto(
                        rs.getString("numero"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getFloat("precio"),
                        rs.getFloat("precioNormal"),
                        rs.getFloat("precioFamiliar"));

                lista.add(producto);
            }
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return lista;
    }

    @Override
    public Producto buscar(int num) {
        return null;
    }

    @Override
    public void editar(Producto producto) {

    }

    @Override
    public void eliminar(Producto producto) {

    }
}