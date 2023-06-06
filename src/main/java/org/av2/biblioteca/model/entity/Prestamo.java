package org.av2.biblioteca.model.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="prestamos")
public class Prestamo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "fechaDevolucion")
	private Date fechaDevolucion;
	
	@Column(name = "fechaVenta")
	private Date fechaVenta;
	
	@Column(name = "observacion")
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ejemplar_id")
	private Ejemplar ejemplar;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	
	
	public Prestamo() {
		
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



	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}



	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}



	public Date getFechaVenta() {
		return fechaVenta;
	}



	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	public Ejemplar getEjemplar() {
		return ejemplar;
	}



	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", estado=" + estado + ", fechaDevolucion=" + fechaDevolucion + ", fechaVenta="
				+ fechaVenta + ", observacion=" + observacion + ", ejemplar=" + ejemplar + ", usuario=" + usuario + "]";
	}


	
	
	
	
}
