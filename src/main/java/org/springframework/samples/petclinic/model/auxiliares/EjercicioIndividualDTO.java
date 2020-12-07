package org.springframework.samples.petclinic.model.auxiliares;

import org.springframework.samples.petclinic.enumerate.TipoEjercicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EjercicioIndividualDTO {
	
	private TipoEjercicio tipo;
	
	private String nombre;
	
	private String descripcion;

}
