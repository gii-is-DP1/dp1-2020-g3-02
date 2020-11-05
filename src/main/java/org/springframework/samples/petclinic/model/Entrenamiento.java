package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Sistema;

@Entity
@Table(name = "entrenamiento", uniqueConstraints = @UniqueConstraint(columnNames = { "fecha","hora" }))
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
	
	@Column(name = "sistema_juego", columnDefinition = "varchar(255) default 'CINCO_UNO' NOT NULL check ('COLOCADOR_GENERAL','CUATRO_DOS', 'CINCO_UNO', 'SEIS_DOS')")
	@Enumerated(value = EnumType.STRING)
	private Sistema sistema_juego;
	
	@Column(name = "saques_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saques_acertados;
	
	@Column(name = "saques_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saques_totales;
	
	@Column(name = "porcentaje_saques", scale = 2)
	private double porcentaje_saques;
	
	@Column(name = "recepciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepciones_acertadas;
	
	@Column(name = "recepciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepciones_totales;
	
	@Column(name = "porcentaje_recepciones", scale = 2)
	private double porcentaje_recepciones;
	
	@Column(name = "colocaciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocaciones_acertadas;
	
	@Column(name = "colocaciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocaciones_totales;
	
	@Column(name = "porcentaje_colocaciones", scale = 2)
	private double porcentaje_colocaciones;
	
	@Column(name = "defensas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensas_acertadas;
	
	@Column(name = "defensas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensas_totales;
	
	@Column(name = "porcentaje_defensas", scale = 2)
	private double porcentaje_defensas;
	
	@Column(name = "bloqueos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueos_acertados;
	
	@Column(name = "bloqueos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueos_totales;
	
	@Column(name = "porcentaje_bloqueos", scale = 2)
	private double porcentaje_bloqueos;
	
	@Column(name = "remates_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int remates_acertados;
	
	@Column(name = "remates_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int remates_totales;
	
	@Column(name = "porcentaje_remates", scale = 2)
	private double porcentaje_remates;
	
	@Column(name = "fintas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintas_acertadas;
	
	@Column(name = "fintas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintas_totales;
	
	@Column(name = "porcentaje_fintas", scale = 2)
	private double porcentaje_fintas;
	
	@Column(name = "num_ataques_rapidos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int num_ataques_rapidos_acertados;
	
	@Column(name = "num_ataques_rapidos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int num_ataques_rapidos_totales;
	
	@Column(name = "porcentaje_ataques_rapidos", scale = 2)
	private double porcentaje_ataques_rapidos;
	
	@Column(name = "num_faltas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int num_faltas_totales;
	

	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo_calentamiento;
	
	@Column(name = "tiempo_mejora_fisico", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo_mejora_fisico;
	
	public Entrenamiento() {
	}
	
	public Entrenamiento(LocalDate fecha, String hora, Sistema sistema_juego) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.sistema_juego = sistema_juego;
	}

	public Entrenamiento(LocalDate fecha, String hora, Sistema sistema_juego, int tiempo_calentamiento, int tiempo_mejora_fisico) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.sistema_juego = sistema_juego;
		this.tiempo_calentamiento = tiempo_calentamiento;
		this.tiempo_mejora_fisico = tiempo_mejora_fisico;
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

	public Sistema getSistema_juego() {
		return sistema_juego;
	}

	public void setSistema_juego(Sistema sistema_juego) {
		this.sistema_juego = sistema_juego;
	}

	public int getSaques_acertados() {
		return saques_acertados;
	}

	public void setSaques_acertados(int saques_acertados) {
		this.saques_acertados = saques_acertados;
	}

	public int getSaques_totales() {
		return saques_totales;
	}

	public void setSaques_totales(int saques_totales) {
		this.saques_totales = saques_totales;
	}

	public double getPorcentaje_saques() {
		return porcentaje_saques;
	}

	public void setPorcentaje_saques(double porcentaje_saques) {
		this.porcentaje_saques = porcentaje_saques;
	}

	public int getRecepciones_acertadas() {
		return recepciones_acertadas;
	}

	public void setRecepciones_acertadas(int recepciones_acertadas) {
		this.recepciones_acertadas = recepciones_acertadas;
	}

	public int getRecepciones_totales() {
		return recepciones_totales;
	}

	public void setRecepciones_totales(int recepciones_totales) {
		this.recepciones_totales = recepciones_totales;
	}

	public double getPorcentaje_recepciones() {
		return porcentaje_recepciones;
	}

	public void setPorcentaje_recepciones(double porcentaje_recepciones) {
		this.porcentaje_recepciones = porcentaje_recepciones;
	}

	public int getColocaciones_acertadas() {
		return colocaciones_acertadas;
	}

	public void setColocaciones_acertadas(int colocaciones_acertadas) {
		this.colocaciones_acertadas = colocaciones_acertadas;
	}

	public int getColocaciones_totales() {
		return colocaciones_totales;
	}

	public void setColocaciones_totales(int colocaciones_totales) {
		this.colocaciones_totales = colocaciones_totales;
	}

	public double getPorcentaje_colocaciones() {
		return porcentaje_colocaciones;
	}

	public void setPorcentaje_colocaciones(double porcentaje_colocaciones) {
		this.porcentaje_colocaciones = porcentaje_colocaciones;
	}

	public int getDefensas_acertadas() {
		return defensas_acertadas;
	}

	public void setDefensas_acertadas(int defensas_acertadas) {
		this.defensas_acertadas = defensas_acertadas;
	}

	public int getDefensas_totales() {
		return defensas_totales;
	}

	public void setDefensas_totales(int defensas_totales) {
		this.defensas_totales = defensas_totales;
	}

	public double getPorcentaje_defensas() {
		return porcentaje_defensas;
	}

	public void setPorcentaje_defensas(double porcentaje_defensas) {
		this.porcentaje_defensas = porcentaje_defensas;
	}

	public int getBloqueos_acertados() {
		return bloqueos_acertados;
	}

	public void setBloqueos_acertados(int bloqueos_acertados) {
		this.bloqueos_acertados = bloqueos_acertados;
	}

	public int getBloqueos_totales() {
		return bloqueos_totales;
	}

	public void setBloqueos_totales(int bloqueos_totales) {
		this.bloqueos_totales = bloqueos_totales;
	}

	public double getPorcentaje_bloqueos() {
		return porcentaje_bloqueos;
	}

	public void setPorcentaje_bloqueos(double porcentaje_bloqueos) {
		this.porcentaje_bloqueos = porcentaje_bloqueos;
	}

	public int getRemates_acertados() {
		return remates_acertados;
	}

	public void setRemates_acertados(int remates_acertados) {
		this.remates_acertados = remates_acertados;
	}

	public int getRemates_totales() {
		return remates_totales;
	}

	public void setRemates_totales(int remates_totales) {
		this.remates_totales = remates_totales;
	}

	public double getPorcentaje_remates() {
		return porcentaje_remates;
	}

	public void setPorcentaje_remates(double porcentaje_remates) {
		this.porcentaje_remates = porcentaje_remates;
	}

	public int getFintas_acertadas() {
		return fintas_acertadas;
	}

	public void setFintas_acertadas(int fintas_acertadas) {
		this.fintas_acertadas = fintas_acertadas;
	}

	public int getFintas_totales() {
		return fintas_totales;
	}

	public void setFintas_totales(int fintas_totales) {
		this.fintas_totales = fintas_totales;
	}

	public double getPorcentaje_fintas() {
		return porcentaje_fintas;
	}

	public void setPorcentaje_fintas(double porcentaje_fintas) {
		this.porcentaje_fintas = porcentaje_fintas;
	}

	public int getNum_ataques_rapidos_acertados() {
		return num_ataques_rapidos_acertados;
	}

	public void setNum_ataques_rapidos_acertados(int num_ataques_rapidos_acertados) {
		this.num_ataques_rapidos_acertados = num_ataques_rapidos_acertados;
	}

	public int getNum_ataques_rapidos_totales() {
		return num_ataques_rapidos_totales;
	}

	public void setNum_ataques_rapidos_totales(int num_ataques_rapidos_totales) {
		this.num_ataques_rapidos_totales = num_ataques_rapidos_totales;
	}

	public double getPorcentaje_ataques_rapidos() {
		return porcentaje_ataques_rapidos;
	}

	public void setPorcentaje_ataques_rapidos(double porcentaje_ataques_rapidos) {
		this.porcentaje_ataques_rapidos = porcentaje_ataques_rapidos;
	}

	public int getNum_faltas_totales() {
		return num_faltas_totales;
	}

	public void setNum_faltas_totales(int num_faltas_totales) {
		this.num_faltas_totales = num_faltas_totales;
	}

	public int getTiempo_calentamiento() {
		return tiempo_calentamiento;
	}

	public void setTiempo_calentamiento(int tiempo_calentamiento) {
		this.tiempo_calentamiento = tiempo_calentamiento;
	}

	public int getTiempo_mejora_fisico() {
		return tiempo_mejora_fisico;
	}

	public void setTiempo_mejora_fisico(int tiempo_mejora_fisico) {
		this.tiempo_mejora_fisico = tiempo_mejora_fisico;
	}
	
	

}
