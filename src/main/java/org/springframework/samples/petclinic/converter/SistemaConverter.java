package org.springframework.samples.petclinic.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.Sistema;


@Converter
public class SistemaConverter implements AttributeConverter<Sistema, String>{

	@Override
	public String convertToDatabaseColumn(final Sistema valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public Sistema convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : Sistema.fromNombre(nombre);
	}

}
