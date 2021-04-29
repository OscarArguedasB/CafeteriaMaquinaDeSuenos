/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author oscar
 */
public class Cliente {
     private String idCliente;
    private String nombreCliente;
    private String genero;
    private String telefono;
    private int codigo_provincia;
    private int idRestaurante;

    public Cliente() {
    }

    public Cliente(String idCliente, String nombreCliente, String genero, String telefono, int codigo_provincia, int idRestaurante) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.genero = genero;
        this.telefono = telefono;
        this.codigo_provincia = codigo_provincia;
        this.idRestaurante = idRestaurante;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCodigo_provincia() {
        return codigo_provincia;
    }

    public void setCodigo_provincia(int codigo_provincia) {
        this.codigo_provincia = codigo_provincia;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    
    
}

