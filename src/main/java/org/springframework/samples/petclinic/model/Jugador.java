package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;

@Entity
@Table(name="jugadores", uniqueConstraints = @UniqueConstraint(columnNames = { "dni","email" }))
public class Jugador extends Person{
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@ManyToMany
	@JoinTable(name = "juegaPartido", joinColumns = @JoinColumn(name = "jugador_id"), 
	  inverseJoinColumns = @JoinColumn(name = "partido_id"))
	Set<Partido> partidos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<PruebaCondicionFisica> pruebas_condicion_fisica;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<EstadisticaPersonalEntrenamiento> estadisticas_personales_entrenamientos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<EstadisticaPersonalPartido> estadisticas_personales_partidos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<Personales> personales;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<Capitan> capitan;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<RealizaEjercicio> realiza_ejercicios;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
	private Set<Privilegio> privilegios;

	
	@Column(name = "dni", nullable = false, length = 9)
	private String dni;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@Column(name = "email", nullable = false)
	@Email
	private String email;
	
	@Column(name = "localidad", nullable = false)
	private String localidad;
	
	@Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "date default SYSDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaNacimiento;
	
	@Column(name = "altura", nullable = false)
	@Max(350)
	@Min(0)
	private int altura;
	
	@Column(name = "peso", nullable = false)
	@Min(0)
	private int peso;
	
	@Column(name = "peso_ideal", nullable = false)
	private int pesoIdeal;
	
	@Column(name = "imc", nullable = false)
	private double imc;
	
	@Column(name = "numero_camiseta")
	@Max(99)
	@Min(1)
	private Integer numeroCamiseta;
	
	@Column(name = "posicion_principal", columnDefinition = "varchar(255) default 'PUNTA' check(posicion_principal in ('PUNTA','OPUESTO','COLOCADOR','CENTRAL','LIBERO'))")
	@Enumerated(value = EnumType.STRING)
	private Posicion posicionPrincipal;
	
	@Column(name = "posicion_secundaria", columnDefinition = "varchar(255) default 'PUNTA' check(posicion_secundaria in ('PUNTA','OPUESTO','COLOCADOR','CENTRAL','LIBERO'))")
	@Enumerated(value = EnumType.STRING)
	private Posicion posicionSecundaria;
	
	@Column(name = "estado_actual", columnDefinition = "varchar(255) default 'EN_FORMA'check (estado_actual in ('EN_FORMA', 'LESIONADO'))")
	@Enumerated(value = EnumType.STRING)
	private Estado estadoActual;
	
	@Column(name = "saques_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saquesAcertados;
	
	@Column(name = "saques_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saquesTotales;
	
	@Column(name = "porcentaje_saques", scale = 2, columnDefinition = "double default 0")
	private double porcentajeSaques;
	
	@Column(name = "recepciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepcionesAcertadas;
	
	@Column(name = "recepciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepcionesTotales;
	
	@Column(name = "porcentaje_recepciones", scale = 2, columnDefinition = "double default 0")
	private double porcentajeRecepciones;
	
	@Column(name = "colocaciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocacionesAcertadas;
	
	@Column(name = "colocaciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocacionesTotales;
	
	@Column(name = "porcentaje_colocaciones", scale = 2, columnDefinition = "double default 0")
	private double porcentajeColocaciones;
	
	@Column(name = "defensas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensasAcertadas;
	
	@Column(name = "defensas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensasTotales;
	
	@Column(name = "porcentaje_defensas", scale = 2, columnDefinition = "double default 0")
	private double porcentajeDefensas;
	
	@Column(name = "bloqueos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueosAcertados;
	
	@Column(name = "bloqueos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueosTotales;
	
	@Column(name = "porcentaje_bloqueos", scale = 2, columnDefinition = "double default 0")
	private double porcentajeBloqueos;
	
	@Column(name = "remates_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int rematesAcertados;
	
	@Column(name = "remates_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int rematesTotales;
	
	@Column(name = "porcentaje_remates", scale = 2, columnDefinition = "double default 0")
	private double porcentajeRemates;
	
	@Column(name = "fintas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintasAcertadas;
	
	@Column(name = "fintas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintasTotales;
	
	@Column(name = "porcentaje_fintas", scale = 2, columnDefinition = "double default 0")
	private double porcentajeFintas;
	
	@Column(name = "num_ataques_rapidos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAtaquesRapidosAcertados;
	
	@Column(name = "num_ataques_rapidos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAtaquesRapidosTotales;
	
	@Column(name = "porcentaje_ataques_rapidos", scale = 2, columnDefinition = "double default 0")
	private double porcentajeAtaquesRapidos;
	
	@Column(name = "num_faltas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numFaltasTotales;
	
	@Column(name = "num_amarillas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAmarillas;
	
	@Column(name = "num_rojas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numRojas;
	
	public Jugador() {
	}
	
	public Jugador(String dni, String direccion, String email, String localidad, LocalDate fecha_nacimiento, int altura,
			int peso, Posicion posicion_principal, Posicion posicion_secundaria) {
		super();
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.localidad = localidad;
		this.fechaNacimiento = fecha_nacimiento;
		this.altura = altura;
		this.peso = peso;
		this.posicionPrincipal = posicion_principal;
		this.posicionSecundaria = posicion_secundaria;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<PruebaCondicionFisica> getPruebas_condicion_fisica() {
		return pruebas_condicion_fisica;
	}

	public void setPruebas_condicion_fisica(Set<PruebaCondicionFisica> pruebas_condicion_fisica) {
		this.pruebas_condicion_fisica = pruebas_condicion_fisica;
	}

	public Set<Personales> getPersonales() {
		return personales;
	}

	public void setPersonales(Set<Personales> personales) {
		this.personales = personales;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getPeso_ideal() {
		return pesoIdeal;
	}

	public void setPeso_ideal(int peso_ideal) {
		this.pesoIdeal = peso_ideal;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public int getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public void setNumeroCamiseta(int numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}

	public Posicion getPosicionPrincipal() {
		return posicionPrincipal;
	}

	public void setPosicionPrincipal(Posicion posicionPrincipal) {
		this.posicionPrincipal = posicionPrincipal;
	}

	public Posicion getPosicionSecundaria() {
		return posicionSecundaria;
	}

	public void setPosicionSecundaria(Posicion posicionSecundaria) {
		this.posicionSecundaria = posicionSecundaria;
	}

	public Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Estado estadoActual) {
		this.estadoActual = estadoActual;
	}

	public int getSaquesAcertados() {
		return saquesAcertados;
	}

	public void setSaquesAcertados(int saquesAcertados) {
		this.saquesAcertados = saquesAcertados;
	}

	public int getSaquesTotales() {
		return saquesTotales;
	}

	public void setSaquesTotales(int saquesTotales) {
		this.saquesTotales = saquesTotales;
	}

	public double getPorcentajeSaques() {
		return porcentajeSaques;
	}

	public void setPorcentajeSaques(double porcentajeSaques) {
		this.porcentajeSaques = porcentajeSaques;
	}

	public int getRecepcionesAcertadas() {
		return recepcionesAcertadas;
	}

	public void setRecepcionesAcertadas(int recepcionesAcertadas) {
		this.recepcionesAcertadas = recepcionesAcertadas;
	}

	public int getRecepcionesTotales() {
		return recepcionesTotales;
	}

	public void setRecepcionesTotales(int recepcionesTotales) {
		this.recepcionesTotales = recepcionesTotales;
	}

	public double getPorcentajeRecepciones() {
		return porcentajeRecepciones;
	}

	public void setPorcentajeRecepciones(double porcentajeRecepciones) {
		this.porcentajeRecepciones = porcentajeRecepciones;
	}

	public int getColocacionesAcertadas() {
		return colocacionesAcertadas;
	}

	public void setColocacionesAcertadas(int colocacionesAcertadas) {
		this.colocacionesAcertadas = colocacionesAcertadas;
	}

	public int getColocacionesTotales() {
		return colocacionesTotales;
	}

	public void setColocacionesTotales(int colocacionesTotales) {
		this.colocacionesTotales = colocacionesTotales;
	}

	public double getPorcentajeColocaciones() {
		return porcentajeColocaciones;
	}

	public void setPorcentajeColocaciones(double porcentajeColocaciones) {
		this.porcentajeColocaciones = porcentajeColocaciones;
	}

	public int getDefensasAcertadas() {
		return defensasAcertadas;
	}

	public void setDefensasAcertadas(int defensasAcertadas) {
		this.defensasAcertadas = defensasAcertadas;
	}

	public int getDefensasTotales() {
		return defensasTotales;
	}

	public void setDefensasTotales(int defensasTotales) {
		this.defensasTotales = defensasTotales;
	}

	public double getPorcentajeDefensas() {
		return porcentajeDefensas;
	}

	public void setPorcentajeDefensas(double porcentajeDefensas) {
		this.porcentajeDefensas = porcentajeDefensas;
	}

	public int getBloqueosAcertados() {
		return bloqueosAcertados;
	}

	public void setBloqueosAcertados(int bloqueosAcertados) {
		this.bloqueosAcertados = bloqueosAcertados;
	}

	public int getBloqueosTotales() {
		return bloqueosTotales;
	}

	public void setBloqueosTotales(int bloqueosTotales) {
		this.bloqueosTotales = bloqueosTotales;
	}

	public double getPorcentajeBloqueos() {
		return porcentajeBloqueos;
	}

	public void setPorcentajeBloqueos(double porcentajeBloqueos) {
		this.porcentajeBloqueos = porcentajeBloqueos;
	}

	public int getRematesAcertados() {
		return rematesAcertados;
	}

	public void setRematesAcertados(int rematesAcertados) {
		this.rematesAcertados = rematesAcertados;
	}

	public int getRematesTotales() {
		return rematesTotales;
	}

	public void setRematesTotales(int rematesTotales) {
		this.rematesTotales = rematesTotales;
	}

	public double getPorcentajeRemates() {
		return porcentajeRemates;
	}

	public void setPorcentajeRemates(double porcentajeRemates) {
		this.porcentajeRemates = porcentajeRemates;
	}

	public int getFintasAcertadas() {
		return fintasAcertadas;
	}

	public void setFintasAcertadas(int fintasAcertadas) {
		this.fintasAcertadas = fintasAcertadas;
	}

	public int getFintasTotales() {
		return fintasTotales;
	}

	public void setFintasTotales(int fintasTotales) {
		this.fintasTotales = fintasTotales;
	}

	public double getPorcentajeFintas() {
		return porcentajeFintas;
	}

	public void setPorcentajeFintas(double porcentajeFintas) {
		this.porcentajeFintas = porcentajeFintas;
	}

	public int getNumAtaquesRapidosAcertados() {
		return numAtaquesRapidosAcertados;
	}

	public void setNumAtaquesRapidosAcertados(int numAtaquesRapidosAcertados) {
		this.numAtaquesRapidosAcertados = numAtaquesRapidosAcertados;
	}

	public int getNumAtaquesRapidosTotales() {
		return numAtaquesRapidosTotales;
	}

	public void setNumAtaquesRapidosTotales(int numAtaquesRapidosTotales) {
		this.numAtaquesRapidosTotales = numAtaquesRapidosTotales;
	}

	public double getPorcentajeAtaquesRapidos() {
		return porcentajeAtaquesRapidos;
	}

	public void setPorcentajeAtaquesRapidos(double porcentajeAtaquesRapidos) {
		this.porcentajeAtaquesRapidos = porcentajeAtaquesRapidos;
	}

	public int getNumFaltasTotales() {
		return numFaltasTotales;
	}

	public void setNumFaltasTotales(int numFaltasTotales) {
		this.numFaltasTotales = numFaltasTotales;
	}

	public int getNumAmarillas() {
		return numAmarillas;
	}

	public void setNumAmarillas(int numAmarillas) {
		this.numAmarillas = numAmarillas;
	}

	public int getNumRojas() {
		return numRojas;
	}

	public void setNumRojas(int numRojas) {
		this.numRojas = numRojas;
	}
	
	

	public Set<EstadisticaPersonalEntrenamiento> getEstadisticas_personales_entrenamientos() {
		return estadisticas_personales_entrenamientos;
	}

	public void setEstadisticas_personales_entrenamientos(
			Set<EstadisticaPersonalEntrenamiento> estadisticas_personales_entrenamientos) {
		this.estadisticas_personales_entrenamientos = estadisticas_personales_entrenamientos;
	}

	public Set<EstadisticaPersonalPartido> getEstadisticas_personales_partidos() {
		return estadisticas_personales_partidos;
	}

	public void setEstadisticas_personales_partidos(Set<EstadisticaPersonalPartido> estadisticas_personales_partidos) {
		this.estadisticas_personales_partidos = estadisticas_personales_partidos;
	}

	@Override
	public String toString() {
		return "Jugador [user=" + user + ", dni=" + dni
				+ ", direccion=" + direccion + ", email=" + email + ", localidad=" + localidad + ", fechaNacimiento="
				+ fechaNacimiento + ", altura=" + altura + ", peso=" + peso + ", pesoIdeal=" + pesoIdeal + ", imc="
				+ imc + ", numeroCamiseta=" + numeroCamiseta + ", posicionPrincipal=" + posicionPrincipal
				+ ", posicionSecundaria=" + posicionSecundaria + ", estadoActual=" + estadoActual + ", saquesAcertados="
				+ saquesAcertados + ", saquesTotales=" + saquesTotales + ", porcentajeSaques=" + porcentajeSaques
				+ ", recepcionesAcertadas=" + recepcionesAcertadas + ", recepcionesTotales=" + recepcionesTotales
				+ ", porcentajeRecepciones=" + porcentajeRecepciones + ", colocacionesAcertadas="
				+ colocacionesAcertadas + ", colocacionesTotales=" + colocacionesTotales + ", porcentajeColocaciones="
				+ porcentajeColocaciones + ", defensasAcertadas=" + defensasAcertadas + ", defensasTotales="
				+ defensasTotales + ", porcentajeDefensas=" + porcentajeDefensas + ", bloqueosAcertados="
				+ bloqueosAcertados + ", bloqueosTotales=" + bloqueosTotales + ", porcentajeBloqueos="
				+ porcentajeBloqueos + ", rematesAcertados=" + rematesAcertados + ", rematesTotales=" + rematesTotales
				+ ", porcentajeRemates=" + porcentajeRemates + ", fintasAcertadas=" + fintasAcertadas
				+ ", fintasTotales=" + fintasTotales + ", porcentajeFintas=" + porcentajeFintas
				+ ", numAtaquesRapidosAcertados=" + numAtaquesRapidosAcertados + ", numAtaquesRapidosTotales="
				+ numAtaquesRapidosTotales + ", porcentajeAtaquesRapidos=" + porcentajeAtaquesRapidos
				+ ", numFaltasTotales=" + numFaltasTotales + ", numAmarillas=" + numAmarillas + ", numRojas=" + numRojas
				+ "]";
	}
	
}
