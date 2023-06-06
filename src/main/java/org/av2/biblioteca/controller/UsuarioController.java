package org.av2.biblioteca.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.av2.biblioteca.model.entity.Usuario;
import org.av2.biblioteca.model.service.UsuarioService;
import org.av2.biblioteca.utils.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/biblioteca")
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	Usuario usuario = new Usuario();
	
	@GetMapping("/usuariolistar")
	public String usuarioListar(@RequestParam(value = "pag", defaultValue = "0") int pag, Model model) {
		org.springframework.data.domain.Pageable pagina = PageRequest.of(pag, 5);
		Page<Usuario> usuarios = usuarioService.buscarTodosLosUsuario(pagina);
		
		PageRender<Usuario> pageRender = new PageRender<>("/biblioteca/usuariolistar", usuarios);
		
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("pageRender", pageRender);
		return "usuario/listado_usuario";
		
	}
	
	
	@GetMapping("/usuarionuevo")
	public String usuarioNuevo(Model model) {
		model.addAttribute("titulo", "Nuevo Usuario");
		model.addAttribute("accion", "Crear");
		model.addAttribute("usuario", new Usuario());
		return "usuario/nuevo_usuario";
	}

	
	@PostMapping("/usuarioagregar")
	public String usuarioAgregar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		String accion = (usuario.getId() == null) ? "Crear" : "Modificar";

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo Usuario");
			model.addAttribute("accion", accion);
			model.addAttribute("warning", "CORREGIR: Tiene almenos un error en el fomrulario !!");
			return "usuario/nuevo_usuario";
		}

		if (!foto.isEmpty()) {
//			if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
//					&& usuario.getFoto().length() > 0) {
				
				String nombreUnico = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

				Path rutaUploads = Paths.get("uploads").resolve(nombreUnico);
				Path rutaAbsoluta = rutaUploads.toAbsolutePath();

			try {
				Files.copy(foto.getInputStream(), rutaAbsoluta);
				flash.addFlashAttribute("info", "Ha cargado Exitosamente la Foto [ " + nombreUnico + " ] ");
				usuario.setFoto(nombreUnico);

			} catch (Exception e) {
				e.printStackTrace();
			}

//		}
		
//		if (usuario.getId() != null && foto.isEmpty()) {
//			usuario.setFoto(usuarioService.buscarUsuarioPorId(usuario.getId()).getFoto());
//			
//		}
//		
//		
//		if (usuario.getId() == null && foto.isEmpty()) {
//			usuario.setFoto("");
//		}
//		
	}
		
		
		String msgFlash = (usuario.getId() == null) ? "Usuario Agregado con Éxito !" : "Usuario Modificado con Éxito";

		usuario.setFechaIngreso(LocalDate.now());
		usuarioService.actualizarUsuario(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", msgFlash);
		
		return "redirect:/biblioteca/usuariolistar";

	}
	
	
	@GetMapping("/usuarioeliminar/{id}")
	public String usuarioEliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			usuarioService.eliminarUsuarioPorId(id);
			flash.addFlashAttribute("success", "Usuario Eliminado Exitosamente !! ");
		}

		return "redirect:/biblioteca/usuariolistar";

	}
	
	
	@GetMapping("/usuariomodificar/{id}")
	public String usuarioModificar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Usuario usuario = null;
		if (id > 0) {
			usuario = usuarioService.buscarUsuarioPorId(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", " El Usuario no existe en Bases de Datos de la Libreria");
				return "redirect:/biblioteca/usuariolistar";
			}
		} else {
			flash.addFlashAttribute("error", "Error en el Id del Usuario, Contactar al SysAdmin");
			return "redirect:/biblioteca/usuariolistar";
		}
		model.addAttribute("accion", "Modificar");
		model.addAttribute("titulo", "Modificar Usuario");
		model.addAttribute("usuario", usuario);
		return "usuario/nuevo_usuario";
	}
	
	@GetMapping("/usuarioconsultar/{id}")
	public String usuarioConsultar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "El Usuario no existe en la Base de Datos de la Libreria");
			return "redirect:/biblioteca/usuariolistar";
		}
		model.addAttribute("titulo", "Consultando Usuario: " + usuario.getNombre() + " " + usuario.getApellidos());
		model.addAttribute("usuario", usuario);

		return "usuario/consultar_usuario";
	}
	

}

