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
import org.springframework.samples.petclinic.model.padres.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="estadisticos", uniqueConstraints = @UniqueConstraint(columnNames = { "email", }))
public class Estadistico extends Person{
	
	@OneToOne()
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@Column(name= "email", nullable = false)
	@Email
	private String email;
	
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaNacimiento;

	public Estadistico(@Email String email, LocalDate fechaNacimiento) {
		super();
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
