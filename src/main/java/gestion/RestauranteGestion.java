
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
import model.Restaurante;


public class RestauranteGestion {
    
    private static final String SQL_GETRESTAURANTE = "SELECT * FROM restaurante";

    public static String generarJson() {
        Restaurante restaurante = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETRESTAURANTE);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                restaurante = new Restaurante(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", restaurante.getIdRestaurante())
                        .add("codigo", restaurante.getIdRestaurante())
                        .add("nombre", restaurante.getNombre())
                        .add("descripcion",restaurante.getDescripcion())
                        .add("codigoProvincia", restaurante.getCodigo_provincia())
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
            Logger.getLogger(RestauranteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
}
