package com.t70.vector.client.rest;

import com.t70.vector.client.config.*;
import com.t70.vector.client.interfaces.IVectorConfig;
import com.t70.vector.client.model.*;

public class T70Context extends RestContext
{
    /**
     * Default constructor
     */
    public T70Context()
    {
        super(VectorClientProperties.getConfig());
    }

    /**
     * Constructor where the configuration details can be overloaded.
     * 
     * @param config
     */
    public T70Context(IVectorConfig config)
    {
        super(config);
    }

    @Override
    protected void onModelCreating(RestModelBuilder modelBuilder)
    {
        accountSetup(modelBuilder);
        contactSetup(modelBuilder);
        contentSetup(modelBuilder);
        subscriptionSetup(modelBuilder);
        campaignSetup(modelBuilder);
        keywordSetup(modelBuilder);
        eventSetup(modelBuilder);
        callbackSetup(modelBuilder);
    }

    private static void accountSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Account.class)
            .uriFormat("/account/{accountId}");

        modelBuilder.entity(Account.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("accountId");
    }

    private static void contactSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Contact.class)
            .uriFormat("/account/{accountId}/contact/{contactId}");

        modelBuilder.entity(Contact.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("contactId");

        modelBuilder.entity(Contact.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }

    private static void contentSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Content.class)
            .uriFormat("/account/{accountId}/content/{contentId}");

        modelBuilder.entity(Content.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("contentId");

        modelBuilder.entity(Content.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");

        modelBuilder.entity(ContentTemplate.class)
            .uriFormat("/account/{accountId}/content/{contentId}/template/{templateId}");

        modelBuilder.entity(ContentTemplate.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("templateId");

        modelBuilder.entity(ContentTemplate.class)
            .forMember("ContentId")
            .makeUrlSegment()
            .mapTo("contentId");

        modelBuilder.entity(ContentTemplate.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }

    private static void subscriptionSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Subscription.class)
            .uriFormat("/account/{accountId}/subscription/{subscriptionId}");

        modelBuilder.entity(Subscription.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("subscriptionId");

        modelBuilder.entity(Subscription.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }

    private static void campaignSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Campaign.class)
            .uriFormat("/account/{accountId}/campaign/{campaignId}");

        modelBuilder.entity(Campaign.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("campaignId");

        modelBuilder.entity(Campaign.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }

    private static void keywordSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Keyword.class)
            .uriFormat("/account/{accountId}/channel/{channelId}/keyword/{keywordId}");

        modelBuilder.entity(Keyword.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("keywordId");

        modelBuilder.entity(Keyword.class)
            .forMember("ChannelId")
            .makeUrlSegment()
            .mapTo("channelId");

        modelBuilder.entity(Keyword.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }

    private static void eventSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(EventPushCampaign.class)
            .uriFormat("/account/{accountId}/event-pushcampaign/{eventId}");

        modelBuilder.entity(EventPushCampaign.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("eventId");

        modelBuilder.entity(EventPushCampaign.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }

    private static void callbackSetup(RestModelBuilder modelBuilder)
    {
        modelBuilder.entity(Callback.class)
            .uriFormat("/account/{accountId}/callback/{callbackId}");

        modelBuilder.entity(Callback.class)
            .forMember("Id")
            .makeOptionalUrlSegment()
            .makePrimaryKey()
            .mapTo("callbackId");

        modelBuilder.entity(Callback.class)
            .forMember("AccountId")
            .makeUrlSegment()
            .mapTo("accountId");
    }
}
