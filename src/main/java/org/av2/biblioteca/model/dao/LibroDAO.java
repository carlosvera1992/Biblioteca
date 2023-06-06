package org.av2.biblioteca.model.dao;


import java.util.List;

import org.av2.biblioteca.model.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroDAO  extends JpaRepository<Libro, Long>{
//	@Query("select l from Libro l where l.titulo like %?1%")
//	public List<Libro> buscarPorDescripcion(String term);
	
	@Query("SELECT l FROM Libro l JOIN FETCH l.ejemplares e WHERE l.titulo LIKE %?1%")
    List<Libro> buscarLibrosConEjemplares(String term);
}

