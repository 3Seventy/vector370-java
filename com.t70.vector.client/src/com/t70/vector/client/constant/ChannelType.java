package com.t70.vector.client.constant;

/**
 * The type of messages that can be sent on a channel.
 * 
 * <ul>
 * <li>{@link ChannelType#SMS SMS}</li>
 * <li>{@link ChannelType#MMS MMS}</li>
 * <li>{@link ChannelType#EMAIL EMAIL}</li>
 * <li>{@link ChannelType#VOICE VOICE}</li>
 * </ul>
 */
public enum ChannelType
{
    /**
     * Channel is for sending plain text SMS messages.
     */
    SMS(0),

    /**
     * Channel supports sending large format messages such as images.
     */
    MMS(1),

    /**
     * Channel is for sending email messages.
     */
    EMAIL(2),

    /**
     * Channel is for voice/audio messages.
     */
    VOICE(3),

    /**
     * The channel is for testing and the messages will be discarded after being logged.
     * <p>
     * This channel behaves the same as the SMS channel type, but messages are not delivered to the targets.
     * </p>
     */
    NULL(4);

    int m_channelType;

    private ChannelType(int channelType)
    {
        m_channelType = channelType;
    }

    public int getValue()
    {
        return m_channelType;
    }

    public static ChannelType channelType(int channel)
    {
        ChannelType type = null;

        switch (channel)
        {
        case 0:
            type = Enum.valueOf(ChannelType.class, "SMS");
            break;

        case 1:
            type = Enum.valueOf(ChannelType.class, "MMS");
            break;

        case 2:
            type = Enum.valueOf(ChannelType.class, "EMAIL");
            break;

        case 3:
            type = Enum.valueOf(ChannelType.class, "VOICE");
            break;

        case 4:
            type = Enum.valueOf(ChannelType.class, "NULL");
            break;
        }

        return type;
    }
}
