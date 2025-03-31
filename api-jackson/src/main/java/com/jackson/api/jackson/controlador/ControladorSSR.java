package com.jackson.api.jackson.controlador;

import com.jackson.api.jackson.modelo.ModeloFormularioContacto;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorSSR {

    @GetMapping("/jackson")
    public String mostrarMenu(){
        return "index";
    }
    @GetMapping("/about_us")
    public String mostrarAbout(Model model)
    {
        return "about_us";
    }

    @GetMapping("/sorry")
    public String mostrarSorry(Model model)
    {
        return "sorry";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mensaje")
    public String mostrarMensajes(Model model)
    {
        return "contacto";
    }


    @GetMapping("/atencion_al_cliente")
    public String mostrarAtencion(Model model)
    {
        model.addAttribute("contacto", new ModeloFormularioContacto("","","",""));
        return "atencion_al_cliente";
    }

    @PostMapping("/contacto")
    public String procesarFormulario(@Valid @ModelAttribute("contacto") ModeloFormularioContacto contacto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "atencion_al_cliente";
        }
        model.addAttribute("exito", "¡Tu mensaje se ha enviado con éxito!");
        return "atencion_al_cliente";
    }

}
