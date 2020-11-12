package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
@Table(name = "linea_material")
public class LineaMaterial extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
	
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