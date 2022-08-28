package com.utn.frcu.entity;

public class Clave {
    private String valorInicio;
    private String valorCierre;

    public Clave(String valorInicio, String valorCierre) {
        this.valorInicio = valorInicio;
        this.valorCierre = valorCierre;
    }

    public String getValorInicio() {
        return valorInicio;
    }

    public String getValorCierre() {
        return valorCierre;
    }
}
