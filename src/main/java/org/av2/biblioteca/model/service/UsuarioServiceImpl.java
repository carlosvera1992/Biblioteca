package org.av2.biblioteca.model.service;

import org.av2.biblioteca.model.dao.UsuarioDAO;
import org.av2.biblioteca.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> buscarTodosLosUsuario(Pageable pageable){
		return usuarioDAO.findAll(pageable);
	}
	
	@Override
	@Transactional
	public void actualizarUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
	}
	
	@Override
	@Transactional
	public void eliminarUsuarioPorId(Long id) {
		usuarioDAO.deleteById(id);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	
	

}
