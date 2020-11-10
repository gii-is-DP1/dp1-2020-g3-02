package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estadistico")
	Set<EstadisticaPersonalEntrenamiento> estadisticasPersonalEntrenamiento;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estadistico")
	Set<EstadisticaPersonalPartido> estadisticasPersonalPartido;

	@Column(name= "email", nullable = false)
	@Email
	private String email;
	
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate fechaNacimiento;
	
	public Estadistico() {
		
	}
	
	public Estadistico(String email, LocalDate fechaNacimiento) {
		super();
	
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Override
	public String toString() {
		return "Estadistico [user=" + user + ", estadisticas_personales_entrenamiento="
				+ estadisticasPersonalEntrenamiento + ", estadisticas_personales_partido="
				+ estadisticasPersonalPartido + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
}
