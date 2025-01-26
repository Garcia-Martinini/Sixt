package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private int idVehiculo;
    @Column(name = "precio_diario")
    private double precioDiario;
    private int combustible;
    private String patente;

    @Column(name = "id_modelo")
    private int idModelo;

    @Column(name = "id_color")
    private int idColor;

    @Column(name = "id_oficina")
    private int idOficina;

    @Column(name = "id_estado")
    private int idEstado;

    public Vehiculo() {
    }

    public Vehiculo(double precioDiario, int combustible, String patente, int idModelo, int idColor, int idOficina, int idEstado) {
        this.precioDiario = precioDiario;
        this.combustible = combustible;
        this.patente = patente;
        this.idModelo = idModelo;
        this.idColor = idColor;
        this.idOficina = idOficina;
        this.idEstado = idEstado;
    }

    public Vehiculo(int idVehiculo, double precioDiario, int combustible, String patente, int idModelo, int idColor, int idOficina, int idEstado) {
        this.idVehiculo = idVehiculo;
        this.precioDiario = precioDiario;
        this.combustible = combustible;
        this.patente = patente;
        this.idModelo = idModelo;
        this.idColor = idColor;
        this.idOficina = idOficina;
        this.idEstado = idEstado;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public double getPrecioDiario() {
        return precioDiario;
    }

    public void setPrecioDiario(double precioDiario) {
        this.precioDiario = precioDiario;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "idVehiculo=" + idVehiculo + ", precioDiario=" + precioDiario + ", combustible=" + combustible + ", patente=" + patente + ", idModelo=" + idModelo + ", idColor=" + idColor + ", idOficina=" + idOficina + ", idEstado=" + idEstado + '}';
    }
    
    
}
