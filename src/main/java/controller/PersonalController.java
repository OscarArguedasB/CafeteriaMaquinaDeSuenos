/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.PersonalGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Personal;

/**
 *
 * @author PC
 */
@Named(value = "personalController")
@SessionScoped
public class PersonalController extends Personal implements Serializable {

    
    public PersonalController() {
    }
    
     public String edita(String idPersonal) {
        Personal pe = PersonalGestion.getPersonal(idPersonal);
        if (pe != null) {
            this.setIdPersonal(pe.getIdPersonal());
            this.setNombre(pe.getNombre());
            this.setGenero(pe.getGenero());
            this.setFechaNaci(pe.getFechaNaci());
            this.setFechaIngr(pe.getFechaIngr());
            this.setTelefono(pe.getTelefono());
            this.setSalario(pe.getSalario());
            this.setIdRestaurante(pe.getIdRestaurante());
            return "edita.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El personal no existe");
            FacesContext.getCurrentInstance().addMessage("editaPersonalForm:idPersonal", msg);
            return "verPersonal.xhtml";
        }
    }
     public String insertar() {
        if (PersonalGestion.insertaPersonal(this)) {
            return "confiPersonal.xhtml";
        } else {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al insertar el personal");
             FacesContext.getCurrentInstance().addMessage("editaPersonalForm:idPersonal", ms);
        return "edita.xhtml";
        }
    }
     /*
     public String modificar() {
        if (PersonalGestion.modificaPersonal(this)) {
            return "verPersonal.xhtml";
        } else {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al modificar el personal");
             FacesContext.getCurrentInstance().addMessage("editaPersonalForm:idPersonal", ms);
        return "edita.xhtml";
        }
    }
*/
    public String eliminar() {
        if (PersonalGestion.eliminaPersonal(this)) {
            return "verPersonal.xhtml";
        } else {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar el personal");
             FacesContext.getCurrentInstance().addMessage("editaPersonalForm:idPersonal", ms);
        return "edita2.xhtml";
        }
    }
    public List<Personal> getPersonales(){
        return PersonalGestion.getPersonales();
    }
}
