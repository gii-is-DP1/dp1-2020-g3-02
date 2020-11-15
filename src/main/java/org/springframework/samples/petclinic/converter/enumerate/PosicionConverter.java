package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.stereotype.Component;


@Component
public class PosicionConverter implements AttributeConverter<Posicion, String>{

	@Override
	public String convertToDatabaseColumn(final Posicion valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public Posicion convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : Posicion.fromNombre(nombre);
	}

}
