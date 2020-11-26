package org.springframework.samples.petclinic.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.model.padres.BaseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
@Entity
@Table(name = "linea_material")
public class LineaMaterial extends BaseEntity {
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaMaterial")
	private Set<Material> material;
	
	@ManyToOne
	@JoinColumn(name = "entrenamiento_id")
	private Entrenamiento entrenamiento;
	
	
	
	@Column(name = "cantidad", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int cantidad;



	
	public LineaMaterial() {
	}

	public LineaMaterial(int cantidad) {
		super();
		this.cantidad = cantidad;
	}

}