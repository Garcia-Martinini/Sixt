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

import com.sixt.alquiler.modelo.Modelo;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.MarcaServicio;
import com.sixt.alquiler.servicio.ModeloServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorModelo {

    @Autowired
    private ModeloServicio servicio;

    @Autowired
    private MarcaServicio servicioMarca;

    @GetMapping("/listarABM_modelo")
    public String listarLosModelos(@ModelAttribute("usuarioSesion") Usuario usuario, Model model) {
        model.addAttribute("modelos", servicio.listarLosModelos());
        return "Administrador/ABM/modelo/ABM_modelo";
    }

    @GetMapping("/nuevoModelo")
    public String nuevoModeloFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model model, @ModelAttribute("mensaje") String mensaje) {
        Modelo modelo = new Modelo();
        model.addAttribute("modelo", modelo);
        model.addAttribute("marcas", servicioMarca.listarLasMarcas());
        return "Administrador/ABM/modelo/nuevo_modelo";
    }

    @PostMapping("/guardarModelo")
    public String guardarModelo(@ModelAttribute("modelo") Modelo modelo, RedirectAttributes redirectAttributes) {
        List<Modelo> modelos = servicio.listarLosModelos();
        for (Modelo m : modelos) {
            if (m.getNombreModelo().equalsIgnoreCase(modelo.getNombreModelo())) {
                redirectAttributes.addFlashAttribute("mensaje", "El modelo que ingresaste ya existe");
                return "redirect:/nuevoModelo";
            }
        }
        servicio.guardarModelo(modelo);
        return "redirect:/listarABM_modelo";
    }

    @GetMapping("/formularioModelo/{id}")
    public String modificarModeloFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model model, @ModelAttribute("mensaje") String mensaje) {
        Modelo modelo = servicio.obtenerModeloPorIdModelo(id);
        String nombreAnterior = modelo.getNombreModelo();
        model.addAttribute("modelo", modelo);
        model.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/modelo/modificar_modelo";
    }

    @PostMapping("/actualizarModelo/{id}")
    public String actualizarModelo(@PathVariable Integer id, @ModelAttribute("modelo") Modelo modelo, RedirectAttributes redirectAttributes) {
        Modelo modeloModificado = servicio.obtenerModeloPorIdModelo(id);
        List<Modelo> modelos = servicio.listarLosModelos();
        if (modelo.getNombreModelo().isEmpty()) {
                redirectAttributes.addFlashAttribute("mensaje", "No se puede dejar el campo vacio");
                return "redirect:/formularioModelo/" + id;
            }
        for (Modelo m : modelos) {
            if (m.getNombreModelo().equalsIgnoreCase(modelo.getNombreModelo())) {
                redirectAttributes.addFlashAttribute("mensaje", "El modelo que ingresaste ya existe");
                return "redirect:/formularioModelo/" + id;
            }
        }
        modeloModificado.setNombreModelo(modelo.getNombreModelo());
        servicio.modificarModelo(modeloModificado);
        return "redirect:/listarABM_modelo";
    }

    @GetMapping("/eliminarModelo/{id}")
    public String eliminarModelo(@PathVariable Integer id) {
        servicio.eliminarModelo(id);
        return "redirect:/listarABM_modelo";
    }
}
