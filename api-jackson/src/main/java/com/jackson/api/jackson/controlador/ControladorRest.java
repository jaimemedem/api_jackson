package com.jackson.api.jackson.controlador;

import com.jackson.api.jackson.modelo.ModeloFormularioContacto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ControladorRest {

    private final Map<String, ModeloFormularioContacto> formularios = new HashMap<>();

    @PostMapping("/atencion_al_cliente/mensaje")
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloFormularioContacto crea(@RequestBody ModeloFormularioContacto formularioContacto) {
        formularios.put(formularioContacto.email(), formularioContacto);
        return formularioContacto;
    }

    @GetMapping("/mensajes")
    public Collection<ModeloFormularioContacto> obtenerTodos() {
        return formularios.values();
    }

    @DeleteMapping("/api/contactos/{email}")
    public ResponseEntity<String> eliminarContacto(@PathVariable String email) {
        if (formularios.containsKey(email)) {
            formularios.remove(email);
            return ResponseEntity.ok("Contacto eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un contacto con el email: " + email);
        }
    }



}


