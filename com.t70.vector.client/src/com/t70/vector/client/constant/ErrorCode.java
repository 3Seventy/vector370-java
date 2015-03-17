package com.t70.vector.client.constant;

public enum ErrorCode
{
    // <editor-fold defaultstate="collapsed" desc="Internal Logic Errors">

    /*
     * The first block of 256 values (0x00000000 - 0x000000FF) are reserved for these types of errors.
     */

    /**
     * There was some sort of unknown error.
     */
    UNKNOWN_ERROR(0x00000000),

    /**
     * The error code was not set on the validation error.
     */
    UNSET_ERROR_CODE(0x00000001),

    // </editor-fold>

    // <editor-fold default-state="collapsed" desc="Object Level Error Codes">

    /*
     * These errors detail issues on a single object. (E.g. object not found etc.)
     * 
     * The block of number from 256 to 4095 (0x00000100 - 0x00000FFF) are reserved for these types of errors.
     */

    /**
     * The user does not have access to the object specified.
     * 
     * This error code was chosen to match that of the HTTP status code.
     */
    FORBIDDEN(403),

    /**
     * The object was not found.
     * 
     * This error code was chosen to match that of the HTTP status code.
     */
    OBJECT_NOT_FOUND(404),

    // <editor-fold>

    // <editor-fold default-state="collapse" desc="Generic Property Errors">

    /*
     * These errors can be combined with specific errors to provide some additional details for trying to hunt for a
     * proper response to send to the user or to allow for customer code to react to the issue in some generic but known
     * way.
     * 
     * We reserve the upper byte of the 32-bit integer space for these errors: 0x01000000 - 0xFF000000
     */

    /**
     * The supplied value cannot be reused.
     */
    PROPERTY_ALREADY_IN_USE(0x01000000),

    /**
     * The property was missing and is required.
     */
    PROPERTY_REQUIRED(0x02000000),

    /**
     * The property exceeds some maximum.
     */
    PROPERTY_TOO_LARGE(0x03000000),

    /**
     * The property exceeds some minimum.
     */
    PROPERTY_TOO_SMALL(0x04000000),

    /**
     * The value of the property is out of range of a specific limit.
     */
    PROPERTY_OUT_OF_RANGE(0x05000000),

    /**
     * The value for the property is invalid.
     * <p>
     * Example: <code>Invalid email address</code>
     * </p>
     */
    PROPERTY_INVALID_VALUE(0x06000000),

    // <editor-fold default-state="collapse" desc="Specific Errors">

    /*
     * For errors that are very specific that cannot be expressed with the generic errors above.
     * 
     * The reserved range is from 0x00001000 - 0x00FFFFFF
     */

    // </editor-fold>

    // </editor-fold>

    ;

    private Integer m_value;

    private ErrorCode(int value)
    {
        m_value = value;
    }

    public int getValue()
    {
        return m_value;
    }

    public static ErrorCode fromInteger(int errorCodeId)
    {
        switch (errorCodeId)
        {
        // Internal error mappings

        case 0x00000000:
            return ErrorCode.UNKNOWN_ERROR;

        case 0x00000001:
            return ErrorCode.UNSET_ERROR_CODE;

            // Object level error mappings

        case 403:
            return ErrorCode.FORBIDDEN;

        case 404:
            return ErrorCode.OBJECT_NOT_FOUND;

            // Property level error mappings.

        case 0x01000000:
            return ErrorCode.PROPERTY_ALREADY_IN_USE;

        case 0x02000000:
            return ErrorCode.PROPERTY_REQUIRED;

        case 0x03000000:
            return ErrorCode.PROPERTY_TOO_LARGE;

        case 0x04000000:
            return ErrorCode.PROPERTY_TOO_SMALL;

        case 0x05000000:
            return ErrorCode.PROPERTY_OUT_OF_RANGE;

        case 0x06000000:
            return ErrorCode.PROPERTY_INVALID_VALUE;

        default:
            return ErrorCode.UNKNOWN_ERROR;
        }
    }
}
