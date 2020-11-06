package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Sistema;

@Entity
@Table(name = "entrenamientos")
public class Entrenamiento extends BaseEntity{
	
	@ManyToMany
	@JoinTable(
	  name = "realizaEntrenamiento", 
	  joinColumns = @JoinColumn(name = "entrenamiento_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
		
	@ManyToOne
	@JoinColumn(name="equipo_id", nullable = false)
	private Equipo equipo;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "hora", nullable = false, length = 5)
	private String hora;
	
	@Column(name = "sistema_juego", columnDefinition = "varchar(255) default 'CINCO_UNO' NOT NULL check (sistema_juego in ('COLOCADOR_GENERAL','CUATRO_DOS', 'CINCO_UNO', 'SEIS_DOS'))")
	@Enumerated(value = EnumType.STRING)
	private Sistema sistemaJuego;
	
	@Column(name = "saques_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saquesAcertados;
	
	@Column(name = "saques_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saquesTotales;
	
	@Column(name = "porcentaje_saques", scale = 2)
	private double porcentajeSaques;
	
	@Column(name = "recepciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepcionesAcertadas;
	
	@Column(name = "recepciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepcionesTotales;
	
	@Column(name = "porcentaje_recepciones", scale = 2)
	private double porcentajeRecepciones;
	
	@Column(name = "colocaciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocacionesAcertadas;
	
	@Column(name = "colocaciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocacionesTotales;
	
	@Column(name = "porcentaje_colocaciones", scale = 2)
	private double porcentajeColocaciones;
	
	@Column(name = "defensas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensasAcertadas;
	
	@Column(name = "defensas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensasTotales;
	
	@Column(name = "porcentaje_defensas", scale = 2)
	private double porcentajeDefensas;
	
	@Column(name = "bloqueos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueosAcertados;
	
	@Column(name = "bloqueos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueosTotales;
	
	@Column(name = "porcentaje_bloqueos", scale = 2)
	private double porcentajeBloqueos;
	
	@Column(name = "remates_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int rematesAcertados;
	
	@Column(name = "remates_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int rematesTotales;
	
	@Column(name = "porcentaje_remates", scale = 2)
	private double porcentajeRemates;
	
	@Column(name = "fintas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintasAcertadas;
	
	@Column(name = "fintas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintasTotales;
	
	@Column(name = "porcentaje_fintas", scale = 2)
	private double porcentajeFintas;
	
	@Column(name = "num_ataques_rapidos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAtaquesRapidosAcertados;
	
	@Column(name = "num_ataques_rapidos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAtaquesRapidosTotales;
	
	@Column(name = "porcentaje_ataques_rapidos", scale = 2)
	private double porcentajeAtaquesRapidos;
	
	@Column(name = "num_faltas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numFaltasTotales;
	

	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoCalentamiento;
	
	@Column(name = "tiempo_mejora_fisico", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoMejoraFisico;
	
	public Entrenamiento() {
	}
	
	public Entrenamiento(LocalDate fecha, String hora, Sistema sistema_juego) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.sistemaJuego = sistema_juego;
	}

	public Entrenamiento(LocalDate fecha, String hora, Sistema sistema_juego, int tiempo_calentamiento, int tiempo_mejora_fisico) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.sistemaJuego = sistema_juego;
		this.tiempoCalentamiento = tiempo_calentamiento;
		this.tiempoMejoraFisico = tiempo_mejora_fisico;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Sistema getSistemaJuego() {
		return sistemaJuego;
	}

	public void setSistemaJuego(Sistema sistemaJuego) {
		this.sistemaJuego = sistemaJuego;
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

	public int getTiempoCalentamiento() {
		return tiempoCalentamiento;
	}

	public void setTiempoCalentamiento(int tiempoCalentamiento) {
		this.tiempoCalentamiento = tiempoCalentamiento;
	}

	public int getTiempoMejoraFisico() {
		return tiempoMejoraFisico;
	}

	public void setTiempoMejoraFisico(int tiempoMejoraFisico) {
		this.tiempoMejoraFisico = tiempoMejoraFisico;
	}	

}
