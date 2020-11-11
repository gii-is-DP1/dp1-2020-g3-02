package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;

import lombok.Data;

@Data
@Entity
@Table(name="privilegios")
public class Privilegio extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;
	
	@Column(name= "tipo_privilegio", columnDefinition = "varchar(30) default 'partidos'")
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

	@Override
	public String toString() {
		return "Privilegio [jugador=" + jugador + ", equipo=" + equipo + ", tipoPrivilegio=" + tipoPrivilegio
				+ ", descripcion=" + descripcion + "]";
	}
	
}
