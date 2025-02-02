package com.sixt.alquiler.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String dni;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
    
    @ManyToOne
    private Estado estado;

    @OneToMany(mappedBy="cliente")
    private List<Reserva> reservas;



    public Cliente() {
    }



    public Cliente(String dni, String nombre, String direccion, String email, String telefono, Estado estado,
            List<Reserva> reservas) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.reservas = reservas;
    }

    public Long getCodigo() {
        return codigo;
    }



    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }



    public String getDni() {
        return dni;
    }



    public void setDni(String dni) {
        this.dni = dni;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getDireccion() {
        return direccion;
    }



    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getTelefono() {
        return telefono;
    }



    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public Estado getEstado() {
        return estado;
    }



    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    public List<Reserva> getReservas() {
        return reservas;
    }



    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    
}
