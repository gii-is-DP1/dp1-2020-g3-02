package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.stereotype.Component;


@Component
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
