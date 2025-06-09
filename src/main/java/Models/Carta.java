package Models;

import java.util.ArrayList;
import java.util.List;

public class Carta {

    List<Producto> listaProductos;

    public Carta(){
        this.listaProductos = new ArrayList<>();
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String verDetalle(){




        return "";
    }
}
