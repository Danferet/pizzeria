package Controller;

import java.util.List;

public interface CrudRepositorio<T> {

    void crear(T t);

    List<T> listar();

    T buscar(int i);

    void editar(T t);

    void eliminar(T t);

}