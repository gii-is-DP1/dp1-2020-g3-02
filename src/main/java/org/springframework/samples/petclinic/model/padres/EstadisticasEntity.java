package org.springframework.samples.petclinic.model.padres;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import lombok.Data;

@MappedSuperclass
public class EstadisticasEntity extends BaseEntity{
	
	@Column(name = "saques_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int saquesAcertados;
	
	@Column(name = "saques_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int saquesTotales;
	
	@Column(name = "porcentaje_saques", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeSaques;
	
	@Column(name = "recepciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int recepcionesAcertadas;
	
	@Column(name = "recepciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int recepcionesTotales;
	
	@Column(name = "porcentaje_recepciones", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeRecepciones;
	
	@Column(name = "colocaciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int colocacionesAcertadas;
	
	@Column(name = "colocaciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int colocacionesTotales;
	
	@Column(name = "porcentaje_colocaciones", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeColocaciones;
	
	@Column(name = "defensas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int defensasAcertadas;
	
	@Column(name = "defensas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int defensasTotales;
	
	@Column(name = "porcentaje_defensas", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeDefensas;
	
	@Column(name = "bloqueos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int bloqueosAcertados;
	
	@Column(name = "bloqueos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int bloqueosTotales;
	
	@Column(name = "porcentaje_bloqueos", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeBloqueos;
	
	@Column(name = "remates_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int rematesAcertados;
	
	@Column(name = "remates_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int rematesTotales;
	
	@Column(name = "porcentaje_remates", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeRemates;
	
	@Column(name = "fintas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int fintasAcertadas;
	
	@Column(name = "fintas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int fintasTotales;
	
	@Column(name = "porcentaje_fintas", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeFintas;
	
	@Column(name = "num_ataques_rapidos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int numAtaquesRapidosAcertados;
	
	@Column(name = "num_ataques_rapidos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int numAtaquesRapidosTotales;
	
	@Column(name = "porcentaje_ataques_rapidos", scale = 2, columnDefinition = "double default 0")
	protected double porcentajeAtaquesRapidos;
	
	@Column(name = "num_faltas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	protected int numFaltasTotales;

}
