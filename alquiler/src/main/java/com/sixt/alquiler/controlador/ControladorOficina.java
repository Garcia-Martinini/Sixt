package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.OficinaServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorOficina {
    @Autowired
    private OficinaServicio servicio;

    @GetMapping("/listarABM_oficina")
    public String listarLasOficinas(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("oficinas", servicio.listarLasOficinas());
        return "Administrador/ABM/oficina/ABM_oficina";
    }

    @GetMapping("/nuevaOficina")
    public String nuevaOficinaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        Oficina oficina = new Oficina();
        modelo.addAttribute("oficina", oficina);
        return "Administrador/ABM/oficina/nueva_oficina";
    }

    @PostMapping("/guardarOficina")
    public String guardarOficina(@ModelAttribute("oficina") Oficina oficina) {
        servicio.guardarOficina(oficina);
        return "redirect:/listarABM_oficina";
    }

    @GetMapping("/formularioOficina/{id}")
    public String modificarOficinaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id,
            Model modelo) {
        Oficina oficina = servicio.obtenerOficinaPorIdOficina(id);
        String nombreAnterior = oficina.getNombreOficina();
        modelo.addAttribute("oficina", oficina);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/oficina/modificar_oficina";
    }

    @PostMapping("/actualizarOficina/{id}")
    public String actualizarOficina(@PathVariable Integer id, @ModelAttribute("oficina") Oficina oficina) {
        Oficina oficinaModificado = servicio.obtenerOficinaPorIdOficina(id);
        oficinaModificado.setNombreOficina(oficina.getNombreOficina());
        servicio.modificarOficina(oficinaModificado);
        return "redirect:/listarABM_oficina";
    }

    @GetMapping("/eliminarOficina/{id}")
    public String eliminarOficina(@PathVariable Integer id) {
        servicio.eliminarOficina(id);
        return "redirect:/listarABM_oficina";
    }
}
