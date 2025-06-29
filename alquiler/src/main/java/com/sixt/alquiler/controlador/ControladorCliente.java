package com.sixt.alquiler.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sixt.alquiler.modelo.Cliente;
import com.sixt.alquiler.modelo.Estado;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.servicio.ClienteServicio;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.ReservaServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorCliente {

    @GetMapping("/gestionCliente")
    public String mostrarPanelInicioCliente(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        modelo.addAttribute("cliente", usuario);
        return "Cliente/cliente";

    }

    @Autowired
    private ClienteServicio servicioCliente;
    @Autowired
    private EstadoServicio servicioEstado;
    @Autowired
    private ReservaServicio servicioReserva;

    // Recurso que permite mostrar la lista de clientes
    @GetMapping("/Clientes")
    public String mostrarClientes(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        List<Cliente> clientes = servicioCliente.listarTodosLosClientes();
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/listado_clientes";
    }

    // Recurso que permite mostrar el formulario para crear un nuevo cliente
    @GetMapping("/Clientes/Crear")
    public String mostrarFormCrearCliente(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/alta_cliente";
    }

    // Recurso que permite guardar un nuevo cliente
    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes) {
        Cliente clienteNuevo = new Cliente();
        Estado estado = servicioEstado.obtenerEstadoPorIdEstado(1);//revisar el id a asignar (activo deberia ser)
        clienteNuevo.setEstado(estado);
        clienteNuevo.setNombre(cliente.getNombre());
        clienteNuevo.setDni(cliente.getDni());
        clienteNuevo.setDireccion(cliente.getDireccion());
        clienteNuevo.setEmail(cliente.getEmail());
        clienteNuevo.setTelefono(cliente.getTelefono());
        servicioCliente.guardarCliente(clienteNuevo);
        return "redirect:/Clientes";
    }

    // Recurso que permite buscar un cliente x id para editar
    @GetMapping("/Clientes/Buscar")
    public String buscarCliente(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/buscar_cliente";
    }

    // Recurso que permite evalua si el cliente existe y redirige a la vista de
    // edicion o a la busqueda
    @PostMapping("/editarCliente")
    public String editarCliente(@RequestParam("codigo") Long codigo,
            RedirectAttributes redirectAttributes) {
        Cliente clienteEncontrado = servicioCliente.obtenerClientePorId(codigo);
        if (clienteEncontrado != null) {
            clienteEncontrado = servicioCliente.obtenerClientePorId(codigo);
            redirectAttributes.addFlashAttribute("cliente", clienteEncontrado);
            return "redirect:/Editar";
        }
        redirectAttributes.addFlashAttribute("mensaje", "cliente no encontrado");
        return "redirect:/Clientes/Buscar";

    }

    // Recurso que permite mostrar el formulario para editar un cliente
    @GetMapping("/Editar")
    public String mostrarFormEditarCliente(@ModelAttribute("usuarioSesion") Usuario usuario,
            @ModelAttribute("cliente") Cliente cliente, Model modelo) {
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/editar_cliente";
    }

    // Recurso que permite modificar un cliente en la base de datos
    @PostMapping("/actualizarCliente/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("cliente") Cliente cliente,
            RedirectAttributes redirectAttributes) {
        Cliente clienteEditado = servicioCliente.obtenerClientePorId(id);
        clienteEditado.setNombre(cliente.getNombre());
        clienteEditado.setDni(cliente.getDni());
        clienteEditado.setDireccion(cliente.getDireccion());
        clienteEditado.setEmail(cliente.getEmail());
        clienteEditado.setTelefono(cliente.getTelefono());
        servicioCliente.modificarCliente(clienteEditado);
        return "redirect:/Clientes";
    }

    // Recurso que permite buscar un cliente x id para eliminar
    @GetMapping("/Clientes/Elegir")
    public String elegirCliente(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/elegir_Cliente";
    }

    // Recurso que permite evalua si el cliente existe y redirige a la vista de
    // eliminacion o a la busqueda
    @PostMapping("/eliminarCliente")
    public String eliminarCliente(@RequestParam("codigo") Long codigo, RedirectAttributes redirectAttributes) {

        Cliente clienteAEliminar = servicioCliente.obtenerClientePorId(codigo);
        if (clienteAEliminar != null) {

            Boolean tieneReservas = servicioReserva.VerificarExistenciaReservasPorCliente(clienteAEliminar);
            if (tieneReservas) {
                redirectAttributes.addFlashAttribute("mensaje",
                        "No se puede eliminar el cliente porque tiene reservas activas.");
                return "redirect:/Clientes/Elegir";
            }
            servicioReserva.eliminarReservasPorCliente(clienteAEliminar);
            servicioCliente.eliminarCliente(codigo);
            return "redirect:/Clientes";
        }
        redirectAttributes.addFlashAttribute("mensaje", "cliente no encontrado");
        return "redirect:/Clientes/Elegir";
    }

    @GetMapping("/ObtenerCliente")
    @ResponseBody
    public Cliente obtenerCliente(@RequestParam("idCliente") Long codigo) {

        Cliente cliente = servicioCliente.obtenerClientePorId(codigo);

        return cliente;
    }
}
