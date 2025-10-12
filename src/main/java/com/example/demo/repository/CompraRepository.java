package com.example.demo.repository;



import com.example.demo.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    /**
     * Obtiene una lista de Compras cuyo atributo 'estado' es TRUE.
     * Estas son las transacciones que no han sido anuladas lógicamente.
     * Lista de compras activas.
     */
    List<Compra> findByEstadoTrue();
    

}