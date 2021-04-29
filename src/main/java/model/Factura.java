
package model;


public class Factura {
    private int id;
    private String idUsuario;
    private double precio;
    private int numOrden;
    private int idRestaurante;

    public Factura() {
    }

    public Factura(int id, String idUsuario, double precio, int numOrden, int idRestaurante) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.precio = precio;
        this.numOrden = numOrden;
        this.idRestaurante = idRestaurante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    
    
}
