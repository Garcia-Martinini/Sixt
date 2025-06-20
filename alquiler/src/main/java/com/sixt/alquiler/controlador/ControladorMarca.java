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

import com.sixt.alquiler.modelo.Marca;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.MarcaServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorMarca {

    @Autowired
    private MarcaServicio servicio;

    @GetMapping("/listarABM_marca")
    public String listarLasMarcas(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("marcas", servicio.listarLasMarcas());
        return "Administrador/ABM/marca/ABM_marca";
    }

    @GetMapping("/nuevaMarca")
    public String nuevaMarcaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Marca marca = new Marca();
        modelo.addAttribute("marca", marca);
        return "Administrador/ABM/marca/nueva_marca";
    }

    @PostMapping("/guardarMarca")
    public String guardarMarca(@ModelAttribute("marca") Marca marca, RedirectAttributes redirectAttributes) {
        List<Marca> marcas = servicio.listarLasMarcas();
        for (Marca m : marcas) {
            if (m.getNombreMarca().equalsIgnoreCase(marca.getNombreMarca())) {
                redirectAttributes.addFlashAttribute("mensaje", "La marca que ingresaste ya existe");
                return "redirect:/nuevaMarca";
            }
        }
        servicio.guardarMarca(marca);
        return "redirect:/listarABM_marca";
    }

    @GetMapping("/formularioMarca/{id}")
    public String modificarMarcaFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model modelo, @ModelAttribute("mensaje") String mensaje) {
        Marca marca = servicio.obtenerMarcaPorIdMarca(id);
        String nombreAnterior = marca.getNombreMarca();
        modelo.addAttribute("marca", marca);
        modelo.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/marca/modificar_marca";
    }

    @PostMapping("/actualizarMarca/{id}")
    public String actualizarMarca(@PathVariable Integer id, @ModelAttribute("marca") Marca marca, RedirectAttributes redirectAttributes) {
        Marca marcaModificada = servicio.obtenerMarcaPorIdMarca(id);
        List<Marca> marcas = servicio.listarLasMarcas();
        if (marca.getNombreMarca().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensaje", "No se puede dejar el campo vacio");
                return "redirect:/formularioMarca/" + id;
            }
        for (Marca m : marcas) {
            if (m.getNombreMarca().equalsIgnoreCase(marca.getNombreMarca())) {
                redirectAttributes.addFlashAttribute("mensaje", "La marca que ingresaste ya existe");
                return "redirect:/formularioMarca/" + id;
            }
        }
        marcaModificada.setNombreMarca(marca.getNombreMarca());
        servicio.modificarMarca(marcaModificada);
        return "redirect:/listarABM_marca";
    }

    @GetMapping("/eliminarMarca/{id}")
    public String eliminarMarca(@PathVariable Integer id) {
        servicio.eliminarMarca(id);
        return "redirect:/listarABM_marca";
    }
}
