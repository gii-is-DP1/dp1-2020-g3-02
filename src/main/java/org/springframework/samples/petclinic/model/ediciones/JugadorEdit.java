package org.springframework.samples.petclinic.model.ediciones;

import java.time.LocalDate;

import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorEdit {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String dni;
	
	private String direccion;
	
	private String email;
	
	private String localidad;
	
	private LocalDate fechaNacimiento;
	
	private int altura;
	
	private int peso;
	
	private Posicion posicionPrincipal;
	
	private Posicion posicionSecundaria;
	
	private Estado estadoActual;


}
