package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Repository;

@Repository("estadisticoRepository")
public interface EstadisticoRepository extends JpaRepository<Estadistico, Serializable>{

	public List<Estadistico> findByFirstName(String name);
	public Estadistico findByEmail(String email);
	public List<Estadistico> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public List<Estadistico> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public Estadistico findByUser(User user);
	
}
