package com.t70.vector.client.utils;

import java.util.*;

public class StringUtils
{
    static private Boolean isNullOrEmpty(final String s)
    {
        return (s == null) || (s.length() == 0);
    }

    static public Boolean isNullOrWhitespace(final String s)
    {
        return isNullOrEmpty(s) || isWhitespace(s);
    }

    static private Boolean isWhitespace(final String s)
    {
        Boolean rval = true;

        for (int i = 0; rval && i < s.length(); ++i)
            rval = Character.isWhitespace(s.charAt(i));

        return rval;
    }

    static <T> String join(String delimeter, Collection<T> items)
    {
        boolean isFirst = true;
        String rval = "";

        for (T i : items)
        {
            if (!isFirst)
                rval += delimeter;
            else
                isFirst = false;

            rval += i.toString();
        }

        return rval;
    }
}
