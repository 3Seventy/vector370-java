package com.t70.vector.client.constant;

/**
 * The type of event which will trigger the callback.
 */
public enum CallbackType
{
    /**
     * Execute when a contact texts into a keyword.
     */
    KEYWORD(1),

    /**
     * Reserved by 3Seventy, do not use.
     * 
     * @deprecated Reserved by 3Seventy, do not use.
     */
    @Deprecated
    RESERVED0(2),

    /**
     * Executes when a contact texts in a valid response to a dialog question.
     */
    DIALOG(3),

    /**
     * Execute when a contact is sent a sweepstakes campaign. Not supported yet. Do not use.
     * 
     * @deprecated Not supported yet, do not use.
     */
    @Deprecated
    SWEEPSTAKES(4),

    /**
     * Execute when a contact is sent a coupon campaign.
     * 
     * @deprecated Not supported yet, do not use.
     */
    @Deprecated
    COUPON(5),

    /**
     * Execute when a contact is opts out of a subscription.
     */
    STOP(6),

    /**
     * Reserved by 3Seventy, do not use.
     * 
     * @deprecated Reserved by 3Seventy, do not use.
     */
    @Deprecated
    RESERVED1(7),

    /**
     * Executes when a new sub-account is created under a master account.
     * 
     */
    NEW_ACCOUNT(8),

    /**
     * Reserved by 3Seventy, do not use.
     * 
     * @deprecated Reserved by 3Seventy, do not use.
     */
    @Deprecated
    RESERVED2(9),

    /**
     * Reserved by 3Seventy, do not use.
     * 
     * @deprecated Reserved by 3Seventy, do not use.
     */
    @Deprecated
    RESERVED3(10);

    private int m_callbackType;

    private CallbackType(int callbackType)
    {
        m_callbackType = callbackType;
    }

    public int getValue()
    {
        return m_callbackType;
    }

    public static CallbackType getCallbackType(int callBackTypeID)
    {
        CallbackType type = null;

        switch (callBackTypeID)
        {
        case 1:
            type = CallbackType.KEYWORD;
            break;

        case 2:
            type = CallbackType.RESERVED0;
            break;

        case 4:
            type = CallbackType.SWEEPSTAKES;
            break;

        case 5:
            type = CallbackType.COUPON;
            break;

        case 6:
            type = CallbackType.STOP;
            break;

        case 7:
            type = CallbackType.RESERVED1;
            break;

        case 8:
            type = CallbackType.NEW_ACCOUNT;
            break;

        case 9:
            type = CallbackType.RESERVED2;
            break;

        case 10:
            type = CallbackType.KEYWORD;
            break;
        }
        return type;
    }

}
