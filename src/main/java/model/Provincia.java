
package model;


public class Provincia {
    private int codigo_provincia;
    private String nombre_provincia;
    private int codigo_canton;

    public Provincia() {
    }

    public Provincia(int codigo_provincia, String nombre_provincia, int codigo_canton) {
        this.codigo_provincia = codigo_provincia;
        this.nombre_provincia = nombre_provincia;
        this.codigo_canton = codigo_canton;
    }

    public int getCodigo_provincia() {
        return codigo_provincia;
    }

    public void setCodigo_provincia(int codigo_provincia) {
        this.codigo_provincia = codigo_provincia;
    }

    public String getNombre_provincia() {
        return nombre_provincia;
    }

    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public int getCodigo_canton() {
        return codigo_canton;
    }

    public void setCodigo_canton(int codigo_canton) {
        this.codigo_canton = codigo_canton;
    }
    
}
