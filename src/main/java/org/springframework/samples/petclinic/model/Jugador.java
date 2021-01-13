package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import org.springframework.samples.petclinic.converter.enumerate.PosicionConverter;
import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.padres.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="jugadores", uniqueConstraints = @UniqueConstraint(columnNames = { "dni","email","username" }))
public class Jugador extends Person{
	

	@OneToOne()
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@ManyToMany
	@JoinTable(name = "juegaPartido", joinColumns = @JoinColumn(name = "jugador_id"), 
	  inverseJoinColumns = @JoinColumn(name = "partido_id"))
	private List<Partido> partidos;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perteneceA", joinColumns = @JoinColumn(name = "jugador_id"), 
	  inverseJoinColumns = @JoinColumn(name = "equipo_id"))
	List<Equipo> equipos;

	@OneToMany( mappedBy = "jugador")
	private List<NumCamiseta> numCamisetas;
//	
//	@OneToMany( mappedBy = "jugador")
//	private Set<PruebaCondicionFisica> pruebas;
//	
//	@OneToMany( mappedBy = "jugador")
//	private Set<EstadisticaPersonalEntrenamiento> estadisticas_personales_entrenamientos;
	
//	@OneToMany( mappedBy = "jugador")
//	private Set<EstadisticaPersonalPartido> estadisticas_personales_partido;
	
//	@OneToMany( mappedBy = "jugador")
//	private Set<Personales> personales;
//	
//	@OneToMany( mappedBy = "jugador")
//	private Set<Capitan> capitan;
//	
//	@OneToMany( mappedBy = "jugador")
//	private Set<RealizaEjercicio> realiza_ejercicios;
//	
//	@OneToMany( mappedBy = "jugador")
//	private Set<Privilegio> privilegios;
//
	@OneToMany(mappedBy = "jugador")
	private List<Autorizacion> autorizacion; 
	
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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fechaNacimiento;
	
	@Column(name = "altura", nullable = false)
	@Max(350)
	@Min(1)
	private Integer altura;
	
	@Column(name = "peso", nullable = false)
	@Min(1)
	private Integer peso;
	
	@Column(name = "peso_ideal", nullable = false)
	private int pesoIdeal;
	
	@Column(name = "imc", nullable = false)
	private double imc;
	
	@Column(name = "posicion_principal", columnDefinition = "varchar(255) default 'PUNTA'")
	@Convert(converter = PosicionConverter.class)
	private Posicion posicionPrincipal;
	
	@Column(name = "posicion_secundaria", columnDefinition = "varchar(255) default 'PUNTA'")
	@Enumerated(value = EnumType.STRING)
	private Posicion posicionSecundaria;
	
	@Column(name = "estado_actual", columnDefinition = "varchar(255) default 'EN_FORMA'")
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

	@Override
	public String toString() {
		return "Jugador [user="+ user + ", dni=" + dni
				+ ", direccion=" + direccion + ", email=" + email + ", localidad=" + localidad + ", fechaNacimiento="
				+ fechaNacimiento + ", altura=" + altura + ", peso=" + peso + ", pesoIdeal=" + pesoIdeal + ", imc="
				+ imc + ", posicionPrincipal=" + posicionPrincipal
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
