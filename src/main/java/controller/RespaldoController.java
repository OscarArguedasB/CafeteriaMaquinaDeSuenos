
package controller;

import gestion.CantonGestion;
import gestion.ClienteGestion;
import gestion.FacturaGestion;
import gestion.MenuGestion;
import gestion.OrdenGestion;
import gestion.PersonalGestion;
import gestion.ProvinciaGestion;
import gestion.RestauranteGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


@Named(value = "respaldoController")
@SessionScoped
public class RespaldoController implements Serializable {

    
    public RespaldoController() {
    }
    
    public void respaldo() {
        ZipOutputStream out = null;
        try {
            String json = CantonGestion.generarJson();
            String json2 = ProvinciaGestion.generarJson();
            String json3 = RestauranteGestion.generarJson();
            String respaldo = ClienteGestion.generarJson();
            String respaldo2 = MenuGestion.generarJson();
            String respaldo3 = OrdenGestion.generarJson();
            String respaldo4 = FacturaGestion.generarJson();
            String respaldo5= PersonalGestion.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldo.zip");

            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry r = new ZipEntry("respaldo.json");
            out.putNextEntry(r);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            byte[] data2 = json2.getBytes();
            out.write(data2, 0, data2.length);
            byte[] data3 = json3.getBytes();
            out.write(data3, 0, data3.length);
            byte[] data4 = respaldo.getBytes();
            out.write(data4, 0, data4.length);
             byte[] data5 = respaldo2.getBytes();
            out.write(data5, 0, data5.length);
             byte[] data6 = respaldo3.getBytes();
            out.write(data6, 0, data6.length);
            byte[] data7 = respaldo4.getBytes();
            out.write(data7, 0, data7.length);
            byte[] data8 = respaldo5.getBytes();
            out.write(data8, 0, data8.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldo.zip");

            byte[] zip = Files.readAllBytes(zipPath.toPath());
            
            
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=respaldo.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();

            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
}
