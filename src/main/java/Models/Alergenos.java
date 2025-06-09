package Models;

public enum Alergenos {
    //Los 14 alérgenos obligatorios de poner en las cartas en España

    GLUTEN(1, "Gluten"),
    CRUSTACEOS(2, "Crustáceos"),
    HUEVOS(3,"Huevos"),
    PESCADO(4,"Pescado"),
    CACAHUETES(5,"Cacahuetes"),
    SOJA(6,"Soja"),
    LECHE(7,"Leche"),
    FRUTOS_CASCARA(8,"Frutos de cáscara"),
    APIO(9,"Apio"),
    MOSTAZA(10,"Mostaza"),
    SESAMO(11,"Sésamo"),
    SULFITOS(12,"Dióxido de azufre y sulfitos"),
    ALTRAMUCES(13,"Altramuces"),
    MOLUSCOS(14,"Moluscos");

    private final int id;
    private final String nombre;

    private Alergenos(int id,String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}