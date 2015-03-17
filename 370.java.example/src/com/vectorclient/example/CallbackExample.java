 package com.vectorclient.example;

import vector370.client.java.constant.CallbackType;
import vector370.client.java.exceptions.ExceptionBase;
import vector370.client.java.interfaces.ICallback;

import vector370.client.java.utils.ServiceProviderFactory;

public class CallbackExample {
	public static void main(String[] args) throws ExceptionBase {
	getAll(0);
		// createCallback(0,CallbackType.KEYWORD,"http://example.us/e/",false);
		// updateCallback(0, 37010, CallbackType.COUPON, "http://example",
		// false);
		// delete(0, 0);
	}

	public static void getAll(int accountId) throws ExceptionBase {
		ICallback service = ServiceProviderFactory.getCallBackServiceProvider(accountId);
		service.getAll();

	}

	public static void createCallback(int accountId, CallbackType type, String url, boolean active)
			throws ExceptionBase {
		ICallback service = ServiceProviderFactory.getCallBackServiceProvider(accountId);
		service.createCallback(type, url, active);

	}

	public static void updateCallback(int accountId, int callbackID, CallbackType type, String url,boolean active)
			throws ExceptionBase {
		ICallback service = ServiceProviderFactory.getCallBackServiceProvider(accountId);
		service.updateCallback(callbackID, type, url, active);
	}

	public static void delete(int accountId, int callbackId) throws ExceptionBase {
		ICallback service = ServiceProviderFactory.getCallBackServiceProvider(accountId);
		service.delete(callbackId);
	}
}
