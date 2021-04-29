
package model;


public class Canton {
    private int codigo_canton;
    private String nombre;

    public Canton() {
    }

    public Canton(int codigo_canton, String nombre) {
        this.codigo_canton = codigo_canton;
        this.nombre = nombre;
    }

    public int getCodigo_canton() {
        return codigo_canton;
    }

    public void setCodigo_canton(int codigo_canton) {
        this.codigo_canton = codigo_canton;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
