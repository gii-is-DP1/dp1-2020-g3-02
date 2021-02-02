package org.springframework.samples.petclinic.converter.enumerate;

import javax.persistence.AttributeConverter;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.stereotype.Component;


@Component
public class EstadoMaterialConverter implements AttributeConverter<EstadoMaterial, String>{

	@Override
	public String convertToDatabaseColumn(final EstadoMaterial valor) {
		return (valor == null) ? null : valor.name();
	}

	@Override
	public EstadoMaterial convertToEntityAttribute(final String nombre) {
		
		return (nombre == null) ? null : EstadoMaterial.fromNombre(nombre);
	}

}
