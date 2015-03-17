 package com.vectorclient.example;

import java.util.List;

import vector370.client.java.constant.ChannelType;
import vector370.client.java.constant.EncodingType;
import vector370.client.java.constant.LanguageType;
import vector370.client.java.exceptions.ExceptionBase;
import vector370.client.java.interfaces.IContentGroup;
import vector370.client.java.model.content.Content;
import vector370.client.java.model.content.ContentTemplate;
import vector370.client.java.utils.ServiceProviderFactory;

public class ContentExample {

	public static void main(String[] args) throws ExceptionBase {
		/*createContent("example","sdkExample");
		
		 createContentTemplate(0, ChannelType.EMAIL, EncodingType.TEXT,
		 LanguageType.FRENCH, "Hola esta es la prueba utilizando sdk jaa");
		

		getAllContentTemplate(0);*/
	}

	public static void getContentGroup() throws ExceptionBase {
		IContentGroup provider = ServiceProviderFactory
				.getContentServiceProvider(41916);
		List<Content> list = null;
		list = provider.getAllContentGroup();
		for (Content content : list) {
			System.out.println("\n\n  id  " + content.getId() + "\n name:   "
					+ content.getName());
			;
		}
	}

	public static void createContent(String name, String description)
			throws ExceptionBase {

		IContentGroup provider = ServiceProviderFactory
				.getContentServiceProvider(41916);
		Content rez = provider.createContentGroup(name, description);
		System.out.println(rez.getId());
	}

	public static Content getContent(int contentId) throws ExceptionBase {
		IContentGroup provider = ServiceProviderFactory
				.getContentServiceProvider(41916);
		Content cont = provider.getSpecificContentGroup(contentId);
		return cont;
	}

	public static void createContentTemplate(int contentId, ChannelType t1,
			EncodingType t2, LanguageType l2, String st) throws ExceptionBase {
		IContentGroup provider = ServiceProviderFactory
				.getContentServiceProvider(41916);
		ContentTemplate ctemp = provider.createContentTemplate(contentId, t1,
				t2, l2, st);
		

	}

	public static List<ContentTemplate> getAllContentTemplate(int contentGruopId)
			throws ExceptionBase {
		IContentGroup provider = ServiceProviderFactory
				.getContentServiceProvider(41916);
		List<ContentTemplate> list = provider
				.getAllContentTemplate(contentGruopId);
		return list;
	}

}
