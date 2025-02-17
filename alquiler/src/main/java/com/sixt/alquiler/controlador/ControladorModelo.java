package com.sixt.alquiler.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sixt.alquiler.modelo.Modelo;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.ModeloServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorModelo {

    @Autowired
    private ModeloServicio servicio;

    @GetMapping("/listarABM_modelo")
    public String listarLosModelos(@ModelAttribute("usuarioSesion") Usuario usuario, Model model) {
        model.addAttribute("marcas", servicio.listarLosModelos());
        return "Administrador/ABM/modelo/ABM_modelo";
    }

    @GetMapping("/nuevoModelo")
    public String nuevoModeloFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, Model model) {
        Modelo modelo = new Modelo();
        model.addAttribute("modelo", modelo);
        return "Administrador/ABM/modelo/nuevo_modelo";
    }

    @PostMapping("/guardarModelo")
    public String guardarModelo(@ModelAttribute("modelo") Modelo modelo) {
        servicio.guardarModelo(modelo);
        return "redirect:/listarABM_modelo";
    }

    @GetMapping("/formularioModelo/{id}")
    public String modificarModeloFormulario(@ModelAttribute("usuarioSesion") Usuario usuario, @PathVariable Integer id, Model model) {
        Modelo modelo = servicio.obtenerModeloPorIdModelo(id);
        String nombreAnterior = modelo.getNombreModelo();
        model.addAttribute("modelo", modelo);
        model.addAttribute("nombreAnterior", nombreAnterior);
        return "Administrador/ABM/modelo/modificar_modelo";
    }

    @PostMapping("/actualizarModelo/{id}")
    public String actualizarModelo(@PathVariable Integer id, @ModelAttribute("modelo") Modelo modelo) {
        Modelo modeloModificado = servicio.obtenerModeloPorIdModelo(id);
        modeloModificado.setNombreModelo(modelo.getNombreModelo());;
        servicio.modificarModelo(modeloModificado);
        return "redirect:/listarABM_modelo";
    }

    @GetMapping("/eliminarModelo/{id}")
    public String eliminarModelo(@PathVariable Integer id) {
        servicio.eliminarModelo(id);
        return "redirect:/listarABM_modelo";
    }
}
