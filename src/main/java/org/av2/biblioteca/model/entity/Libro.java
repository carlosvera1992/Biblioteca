package org.av2.biblioteca.model.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "libros")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "titulo")
	private String titulo;

	@NotEmpty
	@Column(name = "edicion")
	private String edicion;

	@NotEmpty
	@Pattern(regexp = "\\d{4}")
	@Column(name = "anho")
	private String anho;

	@NotEmpty
	@Pattern(regexp = "^\\d{10,13}$")
	@Column(name = "isbn")
	private String isbn;

	@Column(name = "caratula")
	private String caratula;

	@ManyToOne
	@JoinColumn(name = "editorial_id")
	@JsonIgnore
	private Editorial editorial;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LibroAutor> libroAutores;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
	private List<Ejemplar> ejemplares;

	public Libro() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getAnho() {
		return anho;
	}

	public void setAnho(String anho) {
		this.anho = anho;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public List<LibroAutor> getLibroAutores() {
		return libroAutores;
	}

	public void setLibroAutores(List<LibroAutor> libroAutores) {
		this.libroAutores = libroAutores;
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", edicion=" + edicion + ", anho=" + anho + ", isbn=" + isbn
				+ ", caratula=" + caratula + ", editorial=" + editorial + ", libroAutores=" + libroAutores + "]";
	}

}
