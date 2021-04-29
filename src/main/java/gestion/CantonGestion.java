/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Canton;
import model.Conexion;

/**
 *
 * @author PC
 */
public class CantonGestion {

    private static final String SQL_GETCANTONES = "SELECT * FROM canton";

    public static String generarJson() {
        Canton canton = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETCANTONES);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                canton = new Canton(
                        rs.getInt(1),
                        rs.getString(2)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", canton.getCodigo_canton())
                        .add("codigoCanton", canton.getCodigo_canton())
                        .add("nombre", canton.getNombre())
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
            Logger.getLogger(CantonGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
}
