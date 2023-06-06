package org.av2.biblioteca.controller;

import java.util.List;

import org.av2.biblioteca.model.entity.Libro;
import org.av2.biblioteca.model.entity.Prestamo;
import org.av2.biblioteca.model.entity.Usuario;
import org.av2.biblioteca.model.service.PrestamoService;
import org.av2.biblioteca.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/biblioteca")
@SessionAttributes("prestamo")
public class PrestamoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PrestamoService prestamoService;
	
	@GetMapping("/prestamonuevo/{id}")
	public String prestamoNuevo(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flash ) {
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		if(usuario == null) {
			flash.addFlashAttribute("error", "El Usuario No existe en la base de datos !!");
			return "redirect:/biblioteca/usuariolistar";
		}
		
		Prestamo prestamo = new Prestamo();
		prestamo.setUsuario(usuario);
		
		model.addAttribute("titulo", "Nuevo prestamo");
		model.addAttribute("btn_accion", "Guarda Prestamo");
		model.addAttribute("prestamo", prestamo);
		
		
		return "prestamo/prestamo";
	}
	
	
	
	
	
	@GetMapping(value = "/cargarlibros/{term}", produces = "application/json")
	public @ResponseBody List<Libro> buscarLibrosTitulo(@PathVariable(name = "term") String term){
		return prestamoService.buscarTodosLibrosPorTitulo(term);
	}

}
