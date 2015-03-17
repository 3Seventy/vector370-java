package com.t70.vector.client.constant;

/**
 * Enumeration for identifying the type of campaign we are working with.
 * 
 * @author Bryce Simonds
 */
public enum CampaignType
{
    /**
     * A campaign with no content for pushing out unformatted messages.
     * 
     * <p>
     * Gateway {@link Campaign Campaigns} do not have content.
     * </p>
     * <p>
     * If pushed, then the {@link com.t70.vector.client.model.EventPushCampaign EventPushCampaign} must contain a
     * {@link com.t70.vector.client.model.EventPushCampaign#setMessage message} in order to be successful.
     * </p>
     */
    GATEWAY(0),

    /**
     * A campaign with preformatted content.
     */
    BASIC(1),

    /**
     * A campaign which contains a group of question campaigns.
     * 
     * <p>
     * Dialog {@link Campaign Campaigns} do not have content in of themselves. Their content comes from the linked
     * {@link CampaignType#QUESTION QUESTION} campaigns.
     * </p>
     * <p>
     * TODO: Not supported by this SDK yet.
     * </p>
     */
    DIALOG(2),

    /**
     * A campaign that asks a single question in a dialog.
     * 
     * <p>
     * Question {@link Campaign Campaigns} hold the actual content for {@link CampaignType#DIALOG DIALOGs}.
     * </p>
     * <p>
     * These are chained together to for a series of questions that will get sent to the contact.
     * </p>
     * <p>
     * TODO: Not supported by this SDK yet.
     * </p>
     */
    QUESTION(3),

    /**
     * A campaign which sends a coupon code to a customer.
     */
    COUPON(4),

    /**
     * Reserved, do not use.
     */
    SWEAPSTAKES(5);

    private Integer m_campaignType;

    CampaignType(int campaignType)
    {
        m_campaignType = campaignType;
    }

    public int getValue()
    {
        return m_campaignType;
    }

    public static CampaignType campaignType(int campaignType)
    {
        CampaignType type = null;

        switch (campaignType)
        {
        case 0:
            type = CampaignType.valueOf("GATEWAY");
            break;

        case 1:
            type = CampaignType.valueOf("BASIC");
            break;

        case 2:
            type = CampaignType.valueOf("DIALOG");
            break;

        case 3:
            type = CampaignType.valueOf("QUESTION");
            break;

        case 4:
            type = CampaignType.valueOf("COUPON");
            break;

        case 5:
            type = CampaignType.valueOf("SWEAPSTAKES");
            break;
        }

        return type;
    }
}
