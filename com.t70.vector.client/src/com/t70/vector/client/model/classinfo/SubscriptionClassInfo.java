package com.t70.vector.client.model.classinfo;

import java.util.*;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Subscription;

/**
 * Details for the {@code Subscription} model class.
 * 
 * @see Subscription
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class SubscriptionClassInfo implements IModelClassInfo<Subscription>
{
    @Override
    public Subscription create()
    {
        return new Subscription();
    }

    @Override
    public Class<Subscription> getModelClass()
    {
        return Subscription.class;
    }

    @Override
    public GenericType<Collection<Subscription>> getCollectionClass()
    {
        return new GenericType<Collection<Subscription>>()
        {
        };
    }
}
