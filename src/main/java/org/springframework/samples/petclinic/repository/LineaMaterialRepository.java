package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.stereotype.Repository;


@Repository("lineaMaterialRepository")
public interface LineaMaterialRepository extends JpaRepository<LineaMaterial, Serializable> {


		public List<LineaMaterial> findByCantidad(Integer cantidad);

		@Query("SELECT a FROM LineaMaterial a, Entrenamiento j WHERE j.id=:entrenamiento_id")
		public List<LineaMaterial> findByEntrenamiento(@Param("entrenamiento_id") int entrenamiento_id);

}
