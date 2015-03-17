package com.t70.vector.client.constant;

/**
 * Describes how a member variable is passed on to the HTTP server.
 */
public enum MappingType
{
    /**
     * The parameter is a required part of the URL segment.
     */
    URL_SEGMENT,

    /**
     * Parameter is an optional part of the URL segment.
     */
    OPTIONAL_URL_SEGMENT,

    /**
     * Parameter is a GET value.
     */
    GET,

    /**
     * Parameter appears only in POST/PUT data.
     */
    POST,

    /**
     * Parameter can be set via GET or POST/PUT.
     */
    GET_OR_POST,

    /**
     * Parameter is passed in through via an HTTP cookie value.
     */
    COOKIE,

    /**
     * Parameter is passed in through the HTTP header.
     */
    HEADER;
}
