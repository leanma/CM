package unlam.edu.un;

public class MiExcepcion extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public MiExcepcion(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
