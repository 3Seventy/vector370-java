package com.t70.vector.client.constant;

/**
 * The type of subscription, either one-time or recurring.
 * <p>
 * <strong>Important Note:</strong> Setting the correct subscription type is important for following correct CTIA
 * compliance guidelines.
 * </p>
 * 
 * @see com.t70.vector.client.model.ContactSubscription ContactSubscription
 * @see com.t70.vector.client.model.Subscription Subscription
 * 
 * @author Bryce Simonds
 */
public enum SubscriptionType
{
    /**
     * Recurring subscriptions are for situations where multiple messages are going to be sent to a contact on an
     * ongoing basis. Most types of subscriptions will fall into this category. A good example is for a marketing
     * campaign where events and coupons might be sent out to a list of interested customers.
     */
    RECURRING(1),

    /**
     * One time subscriptions are for one off messages where the intention is to send a single message to that customer
     * once. A good example would be to notify specific people they have a package waiting in an office, or that their
     * scheduled service call will be arriving shortly.
     */
    ONE_TIME(2);

    private int m_subscriptionType;

    private SubscriptionType(int subscriptionType)
    {
        m_subscriptionType = subscriptionType;
    }

    public int getValue()
    {
        return m_subscriptionType;
    }

    public static SubscriptionType subscriptionType(int subscriptionType)
    {
        SubscriptionType type = null;

        switch (subscriptionType)
        {
        case 1:
            SubscriptionType.valueOf("RECURRING");
            break;

        case 2:
            SubscriptionType.valueOf("ONE_TIME");
            break;
        }

        return type;
    }

}
