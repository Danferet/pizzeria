package Controller;

import BBDD.Database;
import Models.Ingredientes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteRepositorio implements CrudRepositorio<Ingredientes> {

    public static Connection getConection() throws SQLException {
        return Database.getConnection();

    }

    @Override
    public void crear(Ingredientes ingredientes) {

        String sentencia = "INSERT INTO ingrediente (nombre) VALUES(?)";

        try(PreparedStatement ps = getConection().prepareStatement(sentencia)){

            ps.setString(1, ingredientes.getNombre());

            int resutlado = ps.executeUpdate();

            if(resutlado>0){
                JOptionPane.showMessageDialog(null,
                        "El elemento " + ingredientes.getNombre() + " se insertó satisfactoriamente en la tabla ingredientes.");
            }

        }catch (SQLException sql){
            System.err.println(sql.getMessage());
        }
    }


    @Override
    public List<Ingredientes> listar() {

        List<Ingredientes> listaIngredientes = new ArrayList<>();

        String consulta = "SELECT nombre FROM ingrediente";

        try(PreparedStatement ps = getConection().prepareStatement(consulta)){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                listaIngredientes.add(new Ingredientes(rs.getString("nombre")));

            }

        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }

        return listaIngredientes;
    }

    @Override
    public Ingredientes buscar(int num) {

        Ingredientes i = null;
        String sentencia = "SELECT * FROM ingrediente WHERE id = ?";

        try(PreparedStatement ps = getConection().prepareStatement(sentencia)){

            ps.setInt(1,num);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                i = new Ingredientes(rs.getString("nombre"));

            }

        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }

        return i;
    }

    @Override
    public void editar(Ingredientes ingrediente) {

    }

    @Override
    public void eliminar(Ingredientes ingrediente) {

        String sentencia = "DELETE FROM ingrediente WHERE nombre = ?";

        try(PreparedStatement ps = getConection().prepareStatement(sentencia)){

            ps.setString(1, ingrediente.getNombre());

            int resultado = ps.executeUpdate();

            if(resultado > 0){
                JOptionPane.showMessageDialog(null,
                        "El elemento " + ingrediente.getNombre() + " se eliminó correctamente.");

            }else{
                JOptionPane.showMessageDialog(null,
                        "No se eliminó el elemento de la BBDD.");
            }

        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }

    }


}
