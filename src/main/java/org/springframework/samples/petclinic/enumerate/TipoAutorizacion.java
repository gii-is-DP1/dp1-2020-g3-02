package org.springframework.samples.petclinic.enumerate;

public enum TipoAutorizacion {
TRANSPORTE, USOIMAGEN,RESPONSABILIDADLESION,EXCURSIONES;

public static TipoAutorizacion fromNombre(String nombre) {
	for(TipoAutorizacion a : TipoAutorizacion.values()) {
		if(a.name().equals(nombre)) {
			return a;
		}
	}
	return null;
}
}
