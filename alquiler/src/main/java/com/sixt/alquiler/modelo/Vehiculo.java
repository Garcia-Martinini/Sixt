package com.sixt.alquiler.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_oficina")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToMany(mappedBy = "vehiculos")
    private List<Reserva> reservas;

    public Vehiculo(int idVehiculo, double precioDiario, int combustible, String patente, Modelo modelo, Color color,
            Oficina oficina, Estado estado, Marca marca, List<Reserva> reservas) {
        this.idVehiculo = idVehiculo;
        this.precioDiario = precioDiario;
        this.combustible = combustible;
        this.patente = patente;
        this.modelo = modelo;
        this.color = color;
        this.oficina = oficina;
        this.estado = estado;
        this.marca = marca;
        this.reservas = reservas;
    }

    public Vehiculo() {
    }

    public Vehiculo(double precioDiario, int combustible, String patente, Modelo modelo, Color color, Oficina oficina,
            Estado estado, Marca marca, List<Reserva> reservas) {
        this.precioDiario = precioDiario;
        this.combustible = combustible;
        this.patente = patente;
        this.modelo = modelo;
        this.color = color;
        this.oficina = oficina;
        this.estado = estado;
        this.marca = marca;
        this.reservas = reservas;
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

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    
    
}
