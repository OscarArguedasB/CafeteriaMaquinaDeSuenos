
package model;


public class Orden {
    private int numOrden;
    private String idMenu;
    private int cantidad;

    public Orden() {
    }

    public Orden(int numOrden, String idMenu, int cantidad) {
        this.numOrden = numOrden;
        this.idMenu = idMenu;
        this.cantidad = cantidad;
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
          
}
