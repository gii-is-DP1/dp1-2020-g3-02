package org.springframework.samples.petclinic.model.auxiliares;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorPriv {

	private String categoria;
	private Boolean partidos;
	private Boolean entrenamientos;
	
}
