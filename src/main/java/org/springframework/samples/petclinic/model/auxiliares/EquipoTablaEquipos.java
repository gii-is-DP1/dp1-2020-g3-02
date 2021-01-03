package org.springframework.samples.petclinic.model.auxiliares;

import org.springframework.samples.petclinic.enumerate.Sistema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoTablaEquipos {

	private Integer id;
	
	private Sistema sistemaJuego;
	
	private String categoria;
	
	private Integer numAmarillas;
	
	private Integer numRojas;
	
	private String liga;
	
	private String federacion;
	
}
