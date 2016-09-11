package com.wora.mail;

import java.util.Properties;

import org.w3c.dom.Document;

public class MailFactory {

	public static MailSender mailFactory(Object configuration) throws Exception {
		
		MailSender mailSender = null;
		
		if(configuration instanceof Document){
			mailSender = new MailSender((Document) configuration);
		}else if(configuration instanceof Properties){
			mailSender = new MailSender((Properties) configuration);
		}
		
		return mailSender;
	}


}
