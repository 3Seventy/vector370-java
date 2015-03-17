package com.t70.vector.client.model.classinfo;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Content;

/**
 * Details for the {@code Content} model class.
 * 
 * @see Content
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class ContentClassInfo implements IModelClassInfo<Content>
{
    @Override
    public Content create()
    {
        return new Content();
    }

    @Override
    public Class<Content> getModelClass()
    {
        return Content.class;
    }

    @Override
    public GenericType<Collection<Content>> getCollectionClass()
    {
        return new GenericType<Collection<Content>>()
        {
        };
    }

}
