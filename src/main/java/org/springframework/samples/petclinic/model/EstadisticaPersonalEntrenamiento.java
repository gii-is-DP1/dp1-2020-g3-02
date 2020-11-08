package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name="estadisticasPersonalEntrenamiento")
public class EstadisticaPersonalEntrenamiento extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name= "entrenamiento_id")
	private Entrenamiento entrenamiento;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;

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
	
	
	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoCalentamiento;

	public EstadisticaPersonalEntrenamiento() {
	}

	public EstadisticaPersonalEntrenamiento(int saquesAcertados,int saquesTotales, double porcentajeSaques,
			int recepcionesAcertadas, int recepcionesTotales, double porcentajeRecepciones,
			int colocacionesAcertadas,int colocacionesTotales, double porcentajeColocaciones,
			int defensasAcertadas,int defensasTotales, double porcentajeDefensas,
			int bloqueosAcertados, int bloqueosTotales, double porcentajeBloqueos,
			int rematesAcertados, int rematesTotales, double porcentajeRemates,
			int fintasAcertadas, int fintasTotales, double porcentajeFintas,
			int numAtaquesRapidosAcertados, int numAtaquesRapidosTotales,
			double porcentajeAtaquesRapidos, int numFaltasTotales, int tiempoCalentamiento) {
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
		this.tiempoCalentamiento = tiempoCalentamiento;
	}

	public Entrenamiento getEntrenamiento() {
		return entrenamiento;
	}

	public void setEntrenamiento(Entrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
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

	@Override
	public String toString() {
		return "EstadisticaPersonalEntrenamiento [entrenamiento=" + entrenamiento + ", jugador=" + jugador
				+ ", saquesAcertados=" + saquesAcertados + ", saquesTotales=" + saquesTotales + ", porcentajeSaques="
				+ porcentajeSaques + ", recepcionesAcertadas=" + recepcionesAcertadas + ", recepcionesTotales="
				+ recepcionesTotales + ", porcentajeRecepciones=" + porcentajeRecepciones + ", colocacionesAcertadas="
				+ colocacionesAcertadas + ", colocacionesTotales=" + colocacionesTotales + ", porcentajeColocaciones="
				+ porcentajeColocaciones + ", defensasAcertadas=" + defensasAcertadas + ", defensasTotales="
				+ defensasTotales + ", porcentajeDefensas=" + porcentajeDefensas + ", bloqueosAcertados="
				+ bloqueosAcertados + ", bloqueosTotales=" + bloqueosTotales + ", porcentajeBloqueos="
				+ porcentajeBloqueos + ", rematesAcertados=" + rematesAcertados + ", rematesTotales=" + rematesTotales
				+ ", porcentajeRemates=" + porcentajeRemates + ", fintasAcertadas=" + fintasAcertadas
				+ ", fintasTotales=" + fintasTotales + ", porcentajeFintas=" + porcentajeFintas
				+ ", numAtaquesRapidosAcertados=" + numAtaquesRapidosAcertados + ", numAtaquesRapidosTotales="
				+ numAtaquesRapidosTotales + ", porcentajeAtaquesRapidos=" + porcentajeAtaquesRapidos
				+ ", numFaltasTotales=" + numFaltasTotales +
				", tiempoCalentamiento=" + tiempoCalentamiento + "]";
	}
	
	

}
