package controller;

import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Usuario;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    public UsuarioController() {
    }

    public String getUsuario() {
        Usuario usuario = UsuarioGestion.getUsuario(this.getIdUsuario(), this.getPwUsuario());
        if (usuario != null) {
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalida");
            FacesContext.getCurrentInstance().addMessage("loginForm:clave", msg);
            return "index.xhtml";
        }
    }

    public void buscarUsuario(int id) {
        Usuario u = UsuarioGestion.getUsuarios(id);
        if (u != null) {
            this.setId(u.getId());
            this.setIdUsuario(u.getIdUsuario());
            this.setPwUsuario(u.getPwUsuario());
            this.setNombreUsuario(u.getNombreUsuario());
            this.setIdRol(u.getIdRol());
            this.setIdRestaurante(u.getIdRestaurante());
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Posiblemente el usuario no existe");
            FacesContext.getCurrentInstance().addMessage("buscarUsuarioForm:idUsuario", msg);
        }
    }

    public String insertar() {
        if (UsuarioGestion.registraUsuario(this)) {
            return "index.xhtml";
        } else {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrarse");
            FacesContext.getCurrentInstance().addMessage("registerForm:idUsuario", ms);
            return "index.xhtml";
        }
    }
    public String modificar() {
        if (UsuarioGestion.modificaUsuario(this)) {
            return "principal.xhtml";
        } else {
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al registrarse");
            FacesContext.getCurrentInstance().addMessage("buscarUsurioForm:idUsuario", ms);
            return "usuario.xhtml";
        }
    }
    

    /*public String cambiopw_ced(String idUsuario) {
        Usuario u = UsuarioGestion.getUsuario(idUsuario);
        if (u != null) {
            this.setIdUsuario(u.getIdUsuario());
            this.setPwUsuario(u.getPwUsuario());
            this.setNombreUsuario(u.getNombreUsuario());
            return "edita.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "El usuario no existe");
            FacesContext.getCurrentInstance().addMessage("cambioUsuarioForm:idUsuario", msg);
            return "verUsuario.xhtml";
        }
    }*/
}
