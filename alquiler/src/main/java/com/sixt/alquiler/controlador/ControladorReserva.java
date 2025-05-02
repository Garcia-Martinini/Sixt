package com.sixt.alquiler.controlador;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.modelo.TipoReserva;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.OficinaServicio;
import com.sixt.alquiler.servicio.ReservaServicio;
import com.sixt.alquiler.servicio.TipoReservaServicio;


@Controller
@SessionAttributes("usuarioSesion")
public class ControladorReserva {
    
    @Autowired
    private ReservaServicio servicioReserva;

    @Autowired
    private EstadoServicio servicioEstado;
    
    @Autowired
    private TipoReservaServicio servicioTipoReserva;
    @Autowired
    private OficinaServicio servicioOficina;


      // Recurso que permite mostrar el formulario para crear una nueva reserva
    @GetMapping("/Clientes/NuevaReserva")
    public String mostrarFormCrearReserva(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {

        Reserva reserva = new Reserva();
        List<TipoReserva> tiposReserva = servicioTipoReserva.listarLosTipoReserva();
        List<Oficina> oficinas = servicioOficina.listarLasOficinas();
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("oficinas", oficinas);
        modelo.addAttribute("tiposReserva", tiposReserva);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/alta_reserva";
    }

    

     // Recurso que permite guardar una nueva reserva
    @PostMapping("/guardarReserva")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva, RedirectAttributes redirectAttributes) {

        Reserva reservaNueva = new Reserva();
        Estado estado = servicioEstado.obtenerEstadoPorIdEstado(1);

        reservaNueva.setEstado(estado);
        reservaNueva.setTipoReserva(reserva.getTipoReserva());
        reservaNueva.setCliente(reserva.getCliente());
        reservaNueva.setFechaInicio(reserva.getFechaInicio());
        reservaNueva.setFechaFin(reserva.getFechaFin());
        reservaNueva.setVehiculos(reserva.getVehiculos());
        reservaNueva.setPrecioTotal(reserva.getPrecioTotal());
        reservaNueva.setDiasTotales(reserva.getDiasTotales());
        reservaNueva.setOficinaOrigen(reserva.getOficinaOrigen());
        servicioReserva.guardarReserva(reservaNueva);

        return "redirect:/Clientes/NuevaReserva";
    }

}
