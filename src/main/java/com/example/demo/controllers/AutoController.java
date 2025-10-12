package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model; // ¡Importante!

import com.example.demo.model.Auto;

@Controller
public class AutoController {

    // Es mejor crear la instancia dentro del método si solo la usas allí,
    // o pasarla al método a través de un servicio, pero para este ejemplo,
    // la modificaremos en el método.

    @GetMapping("/auto")
    public String auto(Model model) { // Añade 'Model model' como argumento

        Auto nuevoAuto = new Auto(); // Crear una nueva instancia dentro del método
        //nuevoAuto.setMarca("Toyota");
        //nuevoAuto.setPatente("ABC123");

        // Añadir el objeto 'nuevoAuto' al Model con el nombre "auto" o "nuevoAuto"
        // El nombre "auto" es más idiomático si solo hay un objeto de ese tipo.
        model.addAttribute("auto", nuevoAuto); 

        return "auto"; // Nombre de la plantilla HTML
    }
}