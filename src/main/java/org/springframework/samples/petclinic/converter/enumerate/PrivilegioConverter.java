package org.springframework.samples.petclinic.converter.enumerate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Privilegio;
import org.springframework.samples.petclinic.model.ediciones.PrivilegioEdit;
import org.springframework.stereotype.Component;

@Component
public class PrivilegioConverter {

	public List<Privilegio> convertListPrivilegiosEditToListPrivilegios(List<TipoPrivilegio> privilegiosEdit, Jugador jugador, Equipo equipo) {
		List<Privilegio> privilegios = new ArrayList<Privilegio>();
		for(TipoPrivilegio privilegioEdit:privilegiosEdit) {
				Privilegio privilegio = new Privilegio(jugador, equipo, privilegioEdit, "");
				privilegios.add(privilegio);
		}
		return privilegios;
	}
}
