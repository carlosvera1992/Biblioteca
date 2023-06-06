package org.av2.biblioteca.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.av2.biblioteca.model.entity.Libro;
import org.av2.biblioteca.model.service.LibroService;
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
@SessionAttributes("libro")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@GetMapping("/librolistar")
	public String libroListar(@RequestParam(value = "pag", defaultValue = "0") int pag, Model model) {
		org.springframework.data.domain.Pageable pagina = PageRequest.of(pag, 8);
		Page<Libro> libros = libroService.buscarTodosLibros(pagina);

		PageRender<Libro> pageRender = new PageRender<>("/biblioteca/librolistar", libros);

		model.addAttribute("titulo", "Listado de Libros");
		model.addAttribute("libros", libros);
		model.addAttribute("pageRender", pageRender);
		return "libro/listado_libros";
	}

	@GetMapping("/libronuevo")
	public String libroFormularioNuevo(Model model) {
		model.addAttribute("titulo", "Nuevo Libro");
		model.addAttribute("accion", "Crear");
		model.addAttribute("libro", new Libro());
		model.addAttribute("editoriales", libroService.buscarTodosLosEditoriales());
		return "libro/nuevo_libro";
	}

	@PostMapping("/libroagregar")
	public String libroAgregar(@Valid @ModelAttribute("libro") Libro libro, BindingResult result, Model model,
			@RequestParam("file") MultipartFile caratula, RedirectAttributes flash, SessionStatus status) {

		String accion = (libro.getId() == null) ? "Crear" : "Modificar";

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo Libro");
			model.addAttribute("accion", accion);
			model.addAttribute("editoriales", libroService.buscarTodosLosEditoriales());
			model.addAttribute("info", "CORREGIR: Tiene almenos un error en el formulario !!");
			return "libro/nuevo_libro";
		}

		if (!caratula.isEmpty()) {
			if (libro.getId() != null && libro.getId() > 0 && libro.getCaratula() != null
					&& libro.getCaratula().length() > 0) {

				Path rutaAbsoluta = Paths.get("uploads").resolve(libro.getCaratula()).toAbsolutePath();
				File archivo = rutaAbsoluta.toFile();

				if (archivo.exists() && archivo.canRead()) {
					archivo.delete();
				}
			}

			String nombreUnico = UUID.randomUUID().toString() + "_" + caratula.getOriginalFilename();

			Path rutaUploads = Paths.get("uploads").resolve(nombreUnico);
			Path rutaAbsoluta = rutaUploads.toAbsolutePath();

			try {
				Files.copy(caratula.getInputStream(), rutaAbsoluta);
				flash.addFlashAttribute("info", "Ha cargado Exitosamente la caratula [ " + nombreUnico + " ] ");
				libro.setCaratula(nombreUnico);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if (libro.getId() != null && caratula.isEmpty()) {
			libro.setCaratula(libroService.buscarLibroPorId(libro.getId()).getCaratula());
		}
		
		
		if (libro.getId() == null && caratula.isEmpty()) {
			libro.setCaratula("");
		}
		
		String msgFlash = (libro.getId() == null) ? "Libro Agregado con Éxito !" : "Libro Modificado con Éxito";

		libroService.actualizarLibro(libro);
		status.setComplete();
		flash.addFlashAttribute("success", msgFlash);
		return "redirect:/biblioteca/librolistar";

	}

	@GetMapping("/libroeliminar/{id}")
	public String libroEliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			libroService.eliminarLibroPorId(id);
			flash.addFlashAttribute("success", "Libro Eliminado Exitosamente !! ");
		}

		return "redirect:/biblioteca/librolistar";

	}

	@GetMapping("/libromodificar/{id}")
	public String libroModificar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Libro libro = null;
		if (id > 0) {
 			libro = libroService.buscarLibroPorId(id);
			if (libro == null) {
				flash.addFlashAttribute("error", " El Libro no existe en Bases de Datos de la Libreria");
				return "redirect:/biblioteca/librolistar";
			}
		} else {
			flash.addFlashAttribute("error", "Error en el Id del Libro, Contactar al SysAdmin");
			return "redirect:/biblioteca/librolistar";
		}
		model.addAttribute("accion", "Modificar");
		model.addAttribute("titulo", "Modificar Libro");
		model.addAttribute("libro", libro);
		return "libro/nuevo_libro";
	}

	@GetMapping("/libroconsultar/{id}")
	public String libroConsultar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Libro libro = libroService.buscarLibroPorId(id);
		if (libro == null) {
			flash.addFlashAttribute("error", "El Libro no existe en la Base de Datos de la Libreria");
			return "redirect:/biblioteca/librolistar";
		}
		model.addAttribute("titulo", "Consultando Libro: " + libro.getTitulo());
		model.addAttribute("libro", libro);

		return "libro/consultar_libro";
	}
	
	
	
	
	
	
	
	
	
	

	

}
