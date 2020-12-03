package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorAut {

	private Integer id;
	private String firstName;
	private String lastName;
	private List<TipoAutorizacion> autorizaciones;
	
}
