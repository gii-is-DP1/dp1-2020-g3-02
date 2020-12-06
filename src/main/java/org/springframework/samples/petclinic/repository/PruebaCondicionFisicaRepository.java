package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;

public interface PruebaCondicionFisicaRepository extends JpaRepository<PruebaCondicionFisica, Serializable> {
	

	public List<PruebaCondicionFisica> findByTipoPrueba(TipoPrueba tipo_prueba);
	public List<PruebaCondicionFisica> findByDatoLessThanEqual(double dato);
	public List<PruebaCondicionFisica> findByJugadorAndTipoPrueba(Jugador jugador, TipoPrueba tipo_prueba);
	public List<PruebaCondicionFisica> findByDatoAndTipoPrueba(double dato, TipoPrueba tipo_prueba);
	public List<PruebaCondicionFisica> findByTipoPruebaAndDatoLessThanEqual(TipoPrueba tipo_prueba, double dato);

//	@Query("SELECT p FROM PruebaCondicionFisica p, Jugador j WHERE j.id=:jugador_id")
	public List<PruebaCondicionFisica> findByJugador(Jugador jugador);

}
