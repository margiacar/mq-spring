package com.example.mqspring;

public class MessageException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageException(String message) {
        super(message);
    }
}
