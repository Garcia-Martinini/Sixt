package com.sixt.alquiler.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorAdministrador {

    @GetMapping("/administrador")
    public String inicioAdministrador(){
        return "Administrador/administrador";
    }
}
