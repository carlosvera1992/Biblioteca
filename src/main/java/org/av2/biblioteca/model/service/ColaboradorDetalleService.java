package org.av2.biblioteca.model.service;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.av2.biblioteca.model.dao.ColaboradorDAO;
import org.av2.biblioteca.model.entity.Colaborador;
import org.av2.biblioteca.model.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColaboradorDetalleService  implements UserDetailsService{

	
protected final Log logger = LogFactory.getLog(this.getClass());
	
	
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Colaborador colaborador = colaboradorDAO.buscarColaboradorPorNombre(username);
		
		
		if(colaborador == null) {
			logger.error(" *** Error de Autenticación, el Usuario '" + username + "' NO Existe");
			throw new UsernameNotFoundException(" *** Error de Autenticación, el Usuario '" + username + "' NO Existe");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for(Rol rol : colaborador.getRoles()) {
			logger.info("Rol: "+rol.getNombre());
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		if (roles.isEmpty()) {
			logger.warn(" -- El Usuario '" + colaborador.getNombre()   + "' No tiene Rol Asignado");
			throw new UsernameNotFoundException(" -- El Usuario '" + colaborador.getNombre()   + "' No tiene Rol Asignado");
		}
		
		
		return new User(colaborador.getNombre(), colaborador.getClave(), colaborador.isActivo(), true, true, true, roles);
	}
	
	
}
