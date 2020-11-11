package org.springframework.samples.petclinic.enumerate;

public enum TipoPrivilegio {
	PARTIDOS, ENTRENAMIENTOS;
	
	public static TipoPrivilegio fromNombre(String nombre) {
		for(TipoPrivilegio a : TipoPrivilegio.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
