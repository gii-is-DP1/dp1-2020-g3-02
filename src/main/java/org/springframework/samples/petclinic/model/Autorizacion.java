package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autorizaciones")
public class Autorizacion extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoAutorizacion tipoAutorizacion;

	
}
