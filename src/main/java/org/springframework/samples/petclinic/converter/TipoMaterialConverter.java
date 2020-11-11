package org.springframework.samples.petclinic.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;


@Converter
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
