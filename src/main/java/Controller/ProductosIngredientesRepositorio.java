package Controller;

import BBDD.Database;
import Models.ProductoIngrediente;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductosIngredientesRepositorio implements CrudRepositorio<ProductoIngrediente>{

    @Override
    public void crear(ProductoIngrediente pi) {

        String sentencia =
                "INSERT INTO productos_ingredientes (id_producto, id_ingrediente) VALUES (?.?)";

        try(PreparedStatement ps = Database.conectar().prepareStatement(sentencia)){

            ps.setInt(1, pi.getId_producto());
            ps.setInt(2, pi.getId_ingrediente());

            int resultado = ps.executeUpdate();

            if(resultado>0){
                JOptionPane.showMessageDialog(
                        null,
                        "El ingrediente se agregó correctamente a la pizza");
            }

        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }

    }

    @Override
    public List<ProductoIngrediente> listar() {
        return List.of();
    }

    @Override
    public ProductoIngrediente buscar(int i) {
        return null;
    }

    @Override
    public void editar(ProductoIngrediente pi) {

    }

    @Override
    public void eliminar(ProductoIngrediente pi) {

    }
}
