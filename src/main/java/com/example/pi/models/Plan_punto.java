package com.example.pi.models;

public class Plan_punto {
    private String id_planpuntovisita;
    private int id_plan, id_actividad;

    public String getId_planpuntovisita() {
        return id_planpuntovisita;
    }

    public void setId_planpuntovisita(String id_planpuntovisita) {
        this.id_planpuntovisita = id_planpuntovisita;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }
}
