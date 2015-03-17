package com.t70.vector.client.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Base class for objects with audit information.
 * 
 * @author Bryce Simonds
 */
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public class BaseAudited extends Base
{
    /**
     * When the resource was created.
     */
    @JsonProperty("Created")
    private Date m_created;

    /**
     * Who created this object.
     */
    @JsonProperty("CreatedBy")
    private String m_createdBy;

    /**
     * When this object was last modified.
     */
    @JsonProperty("Modified")
    private Date m_modified;

    /**
     * Who last modified this object.
     */
    @JsonProperty("ModifiedBy")
    private String m_modifiedBy;

    /**
     * Gets the date when this resource was created.
     */
    public Date getCreated()
    {
        return m_created;
    }

    /**
     * Gets the date when this resource was last updated.
     */
    public Date getModified()
    {
        return m_modified;
    }

    /**
     * Gets the user who created this resource.
     * <p>
     * This is currently not filled in.
     * </p>
     */
    public String getCreatedBy()
    {
        return m_createdBy;
    }

    /**
     * Gets the user who last modified this resource.
     * <p>
     * This is currently not filled in.
     * </p>
     */
    public String getModifiedBy()
    {
        return m_modifiedBy;
    }
}
