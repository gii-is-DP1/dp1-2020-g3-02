package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.padres.Person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name="entrenadores",uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Entrenador extends Person{
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@OneToMany(mappedBy = "entrenador")
	private List<Equipo> equipos;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaNacimiento;
	
	public Entrenador() {
		
	}

	public Entrenador(String email,
			LocalDate fechaNacimiento) {
		super();

		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Entrenador [user=" + user + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
}
