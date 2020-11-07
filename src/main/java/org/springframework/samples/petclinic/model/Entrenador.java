package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
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

@Entity
@Table(name="entrenadores",uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Entrenador extends BaseEntity{
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entrenador")
	private Set<Equipo> equipos;
	
	@Column(name="nombre", nullable = false)
	private String nombre;
	
	@Column(name="apellidos", nullable = false)
	private String apellidos;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name = "fechaNacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate fechaNacimiento;
	
	public Entrenador() {
		
	}

	public Entrenador(User user, String nombre, String apellidos, String email, String usuario, String contraseña,
			LocalDate fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
		return "Entrenador [user=" + user + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	
	
}
