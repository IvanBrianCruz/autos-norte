package com.example.demo.service;



import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Indica a Spring que esta clase es un componente de servicio
@Service
public class ClienteService {

    // Inyección de dependencias: permite usar los métodos del Repository
    @Autowired
    private ClienteRepository clienteRepository;

    // Métodos CRUD (5 métodos requeridos) 

    // 1. CREAR / GUARDAR (Create)
    /**
     * Guarda un nuevo cliente o actualiza uno existente.
     *  El objeto Cliente a persistir.
     *  El objeto Cliente guardado/actualizado.
     */
    public Cliente guardarCliente(Cliente cliente) {
        // La lógica de negocio podría ir aquí (ej: validar email antes de guardar)
        return clienteRepository.save(cliente);
    }
    
    // 2. "LEER TODOS' (Read All) - Filtrado por Borrado Lógico
    /**
     * Obtiene todos los clientes cuyo estado es TRUE (activos).
     * Usa el Query Method definido en el Repository.
     *  Lista de clientes activos.
     */
    public List<Cliente> obtenerTodosClientesActivos() {
        return clienteRepository.findByEstadoTrue();
    }
    
    // 3. LEER POR ID (Read By ID)
    /**
     * Obtiene un cliente por su ID, independientemente de su estado (activo o inactivo).
     *  El ID del cliente a buscar.
     *  Un objeto Optional que puede contener el Cliente.
     */
    public Optional<Cliente> obtenerClientePorId(Integer clienteId) {
        // Usamos findById que devuelve un Optional para manejar la posible ausencia del cliente.
        return clienteRepository.findById(clienteId);
    }
    
    // 4. ACTUALIZAR (Update)
    /**
     * Actualiza la información de un cliente existente.
     * id El ID del cliente a actualizar.
     * detallesCliente Los nuevos datos del cliente.
     *  El cliente actualizado o null si no se encontró.
     */
    public Cliente actualizarCliente(Integer clienteId, Cliente detallesCliente) {
        // 1. Busca el cliente existente
        return clienteRepository.findById(clienteId).map(clienteExistente -> {
            // 2. Actualiza los campos (se asume que el ID ya está validado)
            clienteExistente.setNombre(detallesCliente.getNombre());
            clienteExistente.setApellido(detallesCliente.getApellido());
            clienteExistente.setEmail(detallesCliente.getEmail());
            clienteExistente.setTelefono(detallesCliente.getTelefono());
            clienteExistente.setDireccion(detallesCliente.getDireccion());
            
            // Nota: Podrías optar por no actualizar el estado aquí, o dejar que la lógica de soft-delete lo maneje.
            // Para simplicidad, la actualización de estado solo se hace en eliminarClienteLogico.
            
            // 3. Guarda la entidad actualizada
            return clienteRepository.save(clienteExistente);
        }).orElse(null); // Devuelve null si no encuentra el cliente
    }

    // 5. ELIMINAR (Delete) - Borrado Lógico
    /**
     * Realiza un borrado lógico, cambiando el atributo 'estado' a FALSE.
     *  El ID del cliente a desactivar.
     *  true si la eliminación lógica fue exitosa, false si el cliente no fue encontrado.
     */
    public boolean eliminarClienteLogico(Integer clienteId) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(clienteId);    
        if (clienteEncontrado.isPresent()) {
            Cliente cliente = clienteEncontrado.get();
            cliente.setEstado(false); // 🔑 Lógica clave: Borrado Lógico
            clienteRepository.save(cliente); // Persiste el cambio de estado
            return true;
        }
        return false; // Cliente no encontrado para eliminar
    }
}