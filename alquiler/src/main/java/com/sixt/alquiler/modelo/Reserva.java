package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

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

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_estado")
    private int idEstado;

    @Column(name = "id_oficina_origen")
    private int idOficinaOrigen;

    @Column(name = "id_tipo_reserva")
    private int idTipoReserva;

    public Reserva() {
    }

    public Reserva(Date fechaInicio, Date fechaFin, Double precioTotal, Long idCliente, int idEstado, int idOficinaOrigen, int idTipoReserva) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idOficinaOrigen = idOficinaOrigen;
        this.idTipoReserva = idTipoReserva;
    }

    public Reserva(Long idReserva, Date fechaInicio, Date fechaFin, Double precioTotal, Long idCliente, int idEstado, int idOficinaOrigen, int idTipoReserva) {
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idOficinaOrigen = idOficinaOrigen;
        this.idTipoReserva = idTipoReserva;
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

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdOficinaOrigen() {
        return idOficinaOrigen;
    }

    public void setIdOficinaOrigen(int idOficinaOrigen) {
        this.idOficinaOrigen = idOficinaOrigen;
    }

    public int getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(int idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", precioTotal=" + precioTotal + ", idCliente=" + idCliente + ", idEstado=" + idEstado + ", idOficinaOrigen=" + idOficinaOrigen + ", idTipoReserva=" + idTipoReserva + '}';
    }
    
    
}

