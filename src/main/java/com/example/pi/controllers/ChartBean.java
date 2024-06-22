package com.example.pi.controllers;

import com.example.pi.models.Plan_turistico;
import com.example.pi.models.Punto_visita;
import com.example.pi.repositories.ChartsRepositoryImpl;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.primefaces.model.chart.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Named("beanChart")
@SessionScoped
public class ChartBean implements Serializable {

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private BarChartModel barModel;

    private List<Integer> availableYears;
    private List<Integer> availableMonths;
    private Integer selectedYear;
    private Integer selectedMonth;

    public ChartBean() {

        int currentYear = LocalDate.now().getYear();
        availableYears = IntStream.rangeClosed(currentYear - 5, currentYear).boxed().collect(Collectors.toList());

        availableMonths = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
        selectedYear = 2023; // Año
        selectedMonth = 10; // Mes
        consulta1();
        consulta2();
    }

    public List<Integer> getAvailableYears() {
        return availableYears;
    }

    public List<Integer> getAvailableMonths() {
        return availableMonths;
    }

    public Integer getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(Integer selectedYear) {
        this.selectedYear = selectedYear;
    }

    public Integer getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(Integer selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public String getMonthName(int month) {
        return LocalDate.of(2000, month, 1).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    private DonutChartModel donutModel;

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public void consulta1() {
        ChartsRepositoryImpl charts = new ChartsRepositoryImpl();
        List<Punto_visita> consulta1;
        try {
            consulta1 = charts.consulta1(selectedYear, selectedMonth);
            graficoC1(consulta1);
        } catch (Exception e) {

        }
    }

    private void graficoC1(List<Punto_visita> consulta1) {
        pieModel1 = new PieChartModel();
        for (Punto_visita ch : consulta1) {
            pieModel1.set(ch.getNom_actividad(), ch.getCantidadcompras());
        }
        pieModel1.setTitle("Puntos turísticos que los clientes más " +
                "incluyen en sus compras");
        pieModel1.setLegendPosition("e");
        pieModel1.setFill(false);
        pieModel1.setShowDataLabels(true);
        pieModel1.setDiameter(150);
    }

    public void consulta2() {
        ChartsRepositoryImpl charts = new ChartsRepositoryImpl();
        List<Plan_turistico> consulta2;
        try {
            consulta2 = charts.consulta2(selectedYear, selectedMonth);
            graficoC2(consulta2);
        } catch (Exception e) {

        }
    }

    private void graficoC2(List<Plan_turistico> consulta2) {
        barModel = new BarChartModel();
        barModel.setTitle("Cantidad de planes turísticos que más se vendieron en un mes específico");
        barModel.setLegendPosition("e");

        Axis xAxis = new CategoryAxis("Planes Turísticos");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad de Ventas");
        yAxis.setMin(0);

        ChartSeries chartSeries = new ChartSeries();
        chartSeries.setLabel("Cantidad de Ventas");
        for (Plan_turistico pt : consulta2) {
            chartSeries.set(pt.getTitulo(), pt.getCant_planes());
        }
        barModel.addSeries(chartSeries);
        barModel.setAnimate(true);
        barModel.setShowPointLabels(true);
        barModel.setSeriesColors("8E44AD");
        
        Axis y2Axis = new LinearAxis();
        y2Axis.setMin(0);
        barModel.getAxes().put(AxisType.Y2, y2Axis);
    }
}