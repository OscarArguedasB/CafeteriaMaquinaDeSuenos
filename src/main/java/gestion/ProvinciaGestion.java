
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
import model.Provincia;


public class ProvinciaGestion {
    private static final String SQL_GETPROVINCIAS = "SELECT * FROM provincia";

    public static String generarJson() {
        Provincia provincia = null;
        String tiraJson2 = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETPROVINCIAS);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                provincia = new Provincia(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", provincia.getCodigo_provincia())
                        .add("codigoProvincia", provincia.getCodigo_provincia())
                        .add("nombre", provincia.getNombre_provincia())
                        .add("codigoCanton", provincia.getCodigo_canton())
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
            Logger.getLogger(ProvinciaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson2;
    }
}
