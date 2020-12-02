package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materiales", uniqueConstraints = @UniqueConstraint(columnNames = { "tipo" }))
public class Material extends BaseEntity{


	@ManyToOne
	@JoinColumn(name = "linea_material_id")
	private LineaMaterial lineaMaterial;

	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoMaterial tipo;

	@Column(name = "stock", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private Integer stock;
	
	@Column(name = "estado", nullable = false, columnDefinition = "varchar(30) default 'BUENO'")
	@Enumerated(value = EnumType.STRING)
	private EstadoMaterial estado;

}
