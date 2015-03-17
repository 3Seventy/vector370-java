package com.t70.vector.client.model.classinfo;

import java.util.*;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Keyword;

/**
 * Details for the {@code Keyword} model class.
 * 
 * @see Keyword
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class KeywordClassInfo implements IModelClassInfo<Keyword>
{
    @Override
    public Keyword create()
    {
        return new Keyword();
    }

    @Override
    public Class<Keyword> getModelClass()
    {
        return Keyword.class;
    }

    @Override
    public GenericType<Collection<Keyword>> getCollectionClass()
    {
        return new GenericType<Collection<Keyword>>()
        {
        };
    }
}
