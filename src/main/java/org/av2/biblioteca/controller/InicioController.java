package org.av2.biblioteca.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
	
	
	
	@GetMapping("/")
	public String inicio() {

		return "inicio.html";
	}
	
	@GetMapping("/mantenimiento")
	public String mantenimiento(Model model, Authentication authentication) {
		model.addAttribute("mensaje", "Esta Recurso es solo para el Usuario " + authentication.getName());
		return "vista_mtto";
	}

}
