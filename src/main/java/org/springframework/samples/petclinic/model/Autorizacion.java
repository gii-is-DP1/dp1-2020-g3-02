package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoResponsable;

@Entity
@Table(name = "autorizacion")
public class Autorizacion extends BaseEntity{

	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "tipo", columnDefinition = "varchar(255) check('TRANSPORTE', 'USOIMAGEN','RESPONSABILIDADLESION','EXCURSIONES')")
	@Enumerated(value = EnumType.STRING)
	private TipoAutorizacion tipo;

	@Column(name = "responsable", columnDefinition = "varchar(255) check('MADRE','PADRE','TUTORLEGAL')")
	@Enumerated(value = EnumType.STRING)
	private TipoResponsable responsable;

	public Autorizacion(LocalDate fecha, TipoAutorizacion tipo, TipoResponsable responsable) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.responsable = responsable;
	}
	public Autorizacion() {}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public TipoAutorizacion getTipo() {
		return tipo;
	}
	public void setTipo(TipoAutorizacion tipo) {
		this.tipo = tipo;
	}
	public TipoResponsable getResponsable() {
		return responsable;
	}
	public void setResponsable(TipoResponsable responsable) {
		this.responsable = responsable;
	}

}
