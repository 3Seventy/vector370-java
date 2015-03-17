 package com.vectorclient.example;

import java.util.List;

import vector370.client.java.exceptions.ExceptionBase;
import vector370.client.java.interfaces.Ikeyword;
import vector370.client.java.model.Keyword;
import vector370.client.java.utils.ServiceProviderFactory;

public class KeyWordExample {

	public static void main(String[] args) throws ExceptionBase {
		//createKeyword(0,"", , true);
		//getKeywordDetail(0, 0, 0);
		///getKeywordList(0, true);
	    //getKeywordListChannel(0,false);
	   //updateKeyword(0,0, false);
       //deleteKeyword(0,0,0);
	}
    public static void createKeyword(int accountId,String name, int channelId,
			boolean callbackRequired) throws ExceptionBase{
    	Ikeyword provider = ServiceProviderFactory.getKeywordServiceProvider(accountId);
    Keyword k = provider.createKeyword(name, channelId, callbackRequired);
    System.out.println(k.getId());
    	
    }
	
	
	public static void getKeywordDetail(int accountId, int channelId,
			int keywordId) throws ExceptionBase {

		Ikeyword provider = ServiceProviderFactory.getKeywordServiceProvider(22);
		System.out.println(provider.getKeywordDetail( channelId,
				keywordId).getId());
		;
	}

	public static void getKeywordList(int accountId, boolean onlyMine) throws ExceptionBase {

		Ikeyword provider = ServiceProviderFactory.getKeywordServiceProvider(41916);

		List<Keyword> list = provider.getKeyWordList_Account(
				onlyMine);
		for (Keyword keyword : list) {
			System.out.println(keyword.getId());
		}
	}
	
	public static void getKeywordListChannel(int accountId, int channelId,boolean onlyMine) throws ExceptionBase {

		Ikeyword provider = ServiceProviderFactory.getKeywordServiceProvider(accountId);

		  List<Keyword> list = provider.getKeyWordList_Channel(channelId, onlyMine);
		for (Keyword keyword : list) {
			System.out.println(keyword.getName());
		}
	}
	
	public static Keyword updateKeyword(int channelId, int keywordId, boolean callbackRequired) throws ExceptionBase {

		Ikeyword provider = ServiceProviderFactory.getKeywordServiceProvider(41916);
		  Keyword keyword = provider.updateKeyWord(channelId, keywordId, callbackRequired);
	return keyword;
	}
	
	public static String deleteKeyword(int accountId,int channelId, int keywordId) throws ExceptionBase {

		Ikeyword provider = ServiceProviderFactory.getKeywordServiceProvider(accountId);
		  String keyword = provider.deleteKeyword(channelId, keywordId);
	return keyword;
	}

}
