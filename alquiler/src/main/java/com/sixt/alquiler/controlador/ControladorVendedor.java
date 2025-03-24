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
import com.sixt.alquiler.modelo.Oficina;
import com.sixt.alquiler.modelo.Reserva;
import com.sixt.alquiler.modelo.TipoReserva;
import com.sixt.alquiler.modelo.Usuario;
import com.sixt.alquiler.modelo.Vehiculo;
//import com.sixt.alquiler.modelo.Vendedor;
import com.sixt.alquiler.servicio.ClienteServicio;
import com.sixt.alquiler.servicio.EstadoServicio;
import com.sixt.alquiler.servicio.OficinaServicio;
import com.sixt.alquiler.servicio.ReservaServicio;
import com.sixt.alquiler.servicio.TipoReservaServicio;
import com.sixt.alquiler.servicio.VehiculoServicio;
//import com.sixt.alquiler.servicio.VendedorServicio;

@Controller
@SessionAttributes("usuarioSesion")
public class ControladorVendedor {
    @Autowired
    private ClienteServicio servicio;
    @Autowired
    private EstadoServicio servicio1;
    @Autowired
    private ReservaServicio servicio2;
    @Autowired
    private TipoReservaServicio servicio3;
    @Autowired
    private VehiculoServicio servicio4;
    @Autowired
    private OficinaServicio servicio5;
   // @Autowired
    //private VendedorServicio servicio5;

    // Recurso que permite mostrar la lista de clientes
    @GetMapping("/Clientes")
    public String mostrarClientes(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        List<Cliente> clientes = servicio.listarTodosLosClientes();
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
        Estado estado = new Estado();
        estado = servicio1.obtenerEstadoPorId(1);
        clienteNuevo.setEstado(estado);
        clienteNuevo.setNombre(cliente.getNombre());
        clienteNuevo.setDni(cliente.getDni());
        clienteNuevo.setDireccion(cliente.getDireccion());
        clienteNuevo.setEmail(cliente.getEmail());
        clienteNuevo.setTelefono(cliente.getTelefono());
        servicio.guardarCliente(clienteNuevo);
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
    public String editarCliente(@ModelAttribute("usuarioSesion") Usuario usuario,
            @ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes) {
        Cliente clienteEncontrado = servicio.obtenerClientePorId(cliente.getCodigo());
        if (clienteEncontrado != null) {
            clienteEncontrado = servicio.obtenerClientePorId(cliente.getCodigo());
            redirectAttributes.addFlashAttribute("cliente", clienteEncontrado);
            return "redirect:/Editar";
        }
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
        Cliente clienteEditado = servicio.obtenerClientePorId(id);
        clienteEditado.setNombre(cliente.getNombre());
        clienteEditado.setDni(cliente.getDni());
        clienteEditado.setDireccion(cliente.getDireccion());
        clienteEditado.setEmail(cliente.getEmail());
        clienteEditado.setTelefono(cliente.getTelefono());
        servicio.modificarCliente(clienteEditado);
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
    public String eliminarCliente(@ModelAttribute("usuarioSesion") Usuario usuario,
            @ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes) {

        Cliente clienteAEliminar = servicio.obtenerClientePorId(cliente.getCodigo());
        if (clienteAEliminar != null) {
            servicio.eliminarCliente(clienteAEliminar.getCodigo());
            return "redirect:/Clientes";
        }
        return "redirect:/Clientes/Elegir";
    }

    // Recurso que permite mostrar el formulario para crear una nueva reserva
    @GetMapping("/Clientes/NuevaReserva")
    public String mostrarFormCrearReserva(@ModelAttribute("usuarioSesion") Usuario usuario, Model modelo) {
        
        Reserva reserva = new Reserva();
        List<TipoReserva> tiposReserva = servicio3.listarTodosLosTiposReserva();
        List<Oficina> oficinas = servicio5.listarTodasLasOficinas();
        
        
        modelo.addAttribute("oficinas", oficinas);
        modelo.addAttribute("tiposReserva", tiposReserva);
        modelo.addAttribute("reserva", reserva);
        modelo.addAttribute("vendedor", usuario);
        return "Vendedor/alta_reserva";
    }

    @GetMapping("/ObtenerCliente")
    @ResponseBody
    public Cliente obtenerCliente(@RequestParam("idCliente") Long codigo) {
        
        Cliente cliente = servicio.obtenerClientePorId(codigo);
        
            return cliente;    
    }

    //Recursos que permiten obtener los vehiculos disponibles en una oficina
    @GetMapping("/vehiculosPorOficina")
    @ResponseBody
    public List<Vehiculo> obtenerVehiculosPorOficina(@RequestParam("idOficina") int idOficina) {
        return servicio4.listarVehiculosDisponiblesEnOficina(idOficina,3);
    }

    //Recursos que permiten obtener el precio diario de un vehiculo
    @GetMapping("/precioDiario")
    @ResponseBody   
    public double obtenerPrecioDiario(@RequestParam("idVehiculo") int idVehiculo) {
        Vehiculo vehiculo = servicio4.obtenerVehiculoPorId(idVehiculo);
        return vehiculo.getPrecioDiario();
    }

    // Recurso que permite guardar una nueva reserva
    @PostMapping("/guardarReserva")
    public String guardarReserva(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes) {
        /*
         * Cliente clienteNuevo = new Cliente();
         * Estado estado = new Estado();
         * estado = servicio1.obtenerEstadoPorId(1);
         * clienteNuevo.setEstado(estado);
         * clienteNuevo.setNombre(cliente.getNombre());
         * clienteNuevo.setDni(cliente.getDni());
         * clienteNuevo.setDireccion(cliente.getDireccion());
         * clienteNuevo.setEmail(cliente.getEmail());
         * clienteNuevo.setTelefono(cliente.getTelefono());
         * servicio.guardarCliente(clienteNuevo);
         */
        return "redirect:/Clientes/NuevaReserva";
    }
}
