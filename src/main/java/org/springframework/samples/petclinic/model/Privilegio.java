package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;

@Entity
@Table(name="privilegio")
public class Privilegio extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@Column(name= "tipo_privilegio", columnDefinition = "varchar(30) default 'partidos' check (tipo_privilegio in ('partidos', 'entrenamientos'))")
	@Enumerated(value = EnumType.STRING)
	private TipoPrivilegio tipoPrivilegio;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	public Privilegio() {
		
	}
	
	public Privilegio(TipoPrivilegio tipo_privilegio, String descripcion) {
		super();
		this.tipoPrivilegio = tipo_privilegio;
		this.descripcion = descripcion;
	}
	
	public TipoPrivilegio getTipo_Privilegio() {
		return tipoPrivilegio;
	}
	
	public void setTipo_Privilegio(TipoPrivilegio tipo_privilegio) {
		this.tipoPrivilegio = tipo_privilegio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
