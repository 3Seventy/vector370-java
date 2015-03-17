package com.vectorclient.example;

import vector370.client.java.constant.SubscriptionType;
import vector370.client.java.exceptions.ExceptionBase;
import vector370.client.java.interfaces.ISubscription;
import vector370.client.java.model.Subscription;
import vector370.client.java.utils.ServiceProviderFactory;

public class SubscriptionExample {

	public static void main(String[] args) throws ExceptionBase {
		
	      //	createSubscription(0,"", "", ,0);
		 //updateSubscription(0, 0, "",  "", , 0);
        //getAllSubscription(0);
	   //	deleteSubsccription(0,0);
	}

	public static void createSubscription(int accountID,String name, String Label,
			SubscriptionType type, int frquency) throws ExceptionBase {
		ISubscription service = ServiceProviderFactory
				.getSubscriptionServiceProvider(accountID);
		Subscription sub = service.createSubscriptions(name, Label, type,
				frquency);
	}

	public static void updateSubscription(int accountID,int subscriptionID, String name,
			String Label, SubscriptionType type, int frquency)
			throws ExceptionBase {
		ISubscription service = ServiceProviderFactory
				.getSubscriptionServiceProvider(accountID);

		Subscription sub = service.updateSubscriptions(subscriptionID, name,
				Label, type, frquency);
	}
	
	public static void getAllSubscription(int accountId) throws ExceptionBase{
	ISubscription service = ServiceProviderFactory.getSubscriptionServiceProvider(accountId);
		service.getAllSubscriptions();
	}
	
	public static void deleteSubsccription(int accountId,int subscriptionId) throws ExceptionBase{
		
		ISubscription service = ServiceProviderFactory.getSubscriptionServiceProvider(accountId);
		service.deleteSubscription(subscriptionId);
	}
}
