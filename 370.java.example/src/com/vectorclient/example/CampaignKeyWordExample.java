 package com.vectorclient.example;
import vector370.client.java.exceptions.ExceptionBase;
import vector370.client.java.interfaces.ICampaignKeyword;
import vector370.client.java.model.Keyword;
import vector370.client.java.utils.ServiceProviderFactory;

public class CampaignKeyWordExample {

	public static void main(String[] args) throws ExceptionBase {
		//attKeyToCampaign(0,0, 0);
		//deAttKeywordToCampaign(0,0);
	}

	public static Keyword attKeyToCampaign(int channelId, int keywordId,
			int CampaignId) throws ExceptionBase {
		ICampaignKeyword service = ServiceProviderFactory
				.campaignKeywordServiceProvider(41916);

		Keyword keyword = service.attKeyToCampaign(channelId, keywordId,
				CampaignId);

		return keyword;
	}

	public static void deAttKeywordToCampaign(int channelId, int keywordId)
			throws ExceptionBase {
		ICampaignKeyword service = ServiceProviderFactory
				.campaignKeywordServiceProvider(41916);
		String st = service.deAttKeyToCampaign(channelId, keywordId);
		System.out.println(st);

	}

}
