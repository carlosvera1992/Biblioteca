package org.av2.biblioteca.model.service;



import java.util.List;

import org.av2.biblioteca.model.dao.EditorialDAO;
import org.av2.biblioteca.model.dao.LibroDAO;
import org.av2.biblioteca.model.entity.Editorial;
import org.av2.biblioteca.model.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class LibroServiceImpl implements LibroService {

	
	@Autowired
	private LibroDAO libroDAO;
	
	@Autowired
	private EditorialDAO editorialDAO;
	
		
	@Override
	@Transactional(readOnly = true)
	public Page<Libro> buscarTodosLibros(Pageable pageable) {
		return libroDAO.findAll(pageable);

	}
	
	@Override
	@Transactional
	public void actualizarLibro(Libro libro) {
		libroDAO.save(libro);
	}
	
	@Override
	@Transactional
	public void eliminarLibroPorId(Long id) {
		libroDAO.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Libro buscarLibroPorId(Long id) {
		return libroDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<Editorial> buscarTodosLosEditoriales() {
		return editorialDAO.findAll();
	}

	
	

	
	
}

