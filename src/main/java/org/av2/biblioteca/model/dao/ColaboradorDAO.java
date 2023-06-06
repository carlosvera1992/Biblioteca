package org.av2.biblioteca.model.dao;

import org.av2.biblioteca.model.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorDAO extends  JpaRepository<Colaborador, Long> {
	

	@Query("select c from Colaborador c where c.nombre =?1")
	public Colaborador buscarColaboradorPorNombre(String nombre);
	
	
	public Colaborador findByNombre(String nombre);
}
