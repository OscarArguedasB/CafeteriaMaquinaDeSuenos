package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Menu;

public class MenuGestion {
    
    String tipom="";

    private static final String SQL_GETMENUS = "SELECT * FROM menu";
    private static final String SQL_GETMENU = "SELECT * FROM menu where idMenu=?";
    private static final String SQL_GETTIPO = "SELECT * FROM menu where tipo=?";
    private static final String SQL_GETINSERTAMENU = "insert into menu (idMenu,tipo,nombrePlatillo,precio,idRestaurante) values (?,?,?,?,?)";
    private static final String SQL_GETMODIFICAMENU = "update menu set tipo=?,nombrePlatillo=?,precio=?,idRestaurante=? where idMenu=?";
    private static final String SQL_GETELIMINAMENU = "Delete FROM menu where idMenu=?";

    public static boolean insertaMenu(Menu menu) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETINSERTAMENU);
            sentencia.setString(1, menu.getIdMenu());
            sentencia.setString(2, menu.getTipo());
            sentencia.setString(3, menu.getNombre());
            sentencia.setDouble(4, menu.getPrecio());
            sentencia.setInt(5, menu.getIdRestaurante());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static boolean modificaMenu(Menu menu) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETMODIFICAMENU);
            sentencia.setString(1, menu.getTipo());
            sentencia.setString(2, menu.getNombre());
            sentencia.setDouble(3, menu.getPrecio());
            sentencia.setInt(4, menu.getIdRestaurante());
            sentencia.setString(5, menu.getIdMenu());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static boolean eliminaMenu(Menu menu) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETELIMINAMENU);
            sentencia.setString(1, menu.getIdMenu());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ArrayList<Menu> getMenuTipo(String tipom) {
        ArrayList<Menu> menuTipo = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETTIPO);
            sentencia.setString(1, tipom);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                menuTipo.add(new Menu(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getInt(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menuTipo;
    }

    public static ArrayList<Menu> getMenus() {
        ArrayList<Menu> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETMENUS);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                list.add(new Menu(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getInt(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Menu getMenu(String idMenu) {
        Menu menu = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETMENU);
            sentencia.setString(1, idMenu);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                menu = new Menu(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getInt(5)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menu;
    }

    public static String generarJson() {
        Menu menu = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETMENUS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                menu = new Menu(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("codigo", menu.getIdMenu())
                        .add("tipo", menu.getTipo())
                        .add("nombre", menu.getNombre())
                        .add("precio", menu.getPrecio())
                        .add("codigoRestaurante", menu.getIdRestaurante())
                        .build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objectJson);
                if (tiraJson == null) {
                    tiraJson = tira.toString() + "\n";
                } else {
                    tiraJson = tiraJson + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

}
