package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.TipoSistema;

import lombok.Data;




@Data
@Entity
@Table(name = "sistema_juego")

public class SistemaJuego extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoSistema tipo;


	@Column(name = "minutoAplicacion", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int minutoAplicacion;

	public SistemaJuego(TipoSistema tipo,  int minutoAplicacion) {
		super();
		this.tipo = tipo;
		this.minutoAplicacion = minutoAplicacion;
	}

	public SistemaJuego() {}

}
