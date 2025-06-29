package Models;

import java.util.ArrayList;
import java.util.List;

public class Producto implements Comparable{

    private String numero;
    private String nombre;
    private String tipo;
    private float precio;
    private float precioNormal;
    private float precioFamiliar;
    private List<Ingredientes> listaIng;
    private List<Alergenos> listaAlergenos;

    public Producto(String numero, String nombre, String tipo,
                    float precio, float precioNormal, float precioFamiliar) {

        this.numero = numero;
        this.nombre = nombre;
        this.tipo = tipo;
        this.listaIng = new ArrayList<>();
        this.precio = precio;
        this.precioNormal = precioNormal;
        this.precioFamiliar = precioFamiliar;
        this.listaAlergenos = new ArrayList<>();

    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ingredientes> getListaIng() {
        return listaIng;
    }

    public void setListaIng(List<Ingredientes> listaIng) {
        this.listaIng = listaIng;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(float precioNormal) {
        this.precioNormal = precioNormal;
    }

    public float getPrecioFamiliar() {
        return precioFamiliar;
    }

    public void setPrecioFamiliar(float precioFamiliar) {
        this.precioFamiliar = precioFamiliar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return numero;
    }

    public List<Alergenos> getListaAlergenos() {
        return listaAlergenos;
    }

    public void setListaAlergenos(List<Alergenos> listaAlergenos) {
        this.listaAlergenos = listaAlergenos;
    }

    public void anadirIngrediente(Ingredientes i){

        listaIng.add(i);
    }


    //Este méeodo me devuelve la lista de ingredientes que tenga el producto
    private String darLista(){

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<listaIng.size();i++){

            if(i == 0){

                sb.append(listaIng.get(i).getNombre()).append(", ");

            }else if(i < listaIng.size()-1){

                sb.append(listaIng.get(i).getNombre().toLowerCase()).append(", ");

            }else{

                sb.append(listaIng.get(i).getNombre().toLowerCase()).append(".");
            }
        }

        return sb.toString();

    }

    private String darListaAlergenos(){

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<listaAlergenos.size();i++){

            if(i == 0){

                sb.append(listaAlergenos.get(i).getNombre()).append(", ");

            }else if(i < listaAlergenos.size()-1){

                sb.append(listaAlergenos.get(i).getNombre().toLowerCase()).append(", ");

            }else{

                sb.append(listaAlergenos.get(i).getNombre().toLowerCase()).append(".");
            }
        }

        return sb.toString();

    }

    public String verDetalle() {

        String detalle;

        if (tipo.equalsIgnoreCase("pizza")){

            StringBuilder sb = new StringBuilder(getNombre())
                    .append("  ").append("Normal: ")
                    .append(getPrecioNormal())
                    .append("€    ")
                    .append("Familiar: ")
                    .append(getPrecioFamiliar())
                    .append("€\n")
                    .append(darLista())
                    .append(darListaAlergenos());

            detalle = sb.toString();

        }else{

            StringBuilder sb = new StringBuilder(getNumero())
                    .append(" ")
                    .append(getNombre())
                    .append("      Precio: ")
                    .append(getPrecio())
                    .append("€\n")
                    .append(darLista())
                    .append(darListaAlergenos());

            detalle = sb.toString();
        }

        return detalle;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
