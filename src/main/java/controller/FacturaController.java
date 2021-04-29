/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.FacturaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Factura;

/**
 *
 * @author PC
 */
@Named(value = "facturaController")
@SessionScoped
public class FacturaController extends Factura implements Serializable {
    
    private boolean habilitar = true;

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
    }
    
    public FacturaController() {
    }
    
    public void buscarFactura(int id) {
        Factura fa = FacturaGestion.buscarFactura(id);
        if (fa != null) {
            this.setId(fa.getId());
            this.setIdUsuario(fa.getIdUsuario());
            this.setPrecio(fa.getPrecio());
            this.setNumOrden(fa.getNumOrden());
            this.setIdRestaurante(fa.getNumOrden());
            habilitar=false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "La factura no existe");
            FacesContext.getCurrentInstance().addMessage("certificacionFacturaForm:id", msg);
            habilitar=true;
        }
    }
    
    
    
}
