package org.springframework.samples.petclinic.model.auxiliares;

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
	
	private String categoria;
	
	private Integer numAmarillas;
	
	private Integer numRojas;
	
	private String liga;
	
	private String federacion;
	
}
