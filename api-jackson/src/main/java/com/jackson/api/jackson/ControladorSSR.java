package com.jackson.api.jackson;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller public class ControladorSSR {
    @GetMapping("/")
    public String mostrarMenu(Model model){
        return "index";
    }

}
