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
        // 2. Si el Optional está vacío, orElseThrow lanza la excepción ResponseStatusException.
        // 3. Spring captura esta excepción y devuelve automáticamente un código de estado HTTP 404.
        
        Cliente cliente = clienteService.obtenerClientePorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + id));
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

 
}
