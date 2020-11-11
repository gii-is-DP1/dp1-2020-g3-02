package org.springframework.samples.petclinic.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.Estado;


@Converter
public class EstadoConverter implements AttributeConverter<Estado, String>{

	@Override
	public String convertToDatabaseColumn(final Estado valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public Estado convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : Estado.fromNombre(nombre);
	}

}
