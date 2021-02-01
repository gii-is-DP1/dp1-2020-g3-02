package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.stereotype.Component;


@Component
public class TipoMaterialConverter implements AttributeConverter<TipoMaterial, String>{

	@Override
	public String convertToDatabaseColumn(final TipoMaterial valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public TipoMaterial convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : TipoMaterial.fromNombre(nombre);
	}

}
