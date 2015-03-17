package com.t70.vector.client.model;

import java.util.List;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.t70.vector.client.model.SoftDeletable;

/**
 * Contact details
 * 
 * <p>
 * A contact is a unique phone number and/or email address per account.
 * </p>
 * 
 * <p>
 * Note that either a {@link Contact#setPhoneNumber(String) phone number} or {@link Contact#setEmail(String) email
 * address} are required. You may have both, but you must have at least one of these two.
 * </p>
 * 
 * @see ContactAttribute
 * @see ContactSubscription
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class Contact extends SoftDeletable
{
    /**
     * The {@link Account} that this contact belongs to.
     */
    @JsonProperty("AccountId")
    private Integer m_accountId;

    /**
     * Mobile number if available.
     * 
     * <p>
     * Either this or the {@link Contact#m_email m_email} field are <em>REQUIRED</em>.
     * </p>
     */
    @JsonProperty("PhoneNumber")
    private String m_phoneNumber;

    /**
     * Email address of the contact.
     * 
     * <p>
     * Either this or the {@link Contact#m_phoneNumber m_phoneNumber} field are <em>REQUIRED</em>.
     * </p>
     */
    @JsonProperty("Email")
    private String m_email;

    /**
     * List of attributes set on this contact
     */
    @JsonUnwrapped
    @JsonProperty("Attributes")
    private List<ContactAttribute> m_attributes = new ArrayList<ContactAttribute>();

    /**
     * List of subscriptions details for this contact.
     */
    @JsonUnwrapped
    @JsonProperty("Subscriptions")
    private List<ContactSubscription> m_subscriptions = new ArrayList<ContactSubscription>();

    /**
     * Gets the {@link Account} ID that this contact belongs to.
     */
    public int getAccountId()
    {
        return m_accountId;
    }

    /**
     * Sets the {@link Account} ID that this contact belongs to.
     */
    public void setAccountId(int accountId)
    {
        m_accountId = accountId;
    }

    /**
     * Gets the mobile number if available.
     * 
     * <p>
     * Either this or the {@link Contact#getEmail() email} field are <em>REQUIRED</em>.
     * </p>
     */
    public String getPhoneNumber()
    {
        return m_phoneNumber;
    }

    /**
     * Sets the mobile number if available.
     * 
     * <p>
     * Either this or the {@link Contact#setEmail(String) email} field are <em>REQUIRED</em>.
     * </p>
     * 
     * <p>
     * Note that contact phone numbers must have the dialing prefix on them to be valid. Phone number validity is
     * checked according to the supplied country code. The currently supported country codes are:
     * <ul>
     * <li>{@code +1} -- United States and Canada</li>
     * <li>{@code +44} -- United Kingdom</li>
     * <li>{@code +52} -- Mexico</li>
     * <li>{@code +86} -- China (main land)</li>
     * <li>{@code +852} -- China (Hong Kong)</li>
     * <li>{@code +853} -- China (Macau)</li>
     * <li>{@code +886} -- China (Taiwan)</li>
     * </ul>
     * </p>
     * 
     * <p>
     * Valid Example: {@code +12215550100}
     * </p>
     * <p>
     * While the area code 221 (as of this writing) is not currently in use, it follows the NANP rules for a valid area
     * code.
     * </p>
     * 
     * <p>
     * Invalid Examples:
     * <ul>
     * <li>{@code +11234560100} -- Area codes cannot start with 0 or 1</li>
     * <li>{@code +12210550100} -- Exchange codes cannot start with 0 or 1</li>
     * <li>{@code +15129110000} -- Exchange and area codes cannot have the form of X11</li>
     * <li>{@code +18005550100} -- Toll free numbers are not allowed.</li>
     * <li>{@code +19005550100} -- Toll numbers are not allowed.</li>
     * </ul>
     * </p>
     * 
     * @param phoneNumber
     *            The new phone number for the contact.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        m_phoneNumber = phoneNumber;
    }

    /**
     * Gets the email address of the contact.
     * 
     * <p>
     * Either this or the {@link Contact#getPhoneNumber() phone number} field are <em>REQUIRED</em>.
     * </p>
     */
    public String getEmail()
    {
        return m_email;
    }

    /**
     * Sets the email address for the contact.
     * 
     * <p>
     * Either this or the {@link Contact#setPhoneNumber(String) phone number} field are <em>REQUIRED</em>.
     * </p>
     * 
     * @param email
     *            The new email address for the contact.
     */
    public void setEmail(String email)
    {
        m_email = email;
    }

    /**
     * Gets the list of attributes set on this contact.
     * 
     * <p>
     * You can use contact attributes to set pieces of tagged data on each contact. These data can then be used in
     * contact lists to help filter your contacts to a select group on large pushes.
     * </p>
     * 
     * <p>
     * Currently the SDK does not support defining these attributes, but you can use the Vector Portal or the API to do
     * this. It is a one time setup, and once defined the attribute does not need to be defined again.
     * </p>
     * 
     * @see ContactAttribute
     */
    public List<ContactAttribute> getAttributes()
    {
        return m_attributes;
    }

    /**
     * Sets a list of attributes to modify for this contact.
     * 
     * <p>
     * You can use contact attributes to set pieces of tagged data on each contact. These data can then be used in
     * contact lists to help filter your contacts to a select group on large pushes.
     * </p>
     * 
     * <p>
     * Currently the SDK does not support defining these attributes, but you can use the Vector Portal or the API to do
     * this. It is a one time setup, and once defined the attribute does not need to be defined again.
     * </p>
     * 
     * @see ContactAttribute
     */
    public void setAttributes(List<ContactAttribute> attributes)
    {
        m_attributes = (attributes == null) ? new ArrayList<ContactAttribute>() : attributes;
    }

    /**
     * Gets the list of subscriptions details for this contact.
     * 
     * <p>
     * The contact may be opted out, but still listed here. There are various enabled fields which detail how the
     * contact wishes to receive messages.
     * </p>
     * 
     * @see ContactSubscription
     */
    public List<ContactSubscription> getSubscriptions()
    {
        return m_subscriptions;
    }

    /**
     * Allows for the modification of contact subscription details.
     * 
     * <p>
     * The contact may be opted out, but still listed here. There are various enabled fields which detail how the
     * contact wishes to receive messages.
     * </p>
     * 
     * @see ContactSubscription
     */
    public void setSubscriptions(List<ContactSubscription> subscriptions)
    {
        m_subscriptions = (subscriptions == null) ? new ArrayList<ContactSubscription>() : subscriptions;
    }
}
