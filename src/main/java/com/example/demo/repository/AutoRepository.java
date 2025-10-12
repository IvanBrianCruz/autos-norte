package com.example.demo.repository;



import com.example.demo.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutoRepository extends JpaRepository<Auto, Integer> {

    /**
     * Obtiene una lista de Modelos de Autos cuyo atributo 'estado' es TRUE.
     * Estos son los modelos/configuraciones disponibles para la venta.
     *  Lista de modelos de autos activos.
     */
    List<Auto> findByEstadoTrue();
    
    
}