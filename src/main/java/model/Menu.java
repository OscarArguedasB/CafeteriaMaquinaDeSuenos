/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author PC
 */
public class Menu {
    
    private String idMenu;
    private String tipo;
    private String nombre;
    private double precio;
    private int idRestaurante;

    public Menu() {
    }

    public Menu(String idMenu, String tipo, String nombre, double precio, int idRestaurante) {
        this.idMenu = idMenu;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        this.idRestaurante = idRestaurante;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    
    
    
    
}
