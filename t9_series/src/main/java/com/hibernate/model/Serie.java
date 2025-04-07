package com.hibernate.model;

import jakarta.persistence.*;

@Entity
@Table(name="netflix")
public class Serie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idserie")
	private int idserie;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="temporadas")
	private int temporadas;
	
	@Column(name="capitulos")
	private int capitulos;
	

	public Serie(String nombre, int temporadas, int capitulos) {
		super();
		this.nombre = nombre;
		this.temporadas = temporadas;
		this.capitulos = capitulos;
	}


	public Serie() {
		super();
	}


	public int getIdserie() {
		return idserie;
	}


	public String getNombre() {
		return nombre;
	}


	public int getTemporadas() {
		return temporadas;
	}


	public int getCapitulos() {
		return capitulos;
	}


	public void setIdserie(int idserie) {
		this.idserie = idserie;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}


	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}
	
	
}
