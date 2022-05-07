package validadores;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import modelo.Persona;

public class PersonaValidator {

	protected String nombre;
	protected String edadStr;
	protected int edad;
	
	protected Map<String, String> errores = new HashMap<>(); 

	public PersonaValidator(String nombre, String edadStr) {
		this.nombre 	= nombre;
		this.edadStr 	= edadStr;
	}
	
	public Map<String, String> getErrores() {
		return errores;
	}

	public Persona makeObject() {
		if( isValid() ) {
			return new Persona(nombre, edad);
		} else {
			return null;
		}
	}
	
	public boolean isValid() {
		// se usa & para que se ejecuten ambos métodos 
		if( isValidNombre() & isValidEdad() ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isValidNombre() {
		if(nombre.length() > 5) {
			return true;
		} else {
			errores.put("nombre", "Nombre muy corto.");
			return false;
		}
	}
	
	public boolean isValidEdad() {
		try {
			edad = Integer.parseInt(edadStr);
		} catch(NumberFormatException nfe) {
			errores.put("edad", "La edad debe ser un número entero.");
			return false;
		}
		
		if(edad >= 18) {
			return true;
		} else {
			errores.put("edad", "Debe ser mayor de 18 años.");
			return false;
		}
	}

	@Override
	public String toString() {
		return errores.values().stream().collect(Collectors.joining(" "));
	}

	public String getNombre() {
		return nombre;
	}

	public String getEdadStr() {
		return edadStr;
	}
	
}
