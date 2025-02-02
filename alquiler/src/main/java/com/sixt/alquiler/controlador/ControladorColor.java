package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sixt.alquiler.servicio.ColorServicio;

@Controller
public class ControladorColor {

    @Autowired
    private ColorServicio servicio;

    @GetMapping("/ABM_color")
    public String listarColores(Model modelo){
        modelo.addAttribute("colores", servicio.listarLosColores());
        return "ABM_color";
    }
}
