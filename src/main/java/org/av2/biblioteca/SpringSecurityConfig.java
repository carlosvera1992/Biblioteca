package org.av2.biblioteca;

import org.av2.biblioteca.auth.LogginAuthSuccessHandler;
import org.av2.biblioteca.model.service.ColaboradorDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Autowired
	LogginAuthSuccessHandler successHandler;
	
	@Autowired
	ColaboradorDetalleService colaboradorDetalleService;
	
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void UserDetailsService(AuthenticationManagerBuilder  build) throws Exception {
		build.userDetailsService(colaboradorDetalleService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  //http.authorizeHttpRequests()
		http.authorizeHttpRequests()
		.requestMatchers("/", "/css/**", "/js/**", "/img/**", "/biblioteca/usuariolistar", "/biblioteca/librolistar").permitAll()
		.requestMatchers("/biblioteca/usuarioconsultar/**", "/biblioteca/libroconsultar/**").hasAnyRole("USER")
		.requestMatchers("/biblioteca/usuarionuevo/**", "/biblioteca/usuarioeliminar/**", "/biblioteca/usuariomodificar/**").hasAnyRole("ADMIN")
		.requestMatchers("/biblioteca/libronuevo/**", "/biblioteca/libroeliminar/**", "/biblioteca/libromodificar/**").hasAnyRole("ADMIN")
		.requestMatchers("/biblioteca/prestamonuevo/**", "/biblioteca/prestamonuevo/**").hasAnyRole("ADMIN")
		.requestMatchers("/mantenimiento/**").hasAnyRole("MMTO")
		.anyRequest().authenticated()
		.and()
		.formLogin().successHandler(successHandler)
		.loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
		
		
		return http.build();
	}


	
}
