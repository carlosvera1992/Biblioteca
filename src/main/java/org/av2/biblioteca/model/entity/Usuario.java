package org.av2.biblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name ="usuarios")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name = "identificacion")
	private String identificacion;
	
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;
		
	@NotEmpty
	@Column(name = "apellidos")
	private String apellidos;
	
	@NotBlank
	@Email
	@Column(name = "correo")
	private String correo;
	
	
	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private LocalDate fechaIngreso;
	
	
	@Column(name = "foto")
	private String foto;
	
	@NotEmpty
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "activo")
	private Boolean activo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Prestamo> prestamos;
	
	

	public Usuario() {
		prestamos = new ArrayList<>();
	}
	
	
	public Usuario(Long id, @NotEmpty String identificacion, @NotEmpty String nombre, @NotEmpty String apellidos,
			@NotBlank @Email String correo, LocalDate fechaIngreso, String foto, @NotEmpty String telefono,
			Boolean activo, List<Prestamo> prestamos) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaIngreso = fechaIngreso;
		this.foto = foto;
		this.telefono = telefono;
		this.activo = activo;
		prestamos = new ArrayList<>();
	}







	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

		public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	 

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", identificacion=" + identificacion + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", correo=" + correo + ", fechaIngreso=" + fechaIngreso + ", foto=" + foto + ", telefono="
				+ telefono + ", activo=" + activo + ", prestamos=" + prestamos + "]";
	}
	
	
	
	
	
	
}