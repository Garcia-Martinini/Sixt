package com.sixt.alquiler.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "color")
public class Color {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_color")
    private int idColor;
    @Column(name = "nombre_color")
    private String nombreColor;

    public Color() {
    }

    public Color(int idColor, String nombreColor) {
        this.idColor = idColor;
        this.nombreColor = nombreColor;
    }

    public Color(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    @Override
    public String toString() {
        return "Color{" + "idColor=" + idColor + ", nombreColor=" + nombreColor + '}';
    }
    
    
}
