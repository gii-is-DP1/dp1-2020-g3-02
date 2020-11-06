package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Actitud;

@Entity
@Table(name = "capitanes")
public class Capitan extends Jugador {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "capitan")
	private Set<Equipo> equipos;

	@Column(name = "ntiemposmuertos", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int ntiemposmuertos;

	@Column(name = "actitud", columnDefinition = "varchar(255) check(actitud in ('POSITIVA,NEGATIVA'))")
	@Enumerated(value = EnumType.STRING)
	private Actitud actitud;

	public Capitan(int ntiemposmuertos, Actitud actitud) {
		super();
		this.ntiemposmuertos = ntiemposmuertos;
		this.actitud = actitud;
	}
	public Capitan() {}
	public int getNtiemposmuertos() {
		return ntiemposmuertos;
	}
	public void setNtiemposmuertos(int ntiemposmuertos) {
		this.ntiemposmuertos = ntiemposmuertos;
	}
	public Actitud getActitud() {
		return actitud;
	}
	public void setActitud(Actitud actitud) {
		this.actitud = actitud;
	}

}
