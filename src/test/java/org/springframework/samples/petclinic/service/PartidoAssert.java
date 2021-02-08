package org.springframework.samples.petclinic.service;

import org.assertj.core.api.AbstractObjectAssert;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;

public class PartidoAssert extends AbstractObjectAssert<PartidoAssert, Partido> {
	
	public PartidoAssert hasFecha(String fecha) {
	    isNotNull();
	    if (!actual.getFecha().toString().equals(fecha)) {
	        failWithMessage("Expected partido to have fecha %s but was %s", fecha, actual.getFecha().toString());
	    }
	    return this;
	}
	
	public PartidoAssert hasNotFecha(String fecha) {
	    isNotNull();
	    if (actual.getFecha().toString().equals(fecha)) {
	        failWithMessage("Expected partido not to have fecha %s but was %s", fecha, actual.getFecha().toString());
	    }
	    return this;
	}
	
	public PartidoAssert hasHora(String hora) {
	    isNotNull();
	    if (!actual.getHora().toString().equals(hora)) {
	        failWithMessage("Expected partido to have hora %s but was %s", hora, actual.getHora().toString());
	    }
	    return this;
	}
	
	public PartidoAssert hasNotPuntosTotales() {
	    isNotNull();
	    if (actual.getNumPuntosTotales() != 0) {
	        failWithMessage("Expected partido to have %s but was %s", 0, actual.getNumPuntosTotales());
	    }
	    return this;
	}
	
	public PartidoAssert isInEquipo(Equipo equipo) {
	    isNotNull();
	    if (!actual.getEquipo().equals(equipo)) {
	        failWithMessage("Expected partido to are in equipo %s but was %s", equipo, actual.getEquipo());
	    }
	    return this;
	}
	
	public PartidoAssert isHoraBefore(String hora) {
	    isNotNull();
	    if (actual.getHora().toString().compareTo(hora) > 0) {
	        failWithMessage("Expected partido to have hora before %s but was %s", hora, actual.getHora().toString());
	    }
	    return this;
	}
	
	public PartidoAssert isHoraAfter(String hora) {
	    isNotNull();
	    if (actual.getHora().toString().compareTo(hora) < 0) {
	        failWithMessage("Expected partido to have hora after %s but was %s", hora, actual.getHora().toString());
	    }
	    return this;
	}
	
	public PartidoAssert hasFaltasTotalesGreaterThan(Integer numFaltas) {
	    isNotNull();
	    if (actual.getNumFaltasTotales() < numFaltas) {
	        failWithMessage("Expected partido to have numero de faltas totales greater than %s but was %s", 0, actual.getNumPuntosTotales());
	    }
	    return this;
	}
	
	public PartidoAssert hasNotFaltasTotalesGreaterThan(Integer numFaltas) {
	    isNotNull();
	    if (actual.getNumFaltasTotales() > numFaltas) {
	    	failWithMessage("Expected partido not to have numero de faltas totales greater than %s but was %s", 0, actual.getNumPuntosTotales());
	    }
	    return this;
	}
	
	public PartidoAssert(Partido actual) {
		super(actual, PartidoAssert.class);
	}
	
	public static PartidoAssert assertThat(Partido actual) {
	    return new PartidoAssert(actual);
	}

}
