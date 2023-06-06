package org.av2.biblioteca.model.service;


import org.av2.biblioteca.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

	
	public Page<Usuario> buscarTodosLosUsuario(Pageable pageable);
	public void actualizarUsuario(Usuario usuario);
	public void eliminarUsuarioPorId(Long id);
	public Usuario buscarUsuarioPorId(Long id);
}
