package com.example.demo.repository;




import com.example.demo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    /**
     * Obtiene una lista de Compras cuyo atributo 'estado' es TRUE.
     * Estas son las transacciones que no han sido anuladas lógicamente.
     * Lista de compras activas.
     */
    List<Vendedor> findByEstadoTrue();
    

}