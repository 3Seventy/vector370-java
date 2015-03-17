package com.t70.vector.client.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.t70.vector.client.constant.ScheduleType;
import com.t70.vector.client.model.BaseAudited;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Used for sending a campaign to a list of contacts. Note that the list of contacts can be specified in a variety of
 * ways: Targets: This is a list of phone numbers and/or email addresses. Contacts: This is a list of contact ids.
 * ContactListId: This is the ID of a contact list which runs on the 3Seventy servers. All three types can be specified
 * and the lists will be merged with duplicate contacts removed.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public final class EventPushCampaign extends BaseAudited
{
    @JsonProperty("AccountId")
    private int m_accountId;

    @JsonProperty("Name")
    private String m_name;

    @JsonProperty("ChannelIds")
    private List<Integer> m_channelIds = new ArrayList<Integer>();

    @JsonProperty("Targets")
    private List<String> m_targets = new ArrayList<String>();

    @JsonProperty("Contacts")
    private List<Integer> m_contacts = new ArrayList<Integer>();

    @JsonProperty("ContactListId")
    private Integer m_contactListId;

    @JsonProperty("CampaignId")
    private int m_campaignId;

    @JsonProperty("From")
    private String m_from;

    @JsonProperty("IgnoreSingleUse")
    private boolean m_ignoreSingleUse;

    @JsonProperty("ForceOptIn")
    private boolean m_forceOptIn;

    @JsonProperty("Message")
    private String m_message;

    @JsonProperty("Subject")
    private String m_subject;

    @JsonProperty("ScheduleTypeId")
    private int m_scheduleTypeId;

    @JsonProperty("Interval")
    private int m_interval;

    @JsonProperty("StartDateTime")
    private Date m_startDateTime;

    @JsonProperty("EndDateTime")
    private Date m_endDateTime;

    @JsonProperty("DayOfWeek")
    private String m_dayOfWeek;

    @JsonProperty("DayOfMonth")
    private String m_dayOfMonth;

    @JsonProperty("Month")
    private String m_month;

    @JsonProperty("Year")
    private String m_year;

    @JsonIgnore
    private ScheduleType scheduleType;

    /**
     * Constructor
     */
    public EventPushCampaign()
    {
        m_scheduleTypeId = ScheduleType.NOW.getValue();
    }

    public int getAccountId()
    {
        return m_accountId;
    }

    public void setAccountId(int accountId)
    {
        m_accountId = accountId;
    }

    /**
     * Sets the channel ID for the event.
     * 
     * @Deprecated This is for backwards compatibility. Use setChannelIds(List<Integer>)
     */
    @Deprecated
    @JsonProperty("ChannelId")
    public void setChannelId(List<Integer> channelId)
    {
        setChannelIds(channelId);
    }

    public List<Integer> getChannelIds()
    {
        return m_channelIds;
    }

    /**
     * Sets the channel IDs list for the event.
     * 
     * @param channelIds
     *            The new list of channel IDs to use.
     */
    public void setChannelIds(List<Integer> channelIds)
    {
        m_channelIds = (channelIds == null) ? new ArrayList<Integer>() : channelIds;
    }

    /**
     * Gets the list of targets that event will be (or was) sent to.
     * 
     * <p>
     * Targets can be a mixture of email addresses, phone numbers, and {@link Contact} ids.
     * </p>
     * 
     * <p>
     * Note that in order for the system to differentiate a phone number from a contact ID, phone numbers must be
     * prefixed with their country dialing code. E.g.: {@code (221) 555-0100} should be listed as {@code +12215550100}
     * </p>
     */
    public List<String> getTargets()
    {
        return m_targets;
    }

    /**
     * Sets the list of targets that event will be (or was) sent to.
     * 
     * <p>
     * Targets can be a mixture of email addresses, phone numbers, and {@link Contact} ids.
     * </p>
     * 
     * <p>
     * Note that in order for the system to differentiate a phone number from a contact ID, phone numbers must be
     * prefixed with their country dialing code. E.g.: {@code (221) 555-0100} should be listed as {@code +12215550100}
     * </p>
     * 
     * @param targets
     *            The list of targets to set.
     */
    public void setTargets(List<String> targets)
    {
        m_targets = (targets == null) ? new ArrayList<String>() : targets;
    }

    /**
     * Gets a list of {@link Contact} IDs this even will be (or was) pushed to.
     * 
     * @deprecated For backwards compatibility, use getTargets() and setTargets()
     */
    @Deprecated
    public List<Integer> getContacts()
    {
        return m_contacts;
    }

    /**
     * Gets the contact list ID to use for getting a list of contacts.
     * 
     * <p>
     * Generation of contact lists are not yet supported by this SDK, but they can be created via raw API calls and
     * using our Portal. If you do create a contact list in this way, you can supply the generated ID here without any
     * issues.
     * </p>
     */
    public Integer getContactListId()
    {
        return m_contactListId;
    }

    /**
     * Sets the contact list ID will be used for getting a list of contacts.
     * 
     * <p>
     * Generation of contact lists are not yet supported by this SDK, but they can be created via raw API calls and
     * using our Portal. If you do create a contact list in this way, you can supply the generated ID here without any
     * issues.
     * </p>
     * 
     * @param contactListId
     *            The new contact list ID
     */
    public void setContactListId(Integer contactListId)
    {
        m_contactListId = contactListId;
    }

    /**
     * Gets the {@link Campaign} ID that will be sent out.
     * 
     * <p>
     * For {@link com.t70.vector.client.constant.CampaignType#GATEWAY GATEWAY} campaigns the
     * {@link EventPushCampaign#setMessage(String) message} field is required.
     * </p>
     */
    public int getCampaignId()
    {
        return m_campaignId;
    }

    /**
     * Sets the {@link Campaign} ID that will be sent out.
     * 
     * <p>
     * For {@link com.t70.vector.client.constant.CampaignType#GATEWAY GATEWAY} campaigns the
     * {@link EventPushCampaign#setMessage(String) message} field is required.
     * </p>
     * 
     * @param campaignId
     *            The campaign ID to set.
     */
    public void setCampaignId(int campaignId)
    {
        m_campaignId = campaignId;
    }

    /**
     * Gets the "from" line used for sending to email channels.
     * 
     * <p>
     * If it is not filled in, a default from field will be used.
     * </p>
     */
    public String getFrom()
    {
        return m_from;
    }

    /**
     * Sets the "from" line to use for sending to email channels.
     * 
     * <p>
     * If it is not filled in, a default from field will be used.
     * </p>
     * 
     * @param from
     *            The value to use in the "from" line.
     */
    public void setFrom(String from)
    {
        m_from = from;
    }

    /**
     * Gets if the event is ignoring a {@link Campaign Campaign's} SingleUse flag.
     *
     * <p>
     * If a campaign is marked as single use, then it will only get sent to a particular contact once and only once.
     * Setting this value will force the message to get sent regardless of the SingleUse flag setting on the campaign.
     * </p>
     * <p>
     * This can be handy if you have a particular contact who did not receive the message and you would like to resend
     * it to them.
     * </p>
     * 
     * @see Campaign#isSingleUse()
     */
    public boolean isIgnoringSingleUse()
    {
        return m_ignoreSingleUse;
    }

    /**
     * Sets if the event should ignore the {@link Campaign Campaign's} SingleUse flag.
     * 
     * <p>
     * If a campaign is marked as single use, then it will only get sent to a particular contact once and only once.
     * Setting this value will force the message to get sent regardless of the SingleUse flag setting on the campaign.
     * </p>
     * <p>
     * This can be handy if you have a particular contact who did not receive the message and you would like to resend
     * it to them.
     * </p>
     * 
     * @see Campaign#isSingleUse()
     * 
     * @param ignoreSingleUse
     *            The new value for IgnoreSingleUse flag.
     */
    public void setIgnoreSingleUse(boolean ignoreSingleUse)
    {
        m_ignoreSingleUse = ignoreSingleUse;
    }

    /**
     * Gets if the event will force a contact to be opted into the {@link Campaign Campaign's} {@link Subscription}.
     */
    public boolean isForcingOptIns()
    {
        return m_forceOptIn;
    }

    /**
     * Sets if the event will force opt ins during the push.
     * 
     * @param forceOptIn
     *            Sets the new ForceOptIn flag.
     */
    public void setForceOptIn(boolean forceOptIn)
    {
        m_forceOptIn = forceOptIn;
    }

    /**
     * Gets the message text that was sent for a {@link com.t70.vector.client.constant.CampaignType#GATEWAY GATEWAY}
     * campaign.
     */
    public String getMessage()
    {
        return m_message;
    }

    /**
     * Sets the message text for use on {@link com.t70.vector.client.constant.CampaignType#GATEWAY GATEWAY} campaigns.
     * 
     * <p>
     * <strong>IMPORTANT:</strong> You cannot send Razor formatted text with this system.
     * </p>
     * 
     * @param message
     *            The text to send.
     */
    public void setMessage(String message)
    {
        m_message = message;
    }

    /**
     * Gets the "Subject" line to use when sending to an email channel.
     * 
     * <p>
     * This field is ignored when being sent to any channel type but
     * {@link com.t70.vector.client.constant.ChannelType#EMAIL EMAIL}.
     * </p>
     * 
     * @return the subject
     */
    public String getSubject()
    {
        return m_subject;
    }

    /**
     * Sets the "Subject" line to use when sending to an email channel.
     * 
     * <p>
     * This field is ignored when being sent to any channel type but
     * {@link com.t70.vector.client.constant.ChannelType#EMAIL EMAIL}.
     * </p>
     * 
     * @param subject
     *            The new "Subject" line to use for emails.
     */
    public void setSubject(String subject)
    {
        m_subject = subject;
    }

    /**
     * Gets the {@link ScheduleType} ID.
     * 
     * <p>
     * Currently the only supported types are: Now, OneTime and Daily.
     * </p>
     * <p>
     * Defaults to {@link ScheduleType#NOW NOW} if not explicitly set.
     * </p>
     * 
     * @see EventPushCampaign#getScheduleType() getScheduleType()
     * @see ScheduleType
     */
    public int getScheduleTypeId()
    {
        return m_scheduleTypeId;
    }

    /**
     * Sets the {@link ScheduleType} ID.
     *
     * <p>
     * Currently the only supported types are: Now, OneTime and Daily.
     * </p>
     * <p>
     * Defaults to {@link ScheduleType#NOW NOW} if not explicitly set.
     * </p>
     * 
     * @see EventPushCampaign#setScheduleType(ScheduleType) setScheduleType(ScheduleType)
     * @see ScheduleType
     */
    public void setScheduleTypeId(int scheduleTypeId)
    {
        m_scheduleTypeId = scheduleTypeId;
    }

    /**
     * Gets how often the event should recur.
     * 
     * <p>
     * The meaning of this value is dependent on the {@link ScheduleType}.
     * 
     * <ul>
     * <li>Seconds: Valid values (0-60)</li>
     * <li>Minutes: Valid values (0-60)</li>
     * <li>Hours: Valid values (0-12)</li>
     * <li>Daily: Interpreted as a bit mask day of the week:
     * <ul>
     * <li>0x00 = Alias for Every Day</li>
     * <li>0x01 = Monday</li>
     * <li>0x02 = Tuesday</li>
     * <li>0x04 = Wednesday</li>
     * <li>0x08 = Thursday</li>
     * <li>0x10 = Friday</li>
     * <li>0x20 = Saturday</li>
     * <li>0x40 = Sunday</li>
     * <li>0x7F = Every day</li>
     * </ul>
     * </ul>
     * </p>
     */
    public int getInterval()
    {
        return m_interval;
    }

    /**
     * Sets how often the event should recur.
     * 
     * <p>
     * The meaning of this value is dependent on the {@link ScheduleType}.
     * 
     * <ul>
     * <li>Seconds: Valid values (0-60)</li>
     * <li>Minutes: Valid values (0-60)</li>
     * <li>Hours: Valid values (0-12)</li>
     * <li>Daily: Interpreted as a bit mask day of the week:
     * <ul>
     * <li>0x00 = Alias for Every Day</li>
     * <li>0x01 = Monday</li>
     * <li>0x02 = Tuesday</li>
     * <li>0x04 = Wednesday</li>
     * <li>0x08 = Thursday</li>
     * <li>0x10 = Friday</li>
     * <li>0x20 = Saturday</li>
     * <li>0x40 = Sunday</li>
     * <li>0x7F = Every day</li>
     * </ul>
     * </ul>
     * </p>
     * 
     * @param interval
     *            The new recurrence frequency value.
     */
    public void setInterval(int interval)
    {
        m_interval = interval;
    }

    /**
     * Gets the date/time when this event should start processing.
     * 
     * <p>
     * This is ignored for {@link ScheduleType#NOW NOW} scheduling.
     * </p>
     */
    public Date getStartDateTime()
    {
        return m_startDateTime;
    }

    /**
     * Sets the date/time when this event should start processing.
     * 
     * <p>
     * This is ignored for {@link ScheduleType#NOW NOW} scheduling.
     * </p>
     * 
     * @param startDateTime
     *            The new start date/time value.
     */
    public void setStartDateTime(Date startDateTime)
    {
        m_startDateTime = startDateTime;
    }

    /**
     * Gets the end date/time for when this event should stop recurring.
     *
     * <p>
     * This is ignored for {@link ScheduleType#NOW NOW} and {@link ScheduleType#ONE_TIME ONE_TIME} scheduling.
     * </p>
     */
    public Date getEndDateTime()
    {
        return m_endDateTime;
    }

    /**
     * Sets the end date/time for when this event should stop recurring.
     *
     * <p>
     * This is ignored for {@link ScheduleType#NOW NOW} and {@link ScheduleType#ONE_TIME ONE_TIME} scheduling.
     * </p>
     * 
     * @param endDateTime
     *            The new ending date/time for when the event should stop recurring.
     */
    public void setEndDateTime(Date endDateTime)
    {
        m_endDateTime = endDateTime;
    }

    /**
     * Gets the day of the month value.
     * 
     * <p>
     * Valid values are from 1 to 31, with * indicating every day.
     * </p>
     */
    public String getDayOfMonth()
    {
        return m_dayOfMonth;
    }

    /**
     * Set the day of the month value.
     * 
     * <p>
     * Valid values are from 1 to 31, with * indicating every day.
     * </p>
     * 
     * @param dayOfMonth
     *            Sets the new day of the month value.
     */
    public void setDayOfMonth(String dayOfMonth)
    {
        m_dayOfMonth = dayOfMonth;
    }

    /**
     * Gets the month of the year value.
     * 
     * <p>
     * Valid values are from 1 to 12, with * indicating every month.
     * </p>
     */
    public String getMonth()
    {
        return m_month;
    }

    /**
     * Sets the month of the year value.
     * 
     * <p>
     * Valid values are from 1 to 12, with * indicating every month.
     * </p>
     * 
     * @param month
     *            The new value to use.
     */
    public void setMonth(String month)
    {
        m_month = month;
    }

    /**
     * Gets the year.
     * 
     * <p>
     * Years are in 4 digit format, with multiple years separated by commas.
     * </p>
     */
    public String getYear()
    {
        return m_year;
    }

    /**
     * Sets the year.
     * 
     * <p>
     * Years are in 4 digit format, with multiple years separated by commas.
     * </p>
     * 
     * @param year
     *            The new year value.
     */
    public void setYear(String year)
    {
        m_year = year;
    }

    /**
     * Gets the {@link ScheduleType} for the event.
     * 
     * <p>
     * This is an enumeration alias for {@link EventPushCampaign#getScheduleTypeId() getScheduleTypeId()}
     * </p>
     * <p>
     * Defaults to {@link ScheduleType#NOW NOW} if not explicitly set.
     * </p>
     */
    public ScheduleType getScheduleType()
    {
        return ScheduleType.scheduleType(m_scheduleTypeId);
    }

    /**
     * Sets the {@link ScheduleType} for the event.
     *
     * <p>
     * This is an enumeration alias for {@link EventPushCampaign#setScheduleTypeId(int) setScheduleTypeId(int)}
     * </p>
     * <p>
     * Defaults to {@link ScheduleType#NOW NOW} if not explicitly set.
     * </p>
     * 
     * @param scheduleType
     *            The new {@link ScheduleType} value to set.
     */
    public void setScheduleType(ScheduleType scheduleType)
    {
        m_scheduleTypeId = scheduleType.getValue();
    }
}
