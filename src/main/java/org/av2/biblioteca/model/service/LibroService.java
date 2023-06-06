package org.av2.biblioteca.model.service;





import java.util.List;

import org.av2.biblioteca.model.entity.Editorial;
import org.av2.biblioteca.model.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroService {
	
		
	public Page<Libro> buscarTodosLibros(Pageable pageable);
	public void actualizarLibro(Libro libro);
	public void eliminarLibroPorId(Long id);
	public Libro buscarLibroPorId(Long id);
	

	public List<Editorial> buscarTodosLosEditoriales();
	
}
