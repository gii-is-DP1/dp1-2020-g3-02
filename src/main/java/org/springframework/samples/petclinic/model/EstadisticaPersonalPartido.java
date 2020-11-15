package org.springframework.samples.petclinic.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name="estadisticasPersonalPartido")
public class EstadisticaPersonalPartido extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name= "partido_id")
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name = "estadistico_id")
	private Estadistico estadistico;

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
	
	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoCalentamiento;

	public EstadisticaPersonalPartido() {
	}

	public EstadisticaPersonalPartido(int saquesAcertados,int saquesTotales, double porcentajeSaques,
			int recepcionesAcertadas, int recepcionesTotales, double porcentajeRecepciones,
			int colocacionesAcertadas,int colocacionesTotales, double porcentajeColocaciones,
			int defensasAcertadas,int defensasTotales, double porcentajeDefensas,
			int bloqueosAcertados, int bloqueosTotales, double porcentajeBloqueos,
			int rematesAcertados, int rematesTotales, double porcentajeRemates,
			int fintasAcertadas, int fintasTotales, double porcentajeFintas,
			int numAtaquesRapidosAcertados, int numAtaquesRapidosTotales,
			double porcentajeAtaquesRapidos, int numFaltasTotales, int numAmarillas,
			int numRojas, int tiempoCalentamiento) {
		super();
		this.saquesAcertados = saquesAcertados;
		this.saquesTotales = saquesTotales;
		this.porcentajeSaques = porcentajeSaques;
		this.recepcionesAcertadas = recepcionesAcertadas;
		this.recepcionesTotales = recepcionesTotales;
		this.porcentajeRecepciones = porcentajeRecepciones;
		this.colocacionesAcertadas = colocacionesAcertadas;
		this.colocacionesTotales = colocacionesTotales;
		this.porcentajeColocaciones = porcentajeColocaciones;
		this.defensasAcertadas = defensasAcertadas;
		this.defensasTotales = defensasTotales;
		this.porcentajeDefensas = porcentajeDefensas;
		this.bloqueosAcertados = bloqueosAcertados;
		this.bloqueosTotales = bloqueosTotales;
		this.porcentajeBloqueos = porcentajeBloqueos;
		this.rematesAcertados = rematesAcertados;
		this.rematesTotales = rematesTotales;
		this.porcentajeRemates = porcentajeRemates;
		this.fintasAcertadas = fintasAcertadas;
		this.fintasTotales = fintasTotales;
		this.porcentajeFintas = porcentajeFintas;
		this.numAtaquesRapidosAcertados = numAtaquesRapidosAcertados;
		this.numAtaquesRapidosTotales = numAtaquesRapidosTotales;
		this.porcentajeAtaquesRapidos = porcentajeAtaquesRapidos;
		this.numFaltasTotales = numFaltasTotales;
		this.numAmarillas = numAmarillas;
		this.numRojas = numRojas;
		this.tiempoCalentamiento = tiempoCalentamiento;
	}

	@Override
	public String toString() {
		return "EstadisticaPersonalPartido [partido=" + partido + ", jugador=" + jugador + ", saquesAcertados="
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
				+ ", tiempoCalentamiento=" + tiempoCalentamiento + "]";
	}
	
}
