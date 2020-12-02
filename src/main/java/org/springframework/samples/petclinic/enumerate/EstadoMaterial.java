package org.springframework.samples.petclinic.enumerate;

public enum EstadoMaterial {
	INSERVIBLE, DAÑADO, ACEPTABLE, BUENO, NUEVO;
	
	public static EstadoMaterial fromNombre(String nombre) {
		for(EstadoMaterial a : EstadoMaterial.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
