package com.t70.vector.client.model.classinfo;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Callback;

/**
 * Details for the {@code Callback} model class.
 * 
 * @see Callback
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class CallbackClassInfo implements IModelClassInfo<Callback>
{
    @Override
    public Callback create()
    {
        return new Callback();
    }

    @Override
    public Class<Callback> getModelClass()
    {
        return Callback.class;
    }

    @Override
    public GenericType<Collection<Callback>> getCollectionClass()
    {
        return new GenericType<Collection<Callback>>()
        {
        };
    }
}
