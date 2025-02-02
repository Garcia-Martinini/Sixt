package com.sixt.alquiler.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorRegistro {
    
    @GetMapping("/")
    public String inicio(){
      //modelo.addAttribute("usuario", Usuario)
      return "Registro/registro";   
    }
    
}
