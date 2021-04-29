/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.MenuGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Menu;


/**
 *
 * @author PC
 */
@Named(value = "menuController")
@SessionScoped
public class MenuController extends Menu implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
    }
    public String edita(String idMenu) {
        Menu me = MenuGestion.getMenu(idMenu);
        if (me != null) {
            this.setIdMenu(me.getIdMenu());
            this.setTipo(me.getTipo());
            this.setNombre(me.getNombre());
            this.setPrecio(me.getPrecio());
            this.setIdRestaurante(me.getIdRestaurante());
            return "editaMenu.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El menu no existe");
            FacesContext.getCurrentInstance().addMessage("editaMenuForm:idMenu", msg);
            return "verMenu.xhtml";
        }
    }
     public String insertar() {
        if (MenuGestion.insertaMenu(this)) {
            return "verMenu.xhtml";
        } else {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El menu no se pudo insertar");
            FacesContext.getCurrentInstance().addMessage("editaMenuForm:idMenu", msg);
            return "editaMenu2.xhtml";
        }
    }
    
     public String modificar() {
        if (MenuGestion.modificaMenu(this)) {
            return "verMenu.xhtml";
        } else {
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El menu no se pudo modificar");
            FacesContext.getCurrentInstance().addMessage("editaMenuForm:idMenu", msg);
            return "editaMenu.xhtml";
        }
    }

    public String eliminar() {
        if (MenuGestion.eliminaMenu(this)) {
            return "verMenu.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El menu no se pudo eliminar");
            FacesContext.getCurrentInstance().addMessage("editaMenuForm:idMenu", msg);
            return "editaMenu.xhtml";
        }
    }
    public List<Menu> getMenus(){
        return MenuGestion.getMenus();
    }
    
    
    public List<Menu> getMientrasTanto(){
        return MenuGestion.getMenuTipo("Mientras Tanto");
    }
    
    public List<Menu> getEspeciales(){
        return MenuGestion.getMenuTipo("Especiales");
    }
    
    public List<Menu> getPastas(){
        return MenuGestion.getMenuTipo("Pastas");
    }
    
    public List<Menu> getPaninis(){
        return MenuGestion.getMenuTipo("Paninis");
    }
    
    public List<Menu> getHamburguesas(){
        return MenuGestion.getMenuTipo("Hamburguesas");
    }
    
    public List<Menu> getCrepas(){
        return MenuGestion.getMenuTipo("Crepas");
    }
    
    public List<Menu> getBebidasCalientes(){
        return MenuGestion.getMenuTipo("Bebidas Calientes");
    }
    
    public List<Menu> getBebidasFrias(){
        return MenuGestion.getMenuTipo("Bebidas Frias");
    }
    
}
