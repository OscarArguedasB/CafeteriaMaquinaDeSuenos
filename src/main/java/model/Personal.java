package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Personal {

    private String idPersonal;
    private String nombre;
    private char genero;
    private Date fechaNaci;
    private Date fechaIngr;
    private int telefono;
    private double salario;
    private int idRestaurante;

    public Personal() {
    }

    public Personal(String idPersonal, String nombre, char genero, Date fechaNaci, Date fechaIngr, int telefono, double salario,int idRestaurante) {
        this.idPersonal = idPersonal;
        this.nombre = nombre;
        this.genero = genero;
        this.fechaNaci = fechaNaci;
        this.fechaIngr = fechaIngr;
        this.telefono = telefono;
        this.salario = salario;
        this.idRestaurante=idRestaurante;
    }
    public String getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public Date getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(Date fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    public Date getFechaIngr() {
        return fechaIngr;
    }

    public void setFechaIngr(Date fechaIngr) {
        this.fechaIngr = fechaIngr;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    


    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha1 = format.format(this.fechaNaci);
        String fecha2 = format.format(this.fechaIngr);
        return "{\"Personal\":{\n\"cedula\":\""
                + idPersonal + "\",\n\"nombre\":\""
                + nombre + "\",\n\"genero\":\""
                + genero + "\",\n\"fechaNaci\":\""
                + fecha1 + "\",\n\"fechaIngr\":\""
                + fecha2 + "\",\n\"telefono\":\""
                + telefono + "\",\n\"salario\":\""
                + salario +  "\",\n\"Codigo del restaurante\":\""
                + idRestaurante + "\"\n}\n}";
    }

}
