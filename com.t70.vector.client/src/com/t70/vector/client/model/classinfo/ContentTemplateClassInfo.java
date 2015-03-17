package com.t70.vector.client.model.classinfo;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.ContentTemplate;

/**
 * Details for the {@code ContentTemplate} model class.
 * 
 * @see ContentTemplate
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class ContentTemplateClassInfo implements IModelClassInfo<ContentTemplate>
{

    @Override
    public ContentTemplate create()
    {
        return new ContentTemplate();
    }

    @Override
    public Class<ContentTemplate> getModelClass()
    {
        return ContentTemplate.class;
    }

    @Override
    public GenericType<Collection<ContentTemplate>> getCollectionClass()
    {
        return new GenericType<Collection<ContentTemplate>>()
        {
        };
    }
}
