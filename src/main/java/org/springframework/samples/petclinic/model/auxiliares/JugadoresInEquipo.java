package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JugadoresInEquipo {

private int id;
	
	private String firstName;
	
	private String lastName;
	
	private Posicion posicionPrincipal;
	
	private Posicion posicionSecundaria;
	
	private Integer altura;
	
	private Integer peso;
	
	private Integer pesoIdeal;
	
	private double imc;
	
	public JugadoresInEquipo(int id, String firstName, String lastName, Integer altura, Integer peso, Integer pesoIdeal, Posicion posicionPrincipal,
			Posicion posicionSecundaria, double imc, User user) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.altura = altura;
		this.peso = peso;
		this.posicionPrincipal = posicionPrincipal;
		this.posicionSecundaria = posicionSecundaria;
		this.pesoIdeal = pesoIdeal;
		this.imc = imc;
		
		this.user =user;
	}

	private User user;
	
}
