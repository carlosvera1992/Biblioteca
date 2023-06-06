package org.av2.biblioteca.model.dao;

import org.av2.biblioteca.model.entity.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarDAO  extends JpaRepository<Ejemplar, Long>{

}
