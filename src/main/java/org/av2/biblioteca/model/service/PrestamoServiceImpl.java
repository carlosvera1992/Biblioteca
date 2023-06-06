package org.av2.biblioteca.model.service;

import java.util.List;

import org.av2.biblioteca.model.dao.LibroDAO;
import org.av2.biblioteca.model.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	private LibroDAO libroDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Libro> buscarTodosLibrosPorTitulo(String term) {
		return libroDAO.buscarLibrosConEjemplares(term);
	}

}
