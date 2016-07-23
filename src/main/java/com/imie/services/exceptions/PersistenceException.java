package com.imie.services.exceptions;

/**
 * Exception levée dans le service de persistence JPA.
 */
public class PersistenceException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9051845677816485567L;

	/**
	 * Instantiates a new persistence exception.
	 */
	public PersistenceException() {
		super();
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public PersistenceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param message
	 *            the message
	 */
	public PersistenceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new persistence exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}
}
