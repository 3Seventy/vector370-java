package com.t70.vector.client.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.t70.vector.client.constant.ChannelType;

/**
 * A channel that messages are sent out on.
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class Channel extends Base
{
    @JsonProperty("type")
    private int m_typeId;

    @JsonProperty("Name")
    private String m_name;

    @JsonProperty("Label")
    private String m_label;

    @JsonProperty("Description")
    private String m_description;

    @JsonProperty("DefaultLanguageId")
    private int m_defaultLanguageId;

    @JsonProperty("IsActive")
    private boolean m_isActive;

    @JsonProperty("OverrideGroup")
    private int m_overrideGroup;

    /**
     * Gets the channel type.
     *
     * @return the channel type
     */
    public int getTypeId()
    {
        return m_typeId;
    }

    /**
     * Sets the channel type.
     *
     * @param typeId
     *            the new channel type
     */
    public void setChannelType(int typeId)
    {
        m_typeId = typeId;
    }

    /**
     * Gets the channel name.
     *
     * @return the channel name
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Sets the channel name.
     *
     * @param name
     *            the new channel name
     */
    public void setName(String name)
    {
        m_name = name;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return m_description;
    }

    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description)
    {
        m_description = description;
    }

    /**
     * Gets the default language id.
     *
     * @return the default language id
     */
    public int getDefaultLanguageId()
    {
        return m_defaultLanguageId;
    }

    /**
     * Sets the default language id.
     *
     * @param defaultLanguageId
     *            the new default language id
     */
    public void setDefaultLanguageId(int defaultLanguageId)
    {
        m_defaultLanguageId = defaultLanguageId;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive()
    {
        return m_isActive;
    }

    /**
     * Sets the active.
     *
     * @param isActive
     *            the new active
     */
    public void setActive(boolean isActive)
    {
        m_isActive = isActive;
    }

    /**
     * Gets the override group.
     *
     * @return the override group
     */
    public int getOverrideGroup()
    {
        return m_overrideGroup;
    }

    /**
     * Sets the override group.
     *
     * @param overrideGroup
     *            the new override group
     */
    public void setOverrideGroup(int overrideGroup)
    {
        m_overrideGroup = overrideGroup;
    }

    /**
     * Gets the channel type as an enumeration.
     */
    public ChannelType getType()
    {
        return ChannelType.channelType(m_typeId);
    }

    public void setType(ChannelType type)
    {
        m_typeId = type.getValue();
    }
}
