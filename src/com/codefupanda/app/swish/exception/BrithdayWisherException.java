/**
 * 
 */
package com.codefupanda.app.swish.exception;

/**
 * @author Shashank Kularni
 *
 */
public class BrithdayWisherException extends Exception {

	private static final long serialVersionUID = 1L;

	public BrithdayWisherException() {
	}

	public BrithdayWisherException(String detailMessage) {
		super(detailMessage);
	}

	public BrithdayWisherException(Throwable throwable) {
		super(throwable);
	}

	public BrithdayWisherException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
