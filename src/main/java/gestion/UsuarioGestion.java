/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Usuario;

/**
 *
 * @author oscar
 */
public class UsuarioGestion {

    private static final String SQL_GETUSUARIO = "Select* from usuario where idUsuario=? and pwUsuario=MD5(?)";
    private static final String SQL_INSERT_REGISTRAUSUARIO = "INSERT into usuario(idUsuario,pwUsuario,nombreUsuario,idRol,idRestaurante) values (?,MD5(?),?,?,?)";
    private static final String SQL_GETMODIFICAUSUARIO = "update usuario set idUsuario=?,pwUsuario=?,nombreUsuario=? where idUsuario=?";
    private static final String SQL_GETUSUARIOS = "Select* from usuario where id=?";
    private static final String SQL_UPDATEUSUARIO = "update usuario set idRol=? where id=?";

    public static Usuario getUsuario(String idUsuario, String pwUsuario) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIO);
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, pwUsuario);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setPwUsuario(pwUsuario);
                usuario.setNombreUsuario(rs.getString(4));
                usuario.setIdRol(rs.getString(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public static Usuario getUsuarios(int id) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETUSUARIOS);
            sentencia.setInt(1, id);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                usuario = new Usuario(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public static boolean registraUsuario(Usuario usuario) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_REGISTRAUSUARIO);
            sentencia.setString(1, usuario.getIdUsuario());
            sentencia.setString(2, usuario.getPwUsuario());
            sentencia.setString(3, usuario.getNombreUsuario());
            sentencia.setString(4, "cliente");
            sentencia.setInt(5, 1);
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean modificaUsuario(Usuario usuario) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATEUSUARIO);
            sentencia.setString(1, usuario.getIdRol());
            sentencia.setInt(2, usuario.getId());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
