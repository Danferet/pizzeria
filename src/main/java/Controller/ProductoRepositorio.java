package Controller;

import BBDD.Database;
import Models.Producto;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        if (producto.getTipo().equalsIgnoreCase("pizza")) {

            String sentencia = "INSERT INTO producto " +
                    "(numero, nombre, tipo, precio, precioNormal, precioFamiliar) " +
                    "VALUES (?,?,?,?,?,?)";

            try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                ps.setString(1, "0");
                ps.setString(2, producto.getNombre());
                ps.setString(3, producto.getTipo());
                ps.setFloat(4, 0.0f);
                ps.setFloat(5, producto.getPrecioNormal());
                ps.setFloat(6, producto.getPrecioFamiliar());

                int resutlado = ps.executeUpdate();

                if (resutlado > 0) {
                    JOptionPane.showMessageDialog(null,
                            "El elemento "
                                    + producto.getNombre()
                                    + " se insertó en la tabla producto.");
                }

            } catch (SQLException sql) {
                System.err.println(sql.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Error al insertar el producto");

            }

        } else {

            String sentencia = "INSERT INTO producto " +
                    "(numero, nombre, tipo, precio, precioNormal, precioFamiliar) " +
                    "VALUES (?,?,?,?,?,?)";

            try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

                ps.setString(1, producto.getNumero());
                ps.setString(2, producto.getNombre());
                ps.setString(3, producto.getTipo());
                ps.setFloat(4, producto.getPrecio());
                ps.setFloat(5, 0.0f);
                ps.setFloat(6, 0.0f);

                int resutlado = ps.executeUpdate();

                if (resutlado > 0) {
                    JOptionPane.showMessageDialog(null,
                            "El elemento "
                                    + producto.getNombre()
                                    + " se insertó en la tabla producto.");
                }

            } catch (SQLException sql) {
                System.err.println(sql.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Error al insertar el producto");

            }
        }
    }

    @Override
    public List<Producto> listar() {
        return List.of();
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