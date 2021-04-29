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
import model.Factura;

public class FacturaGestion {

    private static final String SQL_GETFACTURA = "SELECT * FROM factura";
    private static final String SQL_GETFACTURACERTI = "SELECT * FROM factura where id=?";

    public static String generarJson() {
        Factura factura = null;
        String tiraJson = "";
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_GETFACTURA);
            ResultSet rs = sentencia.executeQuery();
            while (rs != null && rs.next()) {
                factura = new Factura(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
                JsonObjectBuilder creadorJson = Json.createObjectBuilder();
                JsonObject objectJson = creadorJson.add("id", factura.getId())
                        .add("nombreUsuario", factura.getIdUsuario())
                        .add("precio", factura.getPrecio())
                        .add("codigoCanton", factura.getNumOrden())
                        .add("codigoRestaurante", factura.getIdRestaurante())
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
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

    public static Factura buscarFactura(int id) {
        Factura factura = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETFACTURACERTI);
            sentencia.setInt(1, id);
            ResultSet result = sentencia.executeQuery();
            while (result != null && result.next()) {
                factura = new Factura(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        result.getInt(5)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;
    }
}
