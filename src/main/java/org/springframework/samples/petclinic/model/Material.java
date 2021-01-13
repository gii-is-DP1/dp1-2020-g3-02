package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "materiales", uniqueConstraints = @UniqueConstraint(columnNames = { "tipo","estado" }))
public class Material extends BaseEntity{

	public Material(String nombre, TipoMaterial tipo, Integer stock) {
		this.descripcion = nombre;
		this.tipo = tipo;
		this.stock = stock;
	}
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "material")
	private Set<LineaMaterial> lineaMaterial;

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

	public Material(String descripcion, TipoMaterial tipo, @Min(0) Integer stock, EstadoMaterial estado) {
		super();
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.stock = stock;
		this.estado = estado;
	}
	public Material() {
		
	}
	@Override
	public String toString() {
		return "Material [descripcion=" + descripcion + ", tipo=" + tipo + ", stock=" + stock + ", estado=" + estado
				+ "]";
	}
	
}
