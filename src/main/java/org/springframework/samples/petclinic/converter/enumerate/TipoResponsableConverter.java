package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.TipoResponsable;


@Converter
public class TipoResponsableConverter implements AttributeConverter<TipoResponsable, String>{

	@Override
	public String convertToDatabaseColumn(final TipoResponsable valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public TipoResponsable convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : TipoResponsable.fromNombre(nombre);
	}

}
