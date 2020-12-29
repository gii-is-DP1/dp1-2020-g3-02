package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "linea_material")
public class LineaMaterial extends BaseEntity {
	

	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "entrenamiento_id")
	private Entrenamiento entrenamiento;
	
	@Column(name = "cantidad", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private Integer cantidad;

}