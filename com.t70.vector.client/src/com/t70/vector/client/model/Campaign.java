package com.t70.vector.client.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.t70.vector.client.constant.*;

/**
 * Details of a campaign that are sent to a contact.
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class Campaign extends SoftDeletable
{
    /**
     * The account ID to which the campaign belongs.
     */
    @JsonProperty("AccountId")
    private Integer m_accountId;

    /**
     * The ID of the subscription that contacts will be opted into.
     */
    @JsonProperty("SubscriptionId")
    private Integer m_subscriptionId;

    /**
     * The campaign's name.
     * 
     * <p>
     * This is a free form name.
     * </p>
     */
    @JsonProperty("Name")
    private String m_name;

    /**
     * The type of campaign.
     * 
     * @see CampaignType
     */
    @JsonProperty("CampaignTypeId")
    private int m_campaignTypeId;

    /**
     * The ID of the {@link Content} that this campaign sends.
     * 
     * <p>
     * Not used for {@link CampaignType#GATEWAY GATEWAY} and {@link CampaignType#DIALOG DIALOG} campaign types.
     * </p>
     */
    @JsonProperty("ContentId")
    private Integer m_contentId;

    /**
     * Indicates if this campaign will start a new session.
     * 
     * <p>
     * If set then when the contact texts into an attached keyword or the campaign is pushed to a contact they are
     * placed into a session. This is used by dialog campaigns to manage responses without colliding with reserved
     * keywords.
     * </p>
     * 
     * <p>
     * Currently this value cannot be customized.
     * </p>
     * 
     * @see m_sessionLength
     */
    @JsonProperty("Session")
    private boolean m_session;

    /**
     * The duration of sessions in milliseconds from start.
     * 
     * <p>
     * Currently this value cannot be customized.
     * </p>
     * 
     * @see m_session
     */
    @JsonProperty("SessionLength")
    private String m_sessionLength;

    /**
     * Arbitrary user data field.
     * 
     * <p>
     * This an area to store free form data.
     * </p>
     * 
     * <p>
     * The Vector Portal uses this field to store some UI hints.
     * </p>
     */
    @JsonProperty("UserData")
    private String m_userData;

    /**
     * Indicates if the campaign is a one time use campaign.
     * 
     * <p>
     * Single use campaigns can only be sent to a contact once. If the campaign is pushed to a contact more than once
     * then nothing is sent to that contact.
     * </p>
     * <p>
     * If the contact texts into a keyword that is attached to a single use campaign then they are sent the contents of
     * the {@link Campaign#m_singleUseContentId m_singleUseContentId} value.
     * </p>
     */
    @JsonProperty("SingleUse")
    private boolean m_singleUse;

    /**
     * This is the ID of the {@link Content} to send if a contact texts into a single use campaign more than once.
     * 
     * <p>
     * This field is only valid for campaigns marked as {@link Campaign#m_singleUse m_singleUse}.
     * </p>
     * <p>
     * This content is only sent if a contact texts into a keyword attached to the single use campaign. If the campaign
     * is pushed to the contact then nothing is sent to them.
     * </p>
     */
    @JsonProperty("SingleUseContentId")
    private Integer m_singleUseContentId;

    /**
     * Gets the account ID to which the campaign belongs.
     */
    public int getAccountId()
    {
        return m_accountId;
    }

    /**
     * Sets the account ID to which the campaign belongs.
     */
    public void setAccountId(int accountId)
    {
        m_accountId = accountId;
    }

    /**
     * Get the ID of the {@link Subscription} that contacts will be opted into.
     */
    public Integer getSubscriptionId()
    {
        return m_subscriptionId;
    }

    /**
     * Sets the ID of the {@link Subscription} that contacts will be opted into.
     */
    public void setSubscriptionId(Integer subscriptionId)
    {
        m_subscriptionId = subscriptionId;
    }

    /**
     * Gets the campaign's name.
     * 
     * <p>
     * This is a free form name.
     * </p>
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Sets the campaign's name.
     * 
     * <p>
     * This is a free form name.
     * </p>
     * 
     * @param name
     *            The new name for the campaign.
     */
    public void setName(String name)
    {
        m_name = name;
    }

    /**
     * Gets the type ID of the campaign.
     * 
     * @see Campaign#getCampaignType() getCampaignType()
     * @see CampaignType
     */
    public int getCampaignTypeId()
    {
        return m_campaignTypeId;
    }

    /**
     * Sets the type ID of the campaign.
     * 
     * @see Campaign#setCampaignType(CampaignType) setCampaignType(CampaignType)
     * @see CampaignType
     */
    public void setCampaignTypeId(int campaignTypeId)
    {
        m_campaignTypeId = campaignTypeId;
    }

    /**
     * Gets the ID of the {@link Content} that this campaign sends.
     * 
     * <p>
     * Not used for {@link CampaignType#GATEWAY GATEWAY} and {@link CampaignType#DIALOG DIALOG} campaign types.
     * </p>
     */
    public Integer getContentId()
    {
        return m_contentId;
    }

    /**
     * Sets the ID of the {@link Content} that this campaign sends.
     * 
     * <p>
     * Not used for {@link CampaignType#GATEWAY GATEWAY} and {@link CampaignType#DIALOG DIALOG} campaign types.
     * </p>
     * 
     * @param contentId
     *            The new content ID to use.
     */
    public void setContentId(Integer contentId)
    {
        m_contentId = contentId;
    }

    /**
     * Gets the flag indicating if this campaign will start a new session.
     * 
     * <p>
     * If set then when the contact texts into an attached keyword or the campaign is pushed to a contact they are
     * placed into a session. This is used by dialog campaigns to manage responses without colliding with reserved
     * keywords.
     * </p>
     * 
     * <p>
     * Currently this value cannot be customized.
     * </p>
     * 
     * @see Campaign#getSessionLength() getSessionLength()
     */
    public boolean getSession()
    {
        return m_session;
    }

    /**
     * Gets the duration of sessions in milliseconds from start.
     * 
     * <p>
     * Currently this value cannot be customized.
     * </p>
     * 
     * @see Campaign#getSession() getSession()
     */
    public String getSessionLength()
    {
        return m_sessionLength;
    }

    /**
     * Gets the arbitrary user data field.
     * 
     * <p>
     * This an area to store free form data.
     * </p>
     * 
     * <p>
     * The Vector Portal uses this field to store some UI hints.
     * </p>
     */
    public String getUserData()
    {
        return m_userData;
    }

    /**
     * Sets the arbitrary user data field.
     * 
     * <p>
     * This an area to store free form data.
     * </p>
     * 
     * <p>
     * The Vector Portal uses this field to store some UI hints.
     * </p>
     * 
     * @param userData
     *            The new user data value to store.
     */
    public void setUserData(String userData)
    {
        m_userData = userData;
    }

    /**
     * Gets if this campaign is a one time use campaign.
     * 
     * <p>
     * Single use campaigns can only be sent to a contact once. If the campaign is pushed to a contact more than once
     * then nothing is sent to that contact.
     * </p>
     * <p>
     * If the contact texts into a keyword that is attached to a single use campaign then they are sent the contents of
     * the {@link Campaign#getSingleUseContentId() SingleUseContentId} value.
     * </p>
     */
    public boolean isSingleUse()
    {
        return m_singleUse;
    }

    /**
     * Sets a value indicating if this campaign is a one time use campaign or not.
     * 
     * <p>
     * Single use campaigns can only be sent to a contact once. If the campaign is pushed to a contact more than once
     * then nothing is sent to that contact.
     * </p>
     * <p>
     * If the contact texts into a keyword that is attached to a single use campaign then they are sent the contents of
     * the {@link Campaign#getSingleUseContentId() SingleUseContentId} value.
     * </p>
     */
    public void setSingleUse(boolean singleUse)
    {
        m_singleUse = singleUse;
    }

    /**
     * Gets the ID of the {@link Content} to send if a contact texts into a single use campaign more than once.
     * 
     * <p>
     * This field is only valid for campaigns marked as {@link Campaign#setSingleUse() SingleUse}.
     * </p>
     * <p>
     * This content is only sent if a contact texts into a keyword attached to the single use campaign. If the campaign
     * is pushed to the contact then nothing is sent to them.
     * </p>
     */
    public Integer getSingleUseContentId()
    {
        return m_singleUseContentId;
    }

    /**
     * Sets the ID of the {@link Content} to send if a contact texts into a single use campaign more than once.
     * 
     * <p>
     * This field is only valid for campaigns marked as {@link Campaign#setSingleUse() SingleUse}.
     * </p>
     * <p>
     * This content is only sent if a contact texts into a keyword attached to the single use campaign. If the campaign
     * is pushed to the contact then nothing is sent to them.
     * </p>
     * 
     * @param singleUseContentId
     *            The ID of the {@link Content} to send.
     */
    public void setSingleUseContentId(Integer singleUseContentId)
    {
        m_singleUseContentId = singleUseContentId;
    }

    /**
     * Gets the type of the campaign.
     * <p>
     * This is an enumeration alias for {@link Campaign#getCampaignTypeId() getCampaignTypeId()}
     * </p>
     * 
     * @see CampaignType
     */
    public CampaignType getCampaignType()
    {
        return CampaignType.campaignType(m_campaignTypeId);
    }

    /**
     * Sets the type of the campaign.
     * <p>
     * This is an enumeration alias for {@link Campaign#setCampaignTypeId(int) setCampaignTypeId(int)}
     * </p>
     * 
     * @see CampaignType
     */
    public void setCampaignType(CampaignType campaignType)
    {
        m_campaignTypeId = campaignType.getValue();
    }

}
