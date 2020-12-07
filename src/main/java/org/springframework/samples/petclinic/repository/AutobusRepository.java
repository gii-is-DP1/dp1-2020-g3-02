package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.stereotype.Repository;

@Repository("autobusRepository")
public interface AutobusRepository extends JpaRepository<Autobus, Serializable>{
	


}
