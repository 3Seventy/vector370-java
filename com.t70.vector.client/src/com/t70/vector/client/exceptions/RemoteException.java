package com.t70.vector.client.exceptions;

import java.util.*;

import com.t70.vector.client.model.ErrorDetail;

/**
 * An exception that is returned by the remote server when an error is detected.
 *
 * RemoteException errors are often returned when the server returns a 400 or other HTTP error.
 */
public class RemoteException extends Exception
{
    /**
     * Serialization versioning constant.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The remote HTTP status code we got back.
     */
    private int m_statusCode;

    /**
     * A list of erorrs that were parsed from the server's response.
     */
    private Collection<ErrorDetail> m_errors;

    /**
     * Constructor
     * 
     * @param statusCode
     *            The HTTP status code that was received.
     * 
     * @param errors
     *            The list of remote validation errors received.
     */
    public RemoteException(int statusCode, Collection<ErrorDetail> errors)
    {
        super(GenerateMessage(errors));

        m_statusCode = statusCode;
        m_errors = errors;
    }

    /**
     * Constructor
     *
     * @param statusCode
     *            The HTTP status code that was received.
     * 
     * @param errors
     *            The list of remote validation errors that was received.
     * 
     * @param cause
     *            The exception which caused this exception.
     */
    public RemoteException(int statusCode, Collection<ErrorDetail> errors, Throwable cause)
    {
        super(GenerateMessage(errors), cause);

        m_statusCode = statusCode;
        m_errors = errors;
    }

    /**
     * Returns the HTTP status code that was sent by the server.
     */
    public int getStatusCode()
    {
        return m_statusCode;
    }

    /**
     * Returns the list of errors that were parsed from the server.
     */
    public Collection<ErrorDetail> getErrors()
    {
        return m_errors;
    }

    /**
     * Convert the given errors to a string.
     * 
     * Used for setting the message property of the base Exception class.
     * 
     * @param errors
     *            The list of errors to convert to a string.
     * 
     * @return A string representing the errors.
     */
    private static String GenerateMessage(Collection<ErrorDetail> errors)
    {
        return Arrays.toString(errors.toArray());
    }
}
