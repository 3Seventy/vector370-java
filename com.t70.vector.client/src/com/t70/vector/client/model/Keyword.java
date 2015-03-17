package com.t70.vector.client.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.t70.vector.client.constant.ResourceStatus;

/**
 * A reserved keyword.
 * <p>
 * Keywords provide a way for contacts to text into the system to initiate a campaign.
 * </p>
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class Keyword extends SoftDeletable // TODO: Is this really a SoftDeletable?
{
    /**
     * The account which owns this keyword.
     */
    @JsonProperty("AccountId")
    private Integer m_accountId;

    /**
     * The channel this keyword is reserved on.
     */
    @JsonProperty("channel_id")
    private Integer m_channelId;

    /**
     * The campaign this keyword is currently attached to.
     * <p>
     * If this is NULL then the keyword is not attached to any campaign and will not generate a response.
     */
    @JsonProperty("campaign_id")
    private Integer m_campaignId;

    /**
     * The keyword name to reserve.
     * <p>
     * Keywords cannot contain spaces. Keywords are shared across a channel, so if someone else has a keyword of the
     * same name you will have to select a different keyword or use a different channel where it is not already
     * reserved.
     */
    @JsonProperty("name")
    private String m_name;

    /**
     * HACK: Mapping "status" to "StatusId".
     */
    @JsonProperty("Status")
    private Integer m_status;

    /**
     * Set if a callback should be generated on this keyword.
     */
    @JsonProperty("callback_required")
    private boolean m_callbackRequired;

    /**
     * Gets the key word status.
     *
     * @return the key word status
     */
    public ResourceStatus getStatus()
    {
        return ResourceStatus.resourceStatus(m_status);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name)
    {
        m_name = name;
    }

    /**
     * Gets the account id.
     *
     * @return the account id
     */
    public Integer getAccountId()
    {
        return m_accountId;
    }

    /**
     * Sets the account id.
     *
     * @param accountId
     *            the new account id
     */
    public void setAccountId(Integer accountId)
    {
        m_accountId = accountId;
    }

    /**
     * Gets the channel id.
     *
     * @return the channel id
     */
    public Integer getChannelId()
    {
        return m_channelId;
    }

    /**
     * Sets the channel id.
     *
     * @param channelId
     *            the new channel id
     */
    public void setChannelId(Integer channelId)
    {
        m_channelId = channelId;
    }

    /**
     * Gets the campaign_id.
     *
     * @return the campaign_id
     */
    public Integer getCampaignId()
    {
        return m_campaignId;
    }

    /**
     * Sets the campaign_id.
     *
     * @param campaign_id
     *            the new campaign_id
     */
    public void setCampaignId(Integer campaign_id)
    {
        m_campaignId = campaign_id;
    }

    /**
     * Checks if is callback required.
     *
     * @return true, if is callback required
     */
    public boolean isCallbackRequired()
    {
        return m_callbackRequired;
    }

    /**
     * Sets the callback required.
     *
     * @param callbackRequired
     *            the new callback required
     */
    public void setCallbackRequired(boolean callbackRequired)
    {
        m_callbackRequired = callbackRequired;
    }
}
