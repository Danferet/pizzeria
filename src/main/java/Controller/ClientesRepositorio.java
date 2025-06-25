package Controller;

import BBDD.Database;
import Models.Cliente;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesRepositorio<T> implements CrudRepositorio<Cliente> {

    //Metodo para añadir un cliente a la base de datos
    @Override
    public void crear(Cliente cliente) {

        //Sentecia SQL para inserta los datos, necesitamos solo los tes que están entre paréntesis
        String sentencia = "INSERT INTO cliente (nombre, telefono, direccion) VALUES (?,?,?)";

        //Utilizo try-with-resources para cerrar la conexión tras la inserción
        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            //Paso a cada campo el dato correspondiente
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getTelefono());
            ps.setString(3, cliente.getDireccion());

            //Llevo a cabo la sentencia
            int resultado = ps.executeUpdate();

            //Aparecerá un mensaje dependiendo del éxito de la operación.
            if (resultado > 0) {

                JOptionPane.showMessageDialog(null,
                        "Cliente insertado correctamente ✅",
                        "Agregar cliente",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(null,
                        "No se pudo agregar el cliente ✘",
                        "Agregar cliente",
                        JOptionPane.ERROR_MESSAGE);
            }

            //Si algo va mal, capturará el error y saldrá un mensaje de fallo.
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo conectar con la base de datos",
                    "Agregar cliente",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //Obtengo la lista de todos los clientes de la base de datos
    @Override
    public List<Cliente> listar() {

        //Creo una lista vacía
        List<Cliente> lista = new ArrayList<>();

        //Sentencia de busqueda
        String sentencia = "SELECT nombre, telefono, direccion FROM cliente";

        //Realizamos la búsqueda
        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            //Se lleva a cabo la búsqueda
            ResultSet rs = ps.executeQuery();

            //Recorrerá cada elemento de la lista obtenida en la BBDD y
            while (rs.next()) {

                //crearemos instancias del tipo Cliente con cada elemento encontrado
                Cliente cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion"));

                //finalmente añadimos el cliente encontrado a la lista
                lista.add(cliente);

            }
            //Mensaje de error si no se accede a la base de datos.
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo acceder a la lista de clientes",
                    "Error al listar",
                    JOptionPane.ERROR_MESSAGE);
        }

        //Si todo sale bien, retornamos la lista.
        return lista;
    }

    //Busco el cliente por número de teléfono, que es único para cada cliente.
    @Override
    public Cliente buscar(int i) {

        List<Cliente> listaClientes = listar();

        Cliente cliente = new Cliente();

        for (Cliente clientes : listaClientes) {

            if (cliente.getTelefono().equalsIgnoreCase(String.valueOf(i))) {

                cliente = clientes;
                break;
            }
        }

        return cliente;
    }

    //Falta implementar el método editar
    @Override
    public void editar(Cliente cliente) {

    }

    //Método que elimina un cliente
    @Override
    public void eliminar(Cliente cliente) {

        //Pasando el cliente por parámetro,
        //este método buscará el teléfono de los clientes y eliminará el que coincida
        String sentencia = "DELETE FROM cliente WHERE telefono = ?";

        //Se trata de conectar a la base de datos con try-with-resources
        try (PreparedStatement ps = Database.conectar().prepareStatement(sentencia)) {

            //Se pasan los datos a la sentencia en su orden adecuado
            ps.setString(1, cliente.getTelefono());

            //Se lleva a cabo la sentencia
            int resultado = ps.executeUpdate();

            //Se valida si se ha llevado a cabo y se manda una retroalimentación con el resutlado
            if (resultado > 0) {

                JOptionPane.showMessageDialog(null,
                        "Cliente eliminado correctamente",
                        "Eliminar cliente",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el cliente",
                        "Eliminar cliente",
                        JOptionPane.ERROR_MESSAGE);

            }

            //Si no se ha podido acceder a la base de datos se manda este otro mensaje.
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo acceder a la base de datos",
                    "Eliminar cliente",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}