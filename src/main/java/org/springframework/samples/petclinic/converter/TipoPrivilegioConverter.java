package org.springframework.samples.petclinic.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;


@Converter
public class TipoPrivilegioConverter implements AttributeConverter<TipoPrivilegio, String>{

	@Override
	public String convertToDatabaseColumn(final TipoPrivilegio valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public TipoPrivilegio convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : TipoPrivilegio.fromNombre(nombre);
	}

}
