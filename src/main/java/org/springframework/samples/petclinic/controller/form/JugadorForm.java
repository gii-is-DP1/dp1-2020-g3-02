package org.springframework.samples.petclinic.controller.form;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Jugador;

import lombok.Data;

@Data
public class JugadorForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Valid
	private Jugador jugador;
	

}
