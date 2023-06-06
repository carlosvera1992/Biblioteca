package org.av2.biblioteca.model.service;

import java.util.List;

import org.av2.biblioteca.model.entity.Libro;


public interface PrestamoService {

	
	public List<Libro> buscarTodosLibrosPorTitulo(String term);

	
}
