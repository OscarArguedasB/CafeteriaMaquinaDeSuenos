package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Personal;
import model.YearGender;


public class PersonalGestion {

    private static final String SQL_GETPERSONALES = "SELECT * FROM personal";
    private static final String SQL_GETPERSONAL = "SELECT * FROM personal where idPersonal=?";
    private static final String SQL_GETINSERTAPERSONAL = "insert into personal (idPersonal,nombre,genero,fechaNaci,fechaIngr,telefono,salario,idRestaurante) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_GETMODIFICAPERSONAL = "update personal set nombre=?,genero=?,fechaNaci=?,fechaIngr=?,telefono=?,salario=?,idRestaurante=? where idPersonal=?";
    private static final String SQL_GETELIMINAPERSONAL = "Delete FROM personal where  idPersonal=?";
    private static final String SQL_YEAR_GENDER = "SELECT YEAR(fechaIngr) as fechaIngr,genero,Count(*) total FROM personal group by  YEAR(fechaIngr),genero order by YEAR(fechaIngr)";
    
    public static boolean insertaPersonal(Personal personal) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETINSERTAPERSONAL);
             sentencia.setString(1, personal.getIdPersonal());
            sentencia.setString(2, personal.getNombre());
            sentencia.setString(3, "" + personal.getGenero());
            sentencia.setObject(4, personal.getFechaNaci());
            sentencia.setObject(5, personal.getFechaIngr());
            sentencia.setInt(6, personal.getTelefono());
            sentencia.setDouble(7, personal.getSalario());
            sentencia.setInt(8, personal.getIdRestaurante());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static boolean modificaPersonal(Personal personal) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETMODIFICAPERSONAL);
            sentencia.setString(1, personal.getNombre());
            sentencia.setString(2, "" + personal.getGenero());
            sentencia.setObject(3, personal.getFechaNaci());
            sentencia.setObject(4, personal.getFechaIngr());
            sentencia.setInt(5, personal.getTelefono());
            sentencia.setDouble(6, personal.getSalario());
            sentencia.setInt(7, personal.getIdRestaurante());
            sentencia.setString(8, personal.getIdPersonal());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static boolean eliminaPersonal(Personal personal) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETELIMINAPERSONAL);
            sentencia.setString(1, personal.getIdPersonal());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ArrayList<Personal> getPersonales() {
        ArrayList<Personal> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPERSONALES);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                list.add(new Personal(
                       result.getString(1),
                        result.getString(2),
                        result.getString(3).charAt(0),
                        result.getDate(4),
                        result.getDate(5),
                        result.getInt(6),
                        result.getDouble(7),
                        result.getInt(8)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Personal getPersonal(String idPersonal) {
        Personal personal = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETPERSONAL);
            sentencia.setString(1, idPersonal);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                personal = new Personal(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3).charAt(0),
                        result.getDate(4),
                        result.getDate(5),
                        result.getInt(6),
                        result.getDouble(7),
                        result.getInt(8)
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personal;
    }
    
    public static ArrayList<YearGender> getYearGender() {
        ArrayList<YearGender> list = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_YEAR_GENDER);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                list.add(new YearGender(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;
    }
    
    public static String generarJson() {
        Personal personal = null;
        String tiraJson2 = "";
        String fecha1 = "";
        String fecha2 = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETPERSONALES);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                personal = new Personal(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3).charAt(0),
                        result.getDate(4),
                        result.getDate(5),
                        result.getInt(6),
                        result.getDouble(7),
                        result.getInt(8)
                );
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha1 = sdf.format(personal.getFechaNaci());
                fecha2 = sdf.format(personal.getFechaIngr());
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("cedula", personal.getIdPersonal())
                        .add("nombre", personal.getNombre())
                        .add("genero", personal.getGenero())
                        .add("fechaNacimiento",fecha1)
                        .add("fechaIngreso",fecha2)
                        .add("telefono",personal.getTelefono())
                        .add("salario",personal.getSalario())
                        .build();
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(objectJson);
                if (tiraJson2 == null) {
                    tiraJson2 = tira.toString() + "\n";
                } else {
                    tiraJson2 = tiraJson2 + tira.toString() + "\n";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonalGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson2;
    }
     

}
