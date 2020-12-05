package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class JugadorWithEquipo {

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String dni;
	
	private String direccion;
	
	private String email;
	
	private String localidad;
	
	private LocalDate fechaNacimiento;
	
	private Integer altura;
	
	private Integer peso;
	
	private Posicion posicionPrincipal;
	
	private Posicion posicionSecundaria;
	
	private Estado estadoActual;
	
	private List<String> equipos;

	public JugadorWithEquipo(int id, String firstName, String lastName, String dni, String direccion, String email,
			String localidad, LocalDate fechaNacimiento, Integer altura, Integer peso, Posicion posicionPrincipal,
			Posicion posicionSecundaria, Estado estadoActual, List<String> equipos, User user) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.localidad = localidad;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
		this.peso = peso;
		this.posicionPrincipal = posicionPrincipal;
		this.posicionSecundaria = posicionSecundaria;
		this.estadoActual = estadoActual;
		this.equipos = equipos;
		this.user =user;
	}
	
	private User user;
	
	
	
}
