package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(nullable = false, length = 50)
    private String usuario;
    @Column(nullable = false, length = 50)
    private String contrasenia;

    @ManyToOne
    private TipoUsuario tipoUsuario;
    
    @ManyToOne
    private Estado estado;

    public Usuario() {
    }

    public Usuario(String contrasenia, Estado estado, Long idUsuario, TipoUsuario tipoUsuario, String usuario) {
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
    }

    public Usuario(String contrasenia, Estado estado, TipoUsuario tipoUsuario, String usuario) {
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasenia=" + contrasenia
                + ", tipoUsuario=" + tipoUsuario + ", estado=" + estado + "]";
    }

      
}
