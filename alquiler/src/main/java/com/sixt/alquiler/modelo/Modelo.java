package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelo")
public class Modelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private int idModelo;
    @Column(name = "nombre_modelo")
    private String nombreModelo;

    @Column(name = "id_marca")
    private int idMarca;

    public Modelo() {
    }

    public Modelo(int idModelo, String nombreModelo, int idMarca) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.idMarca = idMarca;
    }

    public Modelo(String nombreModelo, int idMarca) {
        this.nombreModelo = nombreModelo;
        this.idMarca = idMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public String toString() {
        return "Modelo{" + "idModelo=" + idModelo + ", nombreModelo=" + nombreModelo + ", idMarca=" + idMarca + '}';
    }
    
    
}
