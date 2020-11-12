package org.springframework.samples.petclinic.model;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.TipoMaterial;

import lombok.Data;

@Data
@Entity
@Table(name = "materiales")
public class Material extends BaseEntity{

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
	private Set<LineaMaterial> lineas_material;

	@Column(name = "descripcion", nullable = false, length = 280)
	private String descripcion;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoMaterial tipo;

	@Column(name = "stock", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int stock;

	public Material(String descripcion, TipoMaterial tipo, int stock) {
		super();
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.stock = stock;
	}

	public Material() {}

}
