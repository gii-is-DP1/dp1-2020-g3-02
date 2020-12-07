package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;

import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.stereotype.Component;


@Component
public class TipoEjercicioConverter implements AttributeConverter<TipoEjercicio, String>{

	@Override
	public String convertToDatabaseColumn(final TipoEjercicio valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public TipoEjercicio convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : TipoEjercicio.fromNombre(nombre);
	}

}
