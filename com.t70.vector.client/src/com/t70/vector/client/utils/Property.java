package com.t70.vector.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Property
{
    private String m_name;

    private Method m_getter;

    private Method m_setter;

    Property(String name, Method getter, Method setter)
    {
        m_name = name;
        m_getter = getter;
        m_setter = setter;
    }

    public String getName()
    {
        return m_name;
    }

    public boolean isReadable()
    {
        return (m_getter != null);
    }

    public boolean isWritable()
    {
        return (m_setter != null);
    }

    public Object getValue(final Object o)
    {
        if (m_getter == null)
            return null;

        boolean saveAccess = m_getter.isAccessible();

        try
        {
            m_getter.setAccessible(true);
            return m_getter.invoke(o);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return null;
        }
        finally
        {
            if (!saveAccess)
                m_getter.setAccessible(false);
        }
    }

    public void setValue(Object o, Object value)
    {
        if (m_setter == null)
            return;

        boolean saveAccess = m_setter.isAccessible();

        try
        {
            m_setter.setAccessible(true);
            m_setter.invoke(o, value);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        finally
        {
            if (!saveAccess)
                m_setter.setAccessible(false);
        }
    }
}
