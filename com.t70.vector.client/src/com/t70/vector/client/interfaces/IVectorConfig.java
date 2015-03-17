package com.t70.vector.client.interfaces;

/**
 * Interface for mapping to a configuration reader.
 * 
 * @author Bryce Simonds
 */
public interface IVectorConfig
{
    /**
     * Gets the base URL where requests are sent to.
     */
    public String getBaseUrl();

    /**
     * Gets the user name to use in HTTP auth headers.
     */
    public String getUserName();

    /**
     * Gets the password to use in the HTTP auth header.
     */
    public String getPassword();

    /**
     * Gets the string to use for the user agent header.
     */
    public String getUserAgent();

    /**
     * Gets the maximum number of attempts we will make when trying to send a REST request.
     */
    public Integer getMaxTries();

    /**
     * The timeout value in seconds we will wait for a response from the server.
     */
    public Integer getHttpTimeout();
}
