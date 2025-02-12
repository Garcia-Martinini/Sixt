package com.sixt.alquiler.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.ClienteServicio;


@Controller
@SessionAttributes("usuarioSesion")
public class ControladorVendedor {
    @Autowired
    private ClienteServicio servicio;

    @GetMapping("/Clientes")
    public String mostrarLogin(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        List <Cliente> clientes= servicio.listarTodosLosClientes();
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/listado_clientes";
    }

}
