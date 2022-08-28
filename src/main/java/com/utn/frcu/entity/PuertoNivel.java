package com.utn.frcu.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuertoNivel {

    private String puerto;
    private String rio;
    private String nivel;
    private String fechaHora;
    private String alerta;
    private String evacuacion;

    public PuertoNivel() {
    }

    public PuertoNivel(String puerto, String rio, String nivel, String fechaHora, String alerta, String evacuacion) {
        this.puerto = puerto;
        this.rio = rio;
        this.nivel = nivel;
        this.fechaHora = fechaHora;
        this.alerta = alerta;
        this.evacuacion = evacuacion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getRio() {
        return rio;
    }

    public void setRio(String rio) {
        this.rio = rio;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getEvacuacion() {
        return evacuacion;
    }

    public void setEvacuacion(String evacuacion) {
        this.evacuacion = evacuacion;
    }

    public List<Object> toList() {
        List<Object> list = new ArrayList<>();
        list.addAll(Arrays.asList(this.rio, this.puerto, this.fechaHora, this.nivel, this.alerta,  this.evacuacion));
        return list;

    }
}

