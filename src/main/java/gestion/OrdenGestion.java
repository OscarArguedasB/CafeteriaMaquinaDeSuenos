
package gestion;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.Orden;


/**
 *
 * @author PC
 */
public class OrdenGestion {
    private static final String SQL_GETORDEN = "SELECT * FROM orden";

    public static String generarJson() {
        Orden orden = null;
        String tiraJson2 = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETORDEN);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                orden = new Orden(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("numeroOrden", orden.getNumOrden())
                        .add("codigoMenu", orden.getIdMenu())
                        .add("cantidad", orden.getCantidad())
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
            Logger.getLogger(OrdenGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson2;
    }
}
