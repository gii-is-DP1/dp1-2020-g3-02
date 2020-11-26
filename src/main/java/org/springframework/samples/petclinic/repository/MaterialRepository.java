package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.stereotype.Repository;

@Repository("materialRepository")
public interface MaterialRepository extends JpaRepository<Material, Serializable>{
	
	public List<Material> findByTipo(TipoMaterial tipo);
	public List<Material> findByDescripcion(String descripcion);
	public List<Material> findByStock(Integer stock);
	
//	@Query("SELECT m FROM Material m, LineaMaterial l WHERE l.id=:linea_material_id")
	public List<Material> findByLineaMaterial(LineaMaterial lineaMaterial);
}
