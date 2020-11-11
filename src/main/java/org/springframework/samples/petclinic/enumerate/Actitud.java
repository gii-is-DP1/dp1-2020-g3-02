package org.springframework.samples.petclinic.enumerate;

public enum Actitud {
	POSITIVA,NEGATIVA;
	
	public static Actitud fromNombre(String nombre) {
		for(Actitud a : Actitud.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
