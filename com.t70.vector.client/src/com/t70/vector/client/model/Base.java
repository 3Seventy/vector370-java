package com.t70.vector.client.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Base for (almost) all model classes.
 * 
 * @author Bryce Simonds
 */
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Base
{
    /**
     * Primary key for the model.
     */
    @JsonProperty("Id")
    private int m_id;

    /**
     * Gets the primary key for the model.
     */
    public int getId()
    {
        return m_id;
    }

    /**
     * Sets the primary key for the model.
     */
    public void setId(int id)
    {
        m_id = id;
    }
}
