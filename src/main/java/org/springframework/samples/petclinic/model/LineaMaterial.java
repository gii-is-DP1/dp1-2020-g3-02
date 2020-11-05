package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "linea_material")
public class LineaMaterial extends BaseEntity {
	@Column(name = "cantidad", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int cantidad;
	
	public LineaMaterial() {
	}

	public LineaMaterial(int cantidad) {
		super();
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}