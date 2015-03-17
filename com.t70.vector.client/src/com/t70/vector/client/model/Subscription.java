package com.t70.vector.client.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.t70.vector.client.constant.SubscriptionType;

/**
 * Represents a subscription that a contact can opt into and out of.
 * <p>
 * Subscriptions provide your contacts a way to select what types messages they wish to, or not to, receive. It also
 * allows them to specify how they wish to receive these messages.
 * </p>
 * <p>
 * <strong>Important Note:</strong> Setting the correct subscription type is important for following correct CTIA
 * compliance guidelines.
 * </p>
 * 
 * @see ContactSubscription
 * @see SubscriptionType
 * 
 * @author Bryce Simonds
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class Subscription extends SoftDeletable
{
    @JsonProperty("AccountId")
    private int m_accountId;

    @JsonProperty("Name")
    private String m_name;

    @JsonProperty("Label")
    private String m_label;

    @JsonProperty("SubscriptionTypeId")
    private int m_subscriptionTypeId;

    @JsonProperty("Frequency")
    private int m_frequency;

    /**
     * Constructor
     */
    public Subscription()
    {
        m_frequency = 30; // Default to 30 messages a month.
    }

    /**
     * Gets the account ID which owns this subscription object.
     */
    public int getAccountId()
    {
        return m_accountId;
    }

    /**
     * Sets the account ID which owns this subscription object.
     *
     * @param accountId
     *            The new account ID.
     */
    public void setAccountId(int accountId)
    {
        m_accountId = accountId;
    }

    /**
     * Gets the name of this subscription.
     * 
     * <p>
     * This is the name that is used when asking contacts to select a subscription to opt out of (in the case where they
     * are opted into more than one subscription)
     * </p>
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Sets the name of this subscription.
     * 
     * <p>
     * This is the name that is used when asking contacts to select a subscription to opt out of (in the case where they
     * are opted into more than one subscription)
     * </p>
     *
     * @param name
     *            The new name for the subscription.
     */
    public void setName(String name)
    {
        m_name = name;
    }

    /**
     * Gets the pretty display name for the subscription.
     */
    public String getLabel()
    {
        return m_label;
    }

    /**
     * Sets the pretty display name for the subscription.
     *
     * @param label
     *            The new label for the susbscription.
     */
    public void setLabel(String label)
    {
        m_label = label;
    }

    /**
     *
     * Gets the type ID of subscription, either one-time or recurring.
     * 
     * <p>
     * {@link SubscriptionType#RECURRING RECURRING} subscriptions are for situations where multiple messages are going
     * to be sent to a contact on an ongoing basis. Most types of subscriptions will fall into this category. A good
     * example is for a marketing campaign where events and coupons might be sent out to a list of interested customers.
     * </p>
     * <p>
     * {@link SubscriptionType#ONE_TIME ONE_TIME} subscriptions are for one off messages where the intention is to send
     * a single message to that customer once. A good example would be to notify specific people they have a package
     * waiting in an office, or that their scheduled service call will be arriving shortly.
     * </p>
     *
     * @return the subscription type id
     * 
     * @see Subscription#getSubscriptionType() getSubscriptionType()
     * @see SubscriptionType
     */
    public int getSubscriptionTypeId()
    {
        return m_subscriptionTypeId;
    }

    /**
     * Sets the type ID of subscription, either one-time or recurring.
     * 
     * <p>
     * {@link SubscriptionType#RECURRING RECURRING} subscriptions are for situations where multiple messages are going
     * to be sent to a contact on an ongoing basis. Most types of subscriptions will fall into this category. A good
     * example is for a marketing campaign where events and coupons might be sent out to a list of interested customers.
     * </p>
     * <p>
     * {@link SubscriptionType#ONE_TIME ONE_TIME} subscriptions are for one off messages where the intention is to send
     * a single message to that customer once. A good example would be to notify specific people they have a package
     * waiting in an office, or that their scheduled service call will be arriving shortly.
     * </p>
     *
     * @param subscriptionTypeId
     *            The new subscription type id
     * 
     * @see Subscription#setSubscriptionType(SubscriptionType) setSubscriptionType(SubscriptionType)
     * @see SubscriptionType
     */
    public void setSubscriptionTypeId(int subscriptionTypeId)
    {
        m_subscriptionTypeId = subscriptionTypeId;
    }

    /**
     * Gets the number of messages (on average) per month a contact can expect to get on this subscription.
     * 
     * <p>
     * This value is only relevant to {@link SubscriptionType#RECURRING RECURRING} subscriptions. It is ignored for
     * {@link SubscriptionType#ONE_TIME ONE_TIME} subscriptions. The default is 30 messages per month.
     * </p>
     *
     * @return the frequency
     * 
     * @see SubscriptionType
     */
    public int getFrequency()
    {
        return m_frequency;
    }

    /**
     * Sets the number of messages (on average) per month a contact can expect to get on this subscription.
     * 
     * <p>
     * This value is only relevant to {@link SubscriptionType#RECURRING RECURRING} subscriptions. It is ignored for
     * {@link SubscriptionType#ONE_TIME ONE_TIME} subscriptions. The default is 30 messages per month.
     * </p>
     *
     * @param frequency
     *            The new message frequency.
     * 
     * @see SubscriptionType
     */
    public void setFrequency(int frequency)
    {
        m_frequency = frequency;
    }

    /**
     * Gets the type of subscription, either one-time or recurring.
     * 
     * <p>
     * {@link SubscriptionType#RECURRING RECURRING} subscriptions are for situations where multiple messages are going
     * to be sent to a contact on an ongoing basis. Most types of subscriptions will fall into this category. A good
     * example is for a marketing campaign where events and coupons might be sent out to a list of interested customers.
     * </p>
     * <p>
     * {@link SubscriptionType#ONE_TIME ONE_TIME} subscriptions are for one off messages where the intention is to send
     * a single message to that customer once. A good example would be to notify specific people they have a package
     * waiting in an office, or that their scheduled service call will be arriving shortly.
     * </p>
     * <p>
     * This is an enumeration alias for {@link Subscription#getSubscriptionTypeId() getSubscriptionTypeId()}.
     * </p>
     *
     * @see SubscriptionType
     */
    public SubscriptionType getSubscriptionType()
    {
        return SubscriptionType.subscriptionType(m_subscriptionTypeId);
    }

    /**
     * Sets the type of subscription, either one-time or recurring.
     * 
     * <p>
     * {@link SubscriptionType#RECURRING RECURRING} subscriptions are for situations where multiple messages are going
     * to be sent to a contact on an ongoing basis. Most types of subscriptions will fall into this category. A good
     * example is for a marketing campaign where events and coupons might be sent out to a list of interested customers.
     * </p>
     * <p>
     * {@link SubscriptionType#ONE_TIME ONE_TIME} subscriptions are for one off messages where the intention is to send
     * a single message to that customer once. A good example would be to notify specific people they have a package
     * waiting in an office, or that their scheduled service call will be arriving shortly.
     * </p>
     * <p>
     * This is an enumeration alias for {@link Subscription#setSubscriptionTypeId(int) setSubscriptionTypeId(int)}.
     * </p>
     * 
     * @param subscriptionType
     *            The new subscription type
     * 
     * @see SubscriptionType
     */
    public void setSubscriptionType(SubscriptionType subscriptionType)
    {
        m_subscriptionTypeId = subscriptionType.getValue();
    }
}
