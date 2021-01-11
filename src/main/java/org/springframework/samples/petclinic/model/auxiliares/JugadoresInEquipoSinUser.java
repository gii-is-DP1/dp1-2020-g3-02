package org.springframework.samples.petclinic.model.auxiliares;

import org.springframework.samples.petclinic.enumerate.Posicion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JugadoresInEquipoSinUser {

private int id;
	
	private String firstName;
	
	private String lastName;
	
	private Posicion posicionPrincipal;
	
	private Posicion posicionSecundaria;
	
	private Integer altura;
	
	private Integer peso;
	
	private Integer pesoIdeal;
	
	private double imc;
	
	private String email;
	
	private Integer numCamiseta;
	
	public JugadoresInEquipoSinUser(int id, String firstName, String lastName, Integer altura, Integer peso, Integer pesoIdeal, Posicion posicionPrincipal,
			Posicion posicionSecundaria, double imc, String email, int numCamiseta) {
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
		this.email = email;
		this.numCamiseta = numCamiseta;
	}
	
}

