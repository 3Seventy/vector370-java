package com.t70.vector.client.constant;

/**
 * 
 * Identifier for languages
 * 
 * <p>
 * This enumeration will later be replaced with the specification dictated by RFC-1766
 * </p>
 *
 * <ul>
 * <li>{@link LanguageType#CHINESE_SIMPLIFIED CHINESE_SIMPLIFIED}</li>
 * <li>{@link LanguageType#ENGLISH ENGLISH} (United States)</li>
 * <li>{@link LanguageType#FRENCH FRENCH} (Canadian)</li>
 * <li>{@link LanguageType#SPANISH SPANISH} (Mexico)</li>
 * </ul>
 */
public enum LanguageType
{
    /**
     * English (United States)
     */
    ENGLISH(0),

    /**
     * Spanish (Mexico)
     */
    SPANISH(1),

    /**
     * Chinese (Simplified)
     */
    CHINESE_SIMPLIFIED(2),

    /**
     * French (Canadian)
     */
    FRENCH(3);

    private int m_languageType;

    private LanguageType(int languageType)
    {

        m_languageType = languageType;
    }

    public int getValue()
    {
        return m_languageType;
    }

    public static LanguageType languageType(int languageType)
    {
        LanguageType type = null;

        switch (languageType)
        {
        case 0:
            type = LanguageType.valueOf("ENGLISH");
            break;

        case 1:
            type = LanguageType.valueOf("SPANISH");
            break;

        case 2:
            type = LanguageType.valueOf("CHINESE_SIMPLIFIED");
            break;

        case 3:
            type = LanguageType.valueOf("FRENCH");
            break;
        }

        return type;
    }
}
