package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.converter.enumerate.TipoPruebaConverter;
import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PruebasSinJugador {

	private int id;
	
	private Double dato;
	
	private LocalDate fecha;
	
	private TipoPrueba tipoPrueba;
	
	
	
}
