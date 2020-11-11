package org.springframework.samples.petclinic.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;


@Converter
public class TipoPruebaConverter implements AttributeConverter<TipoPrueba, String>{

	@Override
	public String convertToDatabaseColumn(final TipoPrueba valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public TipoPrueba convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : TipoPrueba.fromNombre(nombre);
	}

}
