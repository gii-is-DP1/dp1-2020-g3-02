package org.springframework.samples.petclinic.enumerate;

public enum TipoViaje {
IDA, VUELTA;

public static TipoViaje fromNombre(String nombre) {
	for(TipoViaje a : TipoViaje.values()) {
		if(a.name().equals(nombre)) {
			return a;
		}
	}
	return null;
}
}
