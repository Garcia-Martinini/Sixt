package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_reserva")
public class TipoReserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_reserva")
    private Long idTipoReserva;
    @Column(name = "nombre_tipo_reserva")
    private String nombreTipoReserva;

    public TipoReserva() {
    }

    public TipoReserva(String nombreTipoReserva) {
        this.nombreTipoReserva = nombreTipoReserva;
    }

    public Long getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Long idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public String getNombreTipoReserva() {
        return nombreTipoReserva;
    }

    public void setNombreTipoReserva(String nombreTipoReserva) {
        this.nombreTipoReserva = nombreTipoReserva;
    }

    
}
