package com.t70.vector.client.model.classinfo;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Campaign;

/**
 * Details for the {@code Campaign} model class.
 * 
 * @see Campaign
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class CampaignClassInfo implements IModelClassInfo<Campaign>
{
    @Override
    public Campaign create()
    {
        return new Campaign();
    }

    @Override
    public Class<Campaign> getModelClass()
    {
        return Campaign.class;
    }

    @Override
    public GenericType<Collection<Campaign>> getCollectionClass()
    {
        return new GenericType<Collection<Campaign>>()
        {
        };
    }
}
