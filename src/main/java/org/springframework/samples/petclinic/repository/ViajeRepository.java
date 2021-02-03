package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.stereotype.Repository;

@Repository("viajeRepository")
public interface ViajeRepository extends JpaRepository<Viaje,Serializable>{
	
	public List<Viaje> findByJugador(Jugador jugador);
	public List<Viaje> findByPartido(Partido partido);
	public List<Viaje> findByTipoViaje(TipoViaje tipoViaje);
	public List<Viaje> findByAutobus(Autobus autobus);
	public List<Viaje> findByPersonal(Personales personal);
	
	public Viaje findByJugadorAndPartidoAndTipoViaje(Jugador jugador, Partido partido, TipoViaje tipoViaje);
	public List<Viaje> findByJugadorAndTipoViaje(Jugador jugador, TipoViaje tipoViaje);
	public List<Viaje> findByPartidoAndTipoViaje(Partido partido, TipoViaje tipoViaje);
	public List<Viaje> findByJugadorAndPersonal(Jugador jugador, Personales personal);
	
	@Query("SELECT v.personal FROM Viaje v WHERE v.tipoViaje=:tipoViaje AND v.partido=:partido")
	public Set<Personales> findPersonalesByPartidoAndTipoViaje(@Param("partido") Partido partido,@Param("tipoViaje") TipoViaje tipoViaje);
}
