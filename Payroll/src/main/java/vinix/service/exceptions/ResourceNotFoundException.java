package vinix.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Long workerId) {
		super("não foi possivel achar o : " + workerId);
	}
	
	

}
