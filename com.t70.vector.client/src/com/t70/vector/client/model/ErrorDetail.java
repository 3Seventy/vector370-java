package com.t70.vector.client.model;

import com.t70.vector.client.constant.*;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Details of a remote error
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ErrorDetail
{
    @JsonProperty("ErrorCodeId")
    private int m_errorCodeId;

    @JsonProperty("ErrorMessage")
    private String m_errorMessage;

    @JsonProperty("ObjectType")
    private String m_objectType;

    @JsonProperty("PropertyName")
    private String m_propertyName;

    /**
     * Gets a generic error code for looking up the details.
     * <p>
     * This error code is useful for performing i18n operations, or performing error specific actions if need be.
     * </p>
     * <p>
     * Example: <code>PROPERTY_REQUIRED (0x02000000)</code>
     * </p>
     */
    public ErrorCode getErrorCode()
    {
        return ErrorCode.fromInteger(m_errorCodeId);
    }

    /**
     * Gets a basic error message to use if looking up by error code is not possible.
     * <p>
     * Example: <code>"The Name property is required"</code>
     * </p>
     */
    public String getErrorMessage()
    {
        return m_errorMessage;
    }

    /**
     * Gets a string representing the type of object that failed validation.
     * <p>
     * Example: <code>"Account"</code>
     * </p>
     */
    public String getObjectType()
    {
        return m_objectType;
    }

    /**
     * Returns the specific property that failed the validation.
     * <p>
     * Example: <code>"Name"</code>
     * </p>
     */
    public String getPropertyName()
    {
        return m_propertyName;
    }

    /**
     * Sets the error code.
     */
    public void setErrorCode(ErrorCode errorCode)
    {
        m_errorCodeId = errorCode.getValue();
    }

    /**
     * Sets the error message.
     */
    public void setErrorMessage(String errorMessage)
    {
        m_errorMessage = errorMessage;
    }

    /**
     * Sets the object type.
     */
    public void setObjectType(String objectType)
    {
        m_objectType = objectType;
    }

    /**
     * Sets the property name.
     */
    public void setPropertyName(String propertyName)
    {
        m_propertyName = propertyName;
    }
}
