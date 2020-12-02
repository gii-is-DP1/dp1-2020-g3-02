package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Entity
@Table(name = "materiales", uniqueConstraints = @UniqueConstraint(columnNames = { "tipo" }))
public class Material extends BaseEntity{


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
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
	
	public Material () {}
	
	public Material(String descripcion, TipoMaterial tipo, @Min(0) Integer stock, EstadoMaterial estado) {
		super();
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.stock = stock;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Material [descripcion=" + descripcion + ", tipo=" + tipo + ", stock=" + stock + ", estado=" + estado
				+ "]";
	}

}
