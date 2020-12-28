package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false,doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sistema_juego")
public class SistemaJuego extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;

	@Column(name = "sistema")
	@Enumerated(value = EnumType.STRING)
	private Sistema sistema;


	@Column(name = "minutoAplicacion", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int minutoAplicacion;

}
