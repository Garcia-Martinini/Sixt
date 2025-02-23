package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estado")
    private int idEstado;
   
    @Column(name="nombre_estado")
    private String nombreEstado;

    public Estado() {
    }

    public Estado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int id) {
        this.idEstado = id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }


}
