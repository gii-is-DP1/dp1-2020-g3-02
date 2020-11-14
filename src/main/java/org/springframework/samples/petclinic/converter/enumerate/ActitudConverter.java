package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;


@Converter
public class ActitudConverter implements AttributeConverter<Actitud, String>{

	@Override
	public String convertToDatabaseColumn(final Actitud valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public Actitud convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : Actitud.fromNombre(nombre);
	}

}
