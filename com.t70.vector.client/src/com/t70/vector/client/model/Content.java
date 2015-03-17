package com.t70.vector.client.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.t70.vector.client.constant.ChannelType;
import com.t70.vector.client.constant.LanguageType;
import com.t70.vector.client.model.BaseAudited;

/**
 * Provides a container for templates.
 * 
 * <p>
 * Content objects group related {@link ContentTemplate templates}. This is done to allow for a campaign to hold
 * different templates for different types of messages. (i.e.: SMS vs. MMS vs. Email) They can also hold the same
 * message translated into different languages. This allows for a single campaign push to support multiple different
 * languages, and target types with minimal fuss.
 * </p>
 * 
 * <p>
 * <strong>IMPORTANT NOTE:</strong> There is no requirement to have more than one template under a content object.
 * However you must have at least one template and it must be part of a content group.
 * </p>
 * 
 * <p>
 * Additionally, you may only have one template per type per language. For example, you may have a template for
 * {@link ChannelType#SMS SMS}/{@link LanguageType#ENGLISH ENGLISH} and another template for {@link ChannelType#SMS SMS}/{@link LanguageType#FRENCH FRENCH}, but not two templates with {@link ChannelType#SMS SMS}/
 * {@link LanguageType#ENGLISH ENGLISH}.
 * </p>
 * 
 * @see ChannelType
 * @see LanguageType
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class Content extends BaseAudited
{
    @JsonProperty("AccountId")
    private Integer m_accountId;

    @JsonProperty("Name")
    private String m_name;

    @JsonProperty("Description")
    private String m_description;

    @JsonIgnore
    @JsonProperty("Templates")
    private List<ContentTemplate> m_templates = new ArrayList<ContentTemplate>();

    /**
     * Gets the {@link Account} ID which owns this content object.
     */
    public Integer getAccountId()
    {
        return m_accountId;
    }

    /**
     * Sets the {@link Account} ID which owns this content object.
     * 
     * @param accountId
     *            The new account ID to use for this object.
     */
    public void setAccountId(Integer accountId)
    {
        m_accountId = accountId;
    }

    /**
     * Gets the name of this content object.
     * <p>
     * This is a free form name you can use to provide a human readable identifier to each object
     * </p>
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Sets the name of this content object.
     * 
     * @param name
     *            The new name for the content object.
     */
    public void setName(String name)
    {
        m_name = name;
    }

    /**
     * Gets the free form description field.
     */
    public String getDescription()
    {
        return m_description;
    }

    /**
     * Sets the free form description fields.
     * <p>
     * The free form description field allows you to set a human readable detailed description of the object.
     * </p>
     * 
     * @param description
     *            The new description of the object.
     */
    public void setDescription(String description)
    {
        m_description = description;
    }

    /**
     * Gets a list of templates that are under this content object.
     */
    public List<ContentTemplate> getTemplates()
    {
        return m_templates;
    }

    /**
     * Sets the list of templates.
     * 
     * <p>
     * If the list is set to {@code null} or is an empty list then no adjustments to existing content objects will be
     * made. However you can adjust templates and save them in one call here.
     * </p>
     * 
     * @param templates
     *            The list of templates that should be added or updated.
     */
    public void setTemplates(List<ContentTemplate> templates)
    {
        m_templates = (templates == null) ? new ArrayList<ContentTemplate>() : templates;
    }

}
