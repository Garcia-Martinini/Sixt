package com.sixt.alquiler.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String nuevaOficinaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Oficina oficina = new Oficina();
        modelo.addAttribute("oficina", oficina);
        return "Administrador/ABM/oficina/nueva_oficina";
    }

    @PostMapping("/guardarOficina")
    public String guardarOficina(@ModelAttribute("oficina") Oficina oficina, RedirectAttributes redirectAttributes) {
        List<Oficina> oficinas = servicio.listarLasOficinas();
        for (Oficina o : oficinas) {
            if (o.getNombreOficina().equalsIgnoreCase(oficina.getNombreOficina())) {
                redirectAttributes.addFlashAttribute("mensaje", "La oficina que ingresaste ya existe");
                return "redirect:/nuevaOficina";
            }
            if (o.getDireccionOficina().equalsIgnoreCase(oficina.getDireccionOficina())) {
                redirectAttributes.addFlashAttribute("mensaje", "La oficina que ingresaste ya existe");
                return "redirect:/nuevaOficina";
            }
        }
        servicio.guardarOficina(oficina);
        return "redirect:/listarABM_oficina";
    }

    @GetMapping("/formularioOficina/{id}")
    public String modificarOficinaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id,Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Oficina oficina = servicio.obtenerOficinaPorIdOficina(id);
        String nombreAnterior = oficina.getNombreOficina();
        String direccionAnterior = oficina.getDireccionOficina();
        modelo.addAttribute("oficina", oficina);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        modelo.addAttribute("direccionAnterior", direccionAnterior);
        return "Administrador/ABM/oficina/modificar_oficina";
    }

    @PostMapping("/actualizarOficina/{id}")
    public String actualizarOficina(@PathVariable Integer id, @ModelAttribute("oficina") Oficina oficina, RedirectAttributes redirectAttributes) {
        Oficina oficinaModificado = servicio.obtenerOficinaPorIdOficina(id);
        List<Oficina> oficinas = servicio.listarLasOficinas();
        if (oficina.getNombreOficina().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensaje", "No se puede dejar el campo vacio");
                return "redirect:/formularioOficina/" + id;
            }
        for (Oficina o : oficinas) {
            if (o.getNombreOficina().equalsIgnoreCase(oficina.getNombreOficina())) {
                redirectAttributes.addFlashAttribute("mensaje", "La oficina que ingresaste ya existe");
                return "redirect:/formularioOficina/" + id;
            }
            if (o.getDireccionOficina().equalsIgnoreCase(oficina.getDireccionOficina())) {
                redirectAttributes.addFlashAttribute("mensaje", "La oficina que ingresaste ya existe");
                return "redirect:/formularioOficina/" + id;
            }
        }
        oficinaModificado.setNombreOficina(oficina.getNombreOficina());
        oficinaModificado.setDireccionOficina(oficina.getDireccionOficina());
        servicio.modificarOficina(oficinaModificado);
        return "redirect:/listarABM_oficina";
    }

    @GetMapping("/eliminarOficina/{id}")
    public String eliminarOficina(@PathVariable Integer id) {
        servicio.eliminarOficina(id);
        return "redirect:/listarABM_oficina";
    }
}
