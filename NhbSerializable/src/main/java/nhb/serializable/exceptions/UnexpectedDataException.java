package nhb.serializable.exceptions;

public class UnexpectedDataException extends RuntimeException {
	private static final long serialVersionUID = 146413716861693893L;

	public UnexpectedDataException() {
		super();
	}

	public UnexpectedDataException(String message) {
		super(message);
	}

	public UnexpectedDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedDataException(Throwable cause) {
		super(cause);
	}
}
