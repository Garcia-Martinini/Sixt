package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "oficina")
public class Oficina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficina")
    private int idOficina;
    @Column(name = "nombre_oficina")
    private String nombreOficina;
    @Column(name = "direccion_oficina")
    private String direccionOficina;
    
    @Column(name = "id_estado")	
    private int idEstado;

    public Oficina() {
    }

    public Oficina(String nombreOficina, String direccionOficina, int idEstado) {
        this.nombreOficina = nombreOficina;
        this.direccionOficina = direccionOficina;
        this.idEstado = idEstado;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public String getNombreOficina() {
        return nombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public String getDireccionOficina() {
        return direccionOficina;
    }

    public void setDireccionOficina(String direccionOficina) {
        this.direccionOficina = direccionOficina;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Oficina{" + "idOficina=" + idOficina + ", nombreOficina=" + nombreOficina + ", direccionOficina=" + direccionOficina + ", idEstado=" + idEstado + '}';
    }    
    
}

