package nhb.serializable.exceptions;

public class UnsupportedTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedTypeException() {
		super();
	}

	public UnsupportedTypeException(String string) {
		super(string);
	}

	public UnsupportedTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedTypeException(Throwable cause) {
		super(cause);
	}
}
