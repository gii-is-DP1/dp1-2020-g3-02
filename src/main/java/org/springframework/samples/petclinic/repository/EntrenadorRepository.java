package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.stereotype.Repository;

@Repository("entrenadorRepository")
public interface EntrenadorRepository extends JpaRepository<Entrenador, Serializable>{
	
	public List<Entrenador> findByFirstName(String name);
	public List<Entrenador> findByEmail(String email);
	public List<Entrenador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public List<Entrenador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);

}
