package com.sixt.alquiler.modelo;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Column(name = "precio_total")
    private Double precioTotal;
    @Column(name = "dias_totales")
    private int diasTotales;

    @ManyToMany
    @JoinTable(
        name = "reservas_vehiculos", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_reserva"), // FK en la tabla intermedia
        inverseJoinColumns = @JoinColumn(name = "id_vehiculo") // FK del otro lado
    )
    private List<Vehiculo> vehiculos;

    @ManyToOne
    @JoinColumn(name = "codigo")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_oficina")
    private Oficina oficinaOrigen;

    @ManyToOne
    @JoinColumn(name = "idTipoReserva")
    private TipoReserva tipoReserva;

    public Reserva() {
    }

    public Reserva(Date fechaInicio, Date fechaFin, Double precioTotal, int diasTotales, List<Vehiculo> vehiculos,
            Cliente cliente, Estado estado, Oficina oficinaOrigen, TipoReserva tipoReserva) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.diasTotales = diasTotales;
        this.vehiculos = vehiculos;
        this.cliente = cliente;
        this.estado = estado;
        this.oficinaOrigen = oficinaOrigen;
        this.tipoReserva = tipoReserva;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getDiasTotales() {
        return diasTotales;
    }

    public void setDiasTotales(int diasTotales) {
        this.diasTotales = diasTotales;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Oficina getOficinaOrigen() {
        return oficinaOrigen;
    }

    public void setOficinaOrigen(Oficina oficinaOrigen) {
        this.oficinaOrigen = oficinaOrigen;
    }

    public TipoReserva getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(TipoReserva tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

     
}

