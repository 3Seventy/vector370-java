 package com.vectorclient.example;

import java.util.List;

import vector370.client.java.constant.CampaignType;
import vector370.client.java.exceptions.ExceptionBase;
import vector370.client.java.interfaces.ICampaign;
import vector370.client.java.model.campaign.Campaign;
import vector370.client.java.utils.ServiceProviderFactory;

public class CampaignExample {

	public static void main(String[] args) throws ExceptionBase {
    //  campignList(42058);
	      getCampaignDetail(42058,4934);
	//	createCampaign(42058, "testSmsCampaign",CampaignType.BASIC,1212,12121);
		
	//	pushCampaign(42058,4936,6,null);
	}

	public static void campignList(int accountId) throws ExceptionBase {
		ICampaign services = ServiceProviderFactory
				.getCampaignServiceProvider(accountId);
		List<Campaign> list = services.getCampaignList(true);
	}

	public static void getCampaignDetail(int accountId, int campaignId) throws ExceptionBase {
		ICampaign services = ServiceProviderFactory
				.getCampaignServiceProvider(accountId);
		@SuppressWarnings("unused")
		Campaign detail = services.getCampaignDetail(campaignId);
	}

	public static void createCampaign(int accountId,String name,CampaignType type,int contentGroupId,int subscriptionId ) throws ExceptionBase {
		ICampaign services = ServiceProviderFactory
				.getCampaignServiceProvider(accountId);
		services.createCampaign(name,type, contentGroupId, subscriptionId);
		

	}

		
	}
	


