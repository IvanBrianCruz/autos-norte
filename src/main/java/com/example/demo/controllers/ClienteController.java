package com.example.demo.controllers;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; // CAMBIADO a @Controller
import org.springframework.ui.Model; // IMPORTADO para pasar datos a la vista
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
//import java.util.Optional; // Necesario para la búsqueda por ID

// Define que esta clase es un controlador MVC tradicional

@Controller

public class ClienteController {

    private final ClienteService clienteService;

    // Inyección de Dependencias por Constructor (Java 17 style)
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // --- MÉTODOS MVC ---

    // 1. Mostrar la lista de clientes activos (READ ALL - Vista Principal)
    // GET /clientes/
    @GetMapping("/listarClientes")
    public String listarClientesActivos(Model model) {
        // Obtenemos solo los clientes activos
        List<Cliente> clientes = clienteService.obtenerTodosClientesActivos();

        // Agregamos la lista al objeto Model para que la vista pueda acceder a ella
        model.addAttribute("clientes", clientes);

        // Retorna el nombre de la plantilla HTML a renderizar (ej: Thymeleaf o JSP)
        return "listaClientes";
    }

    // 2. Mostrar el formulario para registrar un nuevo cliente
    // GET /clientes/nuevo
    @GetMapping("/nuevoCliente")
    public String mostrarFormularioRegistro(Model model) {
        // Agregamos un objeto Cliente vacío para que el formulario pueda llenarlo
        model.addAttribute("cliente", new Cliente());
        return "formCliente"; // Vista HTML del formulario
    }

    // 3. Guardar nuevo cliente (CREATE)
    // POST /clientes/guardar
    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        // El servicio guarda el objeto enviado desde el formulario
        clienteService.guardarCliente(cliente);

        // Redirige al usuario a la lista principal después de guardar
        return "redirect:/listarClientes";
    }

    // 4. VER DETALLE DEL CLIENTE (READ By ID) - MÁS CONCISO
    // GET /detalleCliente/{id}
    @GetMapping("/detalleCliente/{id}")
    public String verDetalleCliente(@PathVariable("id") Integer id, Model model) {

        // Uso de orElseThrow():
        // 1. Llama al servicio, que devuelve un Optional<Cliente>.
        // 2. Si el Optional está vacío, orElseThrow lanza la excepción
        // ResponseStatusException.
        // 3. Spring captura esta excepción y devuelve automáticamente un código de
        // estado HTTP 404.

        Cliente cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + id));
        // Si el cliente fue encontrado, el código continúa aquí.
        model.addAttribute("cliente", cliente);

        // Retorna el nombre de la plantilla HTML de detalle
        return "detalleCliente";
    }

    // 5. ELIMINAR CLIENTE (DELETE - Borrado Lógico)
    // GET /eliminarCliente/{id}
    @GetMapping("/eliminarCliente/{id}")
    public String eliminarClienteLogico(@PathVariable("id") Integer id) {

        clienteService.eliminarClienteLogico(id);

        // Redirige al usuario a la lista principal después de la operación
        return "redirect:/listarClientes";
    }

    // EDITAR CLIENTE (UPDATE) // GET /editarCliente/{id}









































    // 6. MOSTRAR FORMULARIO PARA EDITAR (UPDATE - GET)
    // GET /editarCliente/{id}
    @GetMapping("/editarCliente/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        // 1. Obtener el cliente por ID, lanzando 404 si no existe
    Cliente cliente = clienteService.obtenerClientePorId(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado para editar con ID: " + id));
    
    // 2. Agregar el cliente encontrado al modelo
    model.addAttribute("cliente", cliente);
        // 2. Agregar el cliente encontrado al modelo
        model.addAttribute("cliente", cliente);

        // 3. Reutilizar la vista del formulario (que ya está preparada para edición)
        return "formCliente";
    }

    // 7. PROCESAR ACTUALIZACIÓN (UPDATE - POST)
    // POST /actualizarCliente/{id}
    @PostMapping("/actualizarCliente/{id}")
    public String actualizarCliente(@PathVariable("id") Integer id, @ModelAttribute Cliente clienteActualizado) {
        // 1. Establecer el ID en el objeto recibido del formulario
        // Esto es vital ya que el ModelAttribute lo crea, pero necesitamos el ID para
        // el Service.
        clienteActualizado.setClienteId(id);

        // 2. Llamar al servicio de actualización
        Cliente clienteResultado = clienteService.actualizarCliente(id, clienteActualizado);

        // 3. Manejo de error (si no se encontró el cliente original en el servicio)
        

        // 4. Redirigir a la lista de clientes o a la vista de detalle
        return "redirect:/listarClientes"; // O "redirect:/detalleCliente/" + id;
    }

}
