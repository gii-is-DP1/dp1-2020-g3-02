package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="estadisticos", uniqueConstraints = @UniqueConstraint(columnNames = { "email", }))
public class Estadistico extends Person{
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	//@OneToMany
	//@JoinTable(name= "rellena", joinColumns = @JoinColumn(name = "estadistico_id"), inverseJoinColumns = @JoinColumn(name = "estadistica_personal_entrenamiento_id"))
	//Set<EstadisticaPersonalEntrenamiento> estadisticas_personales_entrenamiento;
	
	//@OneToMany
		//@JoinTable(name= "rellena", joinColumns = @JoinColumn(name = "estadistico_id"), inverseJoinColumns = @JoinColumn(name = "estadistica_personal_partido_id"))
		//Set<EstadisticaPersonalPartido> estadisticas_personales_partido;

	@Column(name= "email", nullable = false)
	@Email
	private String email;
	
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate fechaNacimiento;
	
	
	
}
