package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;

import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorDTO {
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer numCamiseta;
	
	private String dni;
	
	private String direccion;
	
	private String email;
	
	private String localidad;
	
	private LocalDate fechaNacimiento;
	
	private Integer altura;
	
	private Integer peso;
	
	private int pesoIdeal;
	
	private double imc;
	
	private Posicion posicionPrincipal;
	
	private Posicion posicionSecundaria;
	
	private Estado estadoActual;
	
	private int saquesAcertados;
	
	private int saquesTotales;
	
	private double porcentajeSaques;
	
	private int recepcionesAcertadas;
	
	private int recepcionesTotales;
	
	private double porcentajeRecepciones;
	
	private int colocacionesAcertadas;
	
	private int colocacionesTotales;
	
	private double porcentajeColocaciones;
	
	private int defensasAcertadas;
	
	private int defensasTotales;
	
	private double porcentajeDefensas;
	
	private int bloqueosAcertados;
	
	private int bloqueosTotales;
	
	private double porcentajeBloqueos;
	
	private int rematesAcertados;
	
	private int rematesTotales;
	
	private double porcentajeRemates;
	
	private int fintasAcertadas;
	
	private int fintasTotales;
	
	private double porcentajeFintas;
	
	private int numAtaquesRapidosAcertados;
	
	private int numAtaquesRapidosTotales;
	
	private double porcentajeAtaquesRapidos;
	
	private int numFaltasTotales;
	
	private int numAmarillas;
	
	private int numRojas;

	public JugadorDTO(Integer id, String firstName, String lastName, Integer numCamiseta, String dni, String direccion,
			String email, String localidad, LocalDate fechaNacimiento, Integer altura, Integer peso, int pesoIdeal,
			double imc, Posicion posicionPrincipal, Posicion posicionSecundaria, Estado estadoActual) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numCamiseta = numCamiseta;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.localidad = localidad;
		this.fechaNacimiento = fechaNacimiento;
		this.altura = altura;
		this.peso = peso;
		this.pesoIdeal = pesoIdeal;
		this.imc = imc;
		this.posicionPrincipal = posicionPrincipal;
		this.posicionSecundaria = posicionSecundaria;
		this.estadoActual = estadoActual;
	}
	
	
}
