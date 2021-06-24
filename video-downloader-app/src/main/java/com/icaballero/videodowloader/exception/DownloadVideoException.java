package com.icaballero.videodowloader.exception;


/**
 * Custom Exception class
 * @author Ismael Caballero Hernandez
 *
 */
public class DownloadVideoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2416861049629605131L;

	public DownloadVideoException() {
		super();

	}

	public DownloadVideoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DownloadVideoException(String message, Throwable cause) {
		super(message, cause);

	}

	public DownloadVideoException(String message) {
		super(message);

	}

	public DownloadVideoException(Throwable cause) {
		super(cause);

	}
	
	
	

}
