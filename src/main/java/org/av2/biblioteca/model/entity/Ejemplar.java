package org.av2.biblioteca.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="ejemplares")
public class Ejemplar implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "fechaIngreso")
	private Date fechaIngreso;
	
	@Column(name = "numeroEjemplar")
	private String numeroEjemplar;
	
	//Lo voy a utilizar para cuando hagan prestamos tenerlo como switch
	@Column(name = "activo")
	private Boolean activo;
	
	
	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;
	
	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL)
	private List<Prestamo> prestamos;

	
	
	
	
	public Ejemplar() {
		
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getEstado() {
		return estado;
	}





	public void setEstado(String estado) {
		this.estado = estado;
	}





	public Date getFechaIngreso() {
		return fechaIngreso;
	}





	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}





	public String getNumeroEjemplar() {
		return numeroEjemplar;
	}





	public void setNumeroEjemplar(String numeroEjemplar) {
		this.numeroEjemplar = numeroEjemplar;
	}





	public Boolean getActivo() {
		return activo;
	}





	public void setActivo(Boolean activo) {
		this.activo = activo;
	}





	public Libro getLibro() {
		return libro;
	}





	public void setLibro(Libro libro) {
		this.libro = libro;
	}





	public List<Prestamo> getPrestamos() {
		return prestamos;
	}





	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}





	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", estado=" + estado + ", fechaIngreso=" + fechaIngreso + ", numeroEjemplar="
				+ numeroEjemplar + ", activo=" + activo + ", libro=" + libro + ", prestamos=" + prestamos + "]";
	}

	
	
	
	

	
	
	
	
	
	
	
	
	
}
