package com.example.pi.controllers;

import com.example.pi.models.Plan_turistico;
import com.example.pi.repositories.ChartsRepositoryImpl;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Named
@RequestScoped
public class ChartJsView implements Serializable {

    private static final long serialVersionUID = 1L;
    ChartsRepositoryImpl cs = new ChartsRepositoryImpl();

    private LineChartModel lineModel;
    private long costoplanes, ganancias;
    private int cantClientes;

    private DonutChartModel donutModel;


    @PostConstruct
    public void init() {
        cargardatos();
        createLineModel();
        createDonutModel();
    }

    public void cargardatos() {
        costoplanes=cs.costo_promedio_planes();
        cantClientes=cs.Cantidad_clientes();
        ganancias=cs.total_ganancias();
    }

    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        List<Plan_turistico> x = cs.consulta2(2023, 11);
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        for (int i =0;i< x.size();i++) {
            values.add(x.get(i).getCant_planes());
        }
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Planes mas vendidos en noviembre");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        for (int i =0;i< x.size();i++) {
            labels.add(x.get(i).getTitulo());
        }
        data.setLabels(labels);
        lineModel.setData(data);
    }


    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartOptions options = new DonutChartOptions();
        donutModel.setOptions(options);
        ChartsRepositoryImpl chartsRepository = new ChartsRepositoryImpl();
        int valor1 = chartsRepository.consulta1(2023, 10).size();
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = cs.costo_x_temporada();
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Media");
        labels.add("Alta");
        labels.add("Baja");
        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public long getCostoplanes() {
        return costoplanes;
    }

    public void setCostoplanes(long costoplanes) {
        this.costoplanes = costoplanes;
    }




    public String getMonthName(int month) {
        return LocalDate.of(2000, month, 1).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }


    public long getGanancias() {
        return ganancias;
    }

    public void setGanancias(long ganancias) {
        this.ganancias = ganancias;
    }

    public int getCantClientes() {
        return cantClientes;
    }

    public void setCantClientes(int cantClientes) {
        this.cantClientes = cantClientes;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }


}
