package com.t70.vector.client.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

// TODO: Add contact attribute definition support.

/**
 * User defined attribute data.
 * <p>
 * Contact attributes provide a way to save data to contacts via the API or a
 * {@link com.t70.vector.client.constant.CampaignType#DIALOG DIALOG} campaign.
 * </p>
 * <p>
 * Attributes must be defined before they can be used. The SDK does not currently support defining these attributes
 * directly. However you can do so using either the Vector Portal or making direct API calls. This only needs to be done
 * once per attribute definition.
 * </p>
 * <p>
 * The name of the attribute must be unique, and must follow standard programming naming conventions (must start with an
 * underscore or letter, followed by letters, numbers, and underscores).
 * </p>
 * <p>
 * Child accounts inherit those attributes defined by their parent.
 * </p>
 * 
 * @see Contact
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ContactAttribute
{
    @JsonProperty("Name")
    private String m_name;

    @JsonProperty("Value")
    private String m_value;

    /**
     * Gets the attribute definition name that is set.
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Sets the name of the attribute definition to use.
     *
     * @param name
     *            The attribute definition name to use.
     */
    public void setName(String name)
    {
        m_name = name;
    }

    /**
     * Gets the value set for this contact on this attribute definition name.
     */
    public String getValue()
    {
        return m_value;
    }

    /**
     * Sets the value for this contact on this attribute definition name..
     *
     * @param value
     *            The new value to set.
     */
    public void setValue(String value)
    {
        m_value = value;
    }

}
