package com.t70.vector.client.model.classinfo;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.EventPushCampaign;

/**
 * Details for the {@code EventPushCampaign} model class.
 * 
 * @see EventPushCampaign
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class EventPushCampaignClassInfo implements IModelClassInfo<EventPushCampaign>
{
    @Override
    public EventPushCampaign create()
    {
        return new EventPushCampaign();
    }

    @Override
    public Class<EventPushCampaign> getModelClass()
    {
        return EventPushCampaign.class;
    }

    @Override
    public GenericType<Collection<EventPushCampaign>> getCollectionClass()
    {
        return new GenericType<Collection<EventPushCampaign>>()
        {
        };
    }
}
