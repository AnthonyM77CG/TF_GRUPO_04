package entidades;

public class Pan {
    private int id_panes;
    private String nombre;
    private int cantidad;
    private double precio;

    // Constructor con todos los parámetros
    public Pan(int id_panes, String nombre, int cantidad, double precio) {
        this.id_panes = id_panes;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Constructor sin id_panes (para uso en insertarPan)
    public Pan(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdPanes() {
        return id_panes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}
