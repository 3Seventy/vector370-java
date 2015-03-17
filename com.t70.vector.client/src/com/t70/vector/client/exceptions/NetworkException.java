package com.t70.vector.client.exceptions;

/**
 * Represents an network communication error when attempting to reach the REST server.
 * 
 * This is mostly used as a differentiator between a generic exception and an error that has occurred when attempting to
 * communicate with the server.
 *
 * The retry logic uses this to retry on network errors.
 */
public class NetworkException extends Exception
{
    /**
     * Serialization versioning constant.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param message
     *            The HTTP message that was received.
     */
    public NetworkException(String message)
    {
        super(message);
    }

    /**
     * Constructor
     * 
     * @param message
     *            The HTTP message that was received.
     * 
     * @param cause
     *            The exception which caused this exception.
     */
    public NetworkException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
