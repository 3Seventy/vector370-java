package com.t70.vector.client.utils;

import java.lang.reflect.*;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReflectionUtils
{

    private static final Logger s_logger = LogManager.getLogger(ReflectionUtils.class.getName());

    private static boolean isGetter(Method m)
    {
        Type[] paramTypes = m.getParameterTypes();

        if (Modifier.isPublic(m.getModifiers()) && (paramTypes.length == 0))
        {
            String name = m.getName();
            Type retType = m.getReturnType();

            if (name.matches("^get[A-Z].+") && !retType.equals(void.class))
                return true;

            if (name.matches("^is[A-Z].+") && retType.equals(boolean.class))
                return true;
        }

        return false;
    }

    private static boolean isSetter(Method m)
    {
        Type[] paramTypes = m.getParameterTypes();

        if (Modifier.isPublic(m.getModifiers()) && (paramTypes.length == 1))
        {
            String name = m.getName();
            Type retType = m.getReturnType();

            if (name.matches("^set[A-Z].+") && retType.equals(void.class))
                return true;
        }

        return false;
    }

    private static String getGetterName(Method m)
    {
        String name = m.getName();

        if (name.substring(0, 3).equals("get"))
            name = name.substring(3);
        else if (name.substring(0, 2).equals("is"))
            name = name.substring(2);

        return name;
    }

    private static String getSetterName(Method m)
    {
        String name = m.getName();

        if (name.substring(0, 3).equals("set"))
            name = name.substring(3);

        return name;
    }

    /**
     * Converts an object to a Map with each field name mapped to an object value.
     * 
     * @param o
     *            The object to map
     * 
     * @return A map with the field values mapped to the field names as keys.
     */
    public static Map<String, Object> objectToMap(final Object o)
    {
        final Map<String, Object> rval = new HashMap<String, Object>();

        Class<? extends Object> c = o.getClass();

        do
        {
            Field[] fields = c.getDeclaredFields();

            for (Field f : fields)
            {
                String name = f.getName();
                Object value;

                boolean saveAccess = f.isAccessible();

                try
                {
                    f.setAccessible(true);

                    value = f.get(o);
                }
                catch (Exception ex)
                {
                    String msg = String.format("Unable to get property value %s", name);
                    s_logger.warn(msg, ex);

                    continue;
                }
                finally
                {
                    if (!saveAccess)
                        f.setAccessible(true);
                }

                rval.put(name, value);
            }

            Method[] methods = c.getMethods();

            for (Method m : methods)
            {
                if (!isGetter(m))
                    continue;

                String name = getGetterName(m);

                Object value;

                try
                {
                    value = m.invoke(o);
                }
                catch (Exception ex)
                {
                    String msg = String.format("Unable to call %s", name);
                    s_logger.warn(msg, ex);

                    continue;
                }

                rval.put(name, value);
            }

            c = c.getSuperclass();
        } while (c != null);

        return rval;
    }

    static Map<String, String> objectToArguments(final Object primary, final Map<String, Object> secondary)
    {
        if (primary == null)
            throw new IllegalArgumentException("Object required for primary");

        Map<String, Object> dict = objectToMap(primary);

        final Map<String, String> rval = new HashMap<String, String>();

        for (Map.Entry<String, Object> kvp : dict.entrySet())
        {
            Object value = kvp.getValue();
            rval.put(kvp.getKey(), value != null ? value.toString() : "");
        }

        if (secondary != null)
        {
            for (Map.Entry<String, Object> kvp : secondary.entrySet())
            {
                Object value = kvp.getValue();
                rval.put(kvp.getKey(), value != null ? value.toString() : "");
            }
        }

        return rval;
    }

    public static Map<String, Field> getAllDeclaredFields(Class<? extends Object> type)
    {
        HashMap<String, Field> rval = new HashMap<String, Field>();

        do
        {
            Field[] fields = type.getDeclaredFields();

            for (Field f : fields)
                rval.put(f.getName(), f);

            type = type.getSuperclass();
        } while (type != null);

        return rval;
    }

    public static Map<String, Property> getAllDeclaredProperties(Class<? extends Object> type)
    {
        HashMap<String, Property> rval = new HashMap<String, Property>();

        do
        {
            Method[] methods = type.getDeclaredMethods();

            Map<String, Tuple<Method, Method>> propMethods = new HashMap<String, Tuple<Method, Method>>();

            for (Method m : methods)
            {
                Tuple<Method, Method> vals = null;
                String name = null;

                if (isGetter(m))
                {
                    name = getGetterName(m);

                    if (propMethods.containsKey(name))
                    {
                        vals = propMethods.get(name);
                    }
                    else
                    {
                        vals = new Tuple<Method, Method>();
                    }

                    vals.Value1 = m;
                    propMethods.put(name, vals);
                }
                else if (isSetter(m))
                {
                    name = getSetterName(m);

                    if (propMethods.containsKey(name))
                    {
                        vals = propMethods.get(name);
                    }
                    else
                    {
                        vals = new Tuple<Method, Method>();
                    }

                    vals.Value2 = m;
                    propMethods.put(name, vals);
                }
            }

            for (Map.Entry<String, Tuple<Method, Method>> kvp : propMethods.entrySet())
            {
                String name = kvp.getKey();
                Tuple<Method, Method> value = kvp.getValue();

                Property prop = new Property(name, value.Value1, value.Value2);

                rval.put(name, prop);
            }

            type = type.getSuperclass();

        } while (type != null);

        return rval;
    }
}
