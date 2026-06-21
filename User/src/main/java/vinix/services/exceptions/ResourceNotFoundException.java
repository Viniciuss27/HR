package vinix.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object obj) {
		super("o recurso: " + obj + " não foi encontrado" );
	}
	
	

}
