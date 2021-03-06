package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "capitanes")
public class Capitan extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;

	@Column(name = "ntiemposmuertos", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private Integer ntiemposmuertos;

	@Column(name = "actitud")
	@Enumerated(value = EnumType.STRING)
	private Actitud actitud;

	public Capitan(int ntiemposmuertos, Actitud actitud) {
		super();
		this.ntiemposmuertos = ntiemposmuertos;
		this.actitud = actitud;
	}

	
}
