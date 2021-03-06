package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.stereotype.Component;


@Component
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
