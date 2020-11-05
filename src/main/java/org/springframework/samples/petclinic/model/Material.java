package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.TipoMaterial;


@Entity
@Table(name = "material")
public class Material extends BaseEntity{


	@Column(name = "descripcion", nullable = false, length = 280)
	private String descripcion;

	@Column(name = "tipo", columnDefinition = "varchar(255) check('BALONMEDICINAL','BALONDEJUEGO','RED','POSTE','CONOBAJO','CONOMEDIO','CONOALTO','CUERDA','CINTA')")
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoMaterial getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaterial tipo) {
		this.tipo = tipo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


}
