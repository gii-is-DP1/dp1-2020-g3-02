package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.padres.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name="estadisticos", uniqueConstraints = @UniqueConstraint(columnNames = { "email", }))
public class Estadistico extends Person{
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@Column(name= "email", nullable = false)
	private String email;
	
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaNacimiento;

	public Estadistico() {
		
	}
	
	public Estadistico(String email, LocalDate fechaNacimiento) {
		super();
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Estadistico [user=" + user + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
	
	
}
