package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;


@Converter
public class TipoAutorizacionConverter implements AttributeConverter<TipoAutorizacion, String>{

	@Override
	public String convertToDatabaseColumn(final TipoAutorizacion valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public TipoAutorizacion convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : TipoAutorizacion.fromNombre(nombre);
	}

}
