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
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Sistema;

@Entity
@Table(name = "partidos")
public class Partido extends BaseEntity{
	
	@ManyToMany
	@JoinTable(name = "juegaPartido", joinColumns = @JoinColumn(name = "partido_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
	
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
	
	@Column(name = "num_amarillas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAmarillas;
	
	@Column(name = "num_rojas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numRojas;
	
	@Column(name = "num_puntos_set1", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet1;
	
	@Column(name = "num_puntos_set2", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet2;
	
	@Column(name = "num_puntos_set3", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet3;
	
	@Column(name = "num_puntos_set4", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet4;
	
	@Column(name = "num_puntos_set5", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet5;
	
	@Column(name = "num_puntos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosTotales;
	
	@Column(name = "num_tiempos_muertos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numTiemposMuertosTotales;
	
	@Column(name = "tiempo_total_partido", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoTotalPartido;
	
	@Column(name = "num_sustituciones", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numSustituciones;
	
	@Column(name = "tiempo_colocador_general", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoColocadorGeneral;
	
	@Column(name = "tiempo_5_1", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo51;
	
	@Column(name = "tiempo_4_2", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo42;
	
	@Column(name = "tiempo_6_2", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo62;
	
	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoCalentamiento;
	
	public Partido() {
	}
	
	public Partido(LocalDate fecha, String hora, Sistema sistema_juego) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.sistemaJuego = sistema_juego;
	}

	public Partido(LocalDate fecha, String hora, Sistema sistema_juego, int num_puntos_set1, int num_puntos_set2,
			int num_puntos_set3, int num_puntos_set4, int num_puntos_set5, int tiempo_colocador_general, int tiempo_5_1,
			int tiempo_4_2, int tiempo_6_2, int tiempo_calentamiento) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.sistemaJuego = sistema_juego;
		this.numPuntosSet1 = num_puntos_set1;
		this.numPuntosSet2 = num_puntos_set2;
		this.numPuntosSet3 = num_puntos_set3;
		this.numPuntosSet4 = num_puntos_set4;
		this.numPuntosSet5 = num_puntos_set5;
		this.tiempoColocadorGeneral = tiempo_colocador_general;
		this.tiempo51 = tiempo_5_1;
		this.tiempo42 = tiempo_4_2;
		this.tiempo62 = tiempo_6_2;
		this.tiempoCalentamiento = tiempo_calentamiento;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
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

	public int getNumPuntosSet1() {
		return numPuntosSet1;
	}

	public void setNumPuntosSet1(int numPuntosSet1) {
		this.numPuntosSet1 = numPuntosSet1;
	}

	public int getNumPuntosSet2() {
		return numPuntosSet2;
	}

	public void setNumPuntosSet2(int numPuntosSet2) {
		this.numPuntosSet2 = numPuntosSet2;
	}

	public int getNumPuntosSet3() {
		return numPuntosSet3;
	}

	public void setNumPuntosSet3(int numPuntosSet3) {
		this.numPuntosSet3 = numPuntosSet3;
	}

	public int getNumPuntosSet4() {
		return numPuntosSet4;
	}

	public void setNumPuntosSet4(int numPuntosSet4) {
		this.numPuntosSet4 = numPuntosSet4;
	}

	public int getNumPuntosSet5() {
		return numPuntosSet5;
	}

	public void setNumPuntosSet5(int numPuntosSet5) {
		this.numPuntosSet5 = numPuntosSet5;
	}

	public int getNumPuntosTotales() {
		return numPuntosTotales;
	}

	public void setNumPuntosTotales(int numPuntosTotales) {
		this.numPuntosTotales = numPuntosTotales;
	}

	public int getNumTiemposMuertosTotales() {
		return numTiemposMuertosTotales;
	}

	public void setNumTiemposMuertosTotales(int numTiemposMuertosTotales) {
		this.numTiemposMuertosTotales = numTiemposMuertosTotales;
	}

	public int getTiempoTotalPartido() {
		return tiempoTotalPartido;
	}

	public void setTiempoTotalPartido(int tiempoTotalPartido) {
		this.tiempoTotalPartido = tiempoTotalPartido;
	}

	public int getNumSustituciones() {
		return numSustituciones;
	}

	public void setNumSustituciones(int numSustituciones) {
		this.numSustituciones = numSustituciones;
	}

	public int getTiempoColocadorGeneral() {
		return tiempoColocadorGeneral;
	}

	public void setTiempoColocadorGeneral(int tiempoColocadorGeneral) {
		this.tiempoColocadorGeneral = tiempoColocadorGeneral;
	}

	public int getTiempo51() {
		return tiempo51;
	}

	public void setTiempo51(int tiempo51) {
		this.tiempo51 = tiempo51;
	}

	public int getTiempo42() {
		return tiempo42;
	}

	public void setTiempo42(int tiempo42) {
		this.tiempo42 = tiempo42;
	}

	public int getTiempo62() {
		return tiempo62;
	}

	public void setTiempo62(int tiempo62) {
		this.tiempo62 = tiempo62;
	}

	public int getTiempoCalentamiento() {
		return tiempoCalentamiento;
	}

	public void setTiempoCalentamiento(int tiempoCalentamiento) {
		this.tiempoCalentamiento = tiempoCalentamiento;
	}

}
