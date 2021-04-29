/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import gestion.PersonalGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.YearGender;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author wmolina
 */
@Named(value = "chartZoomView")
@SessionScoped
public class ChartZoomView implements Serializable {

    private LineChartModel zoomModel;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LineChartModel getZoomModel() {
        return zoomModel;
    }

    private LineChartModel initCategoryModel() {

        LineChartModel model = new LineChartModel();
        
        ChartSeries men = new ChartSeries();
        men.setLabel("Men");
        men.set("2002", 5);
        men.set("2003", 3);
        men.set("2004", 2);
        men.set("2006", 1);

        ChartSeries women = new ChartSeries();
        women.setLabel("Women");
        women.set("2001", 1);
        women.set("2002", 3);
        women.set("2003", 2);

        model.addSeries(men);
        model.addSeries(women);

        return model;
    }

    private void createLineModels() {
        zoomModel = initLinearModel();
        zoomModel.setTitle("Zoom");
        zoomModel.setZoom(true);
        zoomModel.setLegendPosition("e");
        Axis yAxis = zoomModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
         
        ChartSeries men = new ChartSeries();
        ChartSeries women = new ChartSeries();
        
        

        String label = "N/A";
        String label1 = "N/A";

        ArrayList<YearGender> list = PersonalGestion.getYearGender();
        int mayor = list.get(0).getTotal();

        ArrayList<String> genero = new ArrayList<>();

        list.forEach(linea -> {
            genero.add(linea.getGenero());
        });

        List<String> distinctGenders = genero.stream().distinct().collect(Collectors.toList());
        for (String s : distinctGenders) {
            if (s.equalsIgnoreCase("M")) {
                label = "Masculino";

            }
            if (s.equalsIgnoreCase("F")) {
                label1 = "Femenino";
            }
        }
        men.setLabel(label);
        women.setLabel(label1);
        
        for (YearGender row : list) {
            if (row.getGenero().equalsIgnoreCase("M")) {
                men.set(row.getYear(), row.getTotal());                
            }
            if (row.getGenero().equalsIgnoreCase("F")) {
                women.set(row.getYear(), row.getTotal());
            }
            if (mayor < row.getTotal()) {
                mayor = row.getTotal();
            }
        }
        model.addSeries(men);
        model.addSeries(women);
        

        return model;
    }

}
