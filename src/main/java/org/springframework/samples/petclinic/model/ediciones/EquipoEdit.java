package org.springframework.samples.petclinic.model.ediciones;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.Sistema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoEdit {
	
	private Integer id;
	
	private String categoria;
	
	private Sistema sistemaJuego;
	
	private String liga;
	
	
}
