package org.av2.biblioteca.model.dao;

import org.av2.biblioteca.model.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialDAO extends JpaRepository<Editorial, Long> {

}
