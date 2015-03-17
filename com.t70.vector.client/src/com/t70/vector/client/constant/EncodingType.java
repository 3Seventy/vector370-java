package com.t70.vector.client.constant;

/**
 * Dictates the template's format.
 */
public enum EncodingType
{
    /**
     * All text is plain and should not be interpreted in anyway.
     */
    TEXT(0), // NO_UCD (unused code)

    /**
     * The text has Razor encodings in it.
     */
    RAZOR(1); // NO_UCD (unused code)

    private int m_encodingType;

    private EncodingType(int encodingType)
    {
        m_encodingType = encodingType;
    }

    public int getValue()
    {
        return m_encodingType;
    }

    public static EncodingType encodingType(int encodingType) // NO_UCD (unused code)
    {
        EncodingType type = null;

        switch (encodingType)
        {
        case 0:
            type = Enum.valueOf(EncodingType.class, "TEXT");
            break;

        case 1:
            type = Enum.valueOf(EncodingType.class, "RAZOR");
            break;
        }

        return type;
    }
}
