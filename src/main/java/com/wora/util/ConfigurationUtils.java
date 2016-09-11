package com.wora.util;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.wora.bean.ConfigurationBean;

public class ConfigurationUtils {

	public static ConfigurationBean extractConfigurations(Object configuration) throws Exception {

		if (configuration instanceof Document) {
			return extractXmlConfiguration((Document) configuration);
		} else if (configuration instanceof Properties) {
			return extractPropertiesConfiguration((Properties) configuration);
		}

		return null;
	}

	private static ConfigurationBean extractXmlConfiguration(Document document) throws Exception {
		ConfigurationBean config = new ConfigurationBean();

		Node mailNode = XmlUtils.findNode(document, "mailsender");
		// <SMTPDebug>true</SMTPDebug>
		// <SMTPHost>10.2.41.45</SMTPHost>
		// <SMTPAuth>true</SMTPAuth>
		// <SMTPUsername>test</SMTPUsername>
		// <SMTPPassword>test</SMTPPassword>
		// <SendInterval>60</SendInterval>

		Element smtpDebugElement = XmlUtils.findElement(mailNode, "SMTPDebug");
		Element smtpHostElement = XmlUtils.findElement(mailNode, "SMTPHost");
		Element smtpAuthElement = XmlUtils.findElement(mailNode, "SMTPAuth");
		Element smtpUsernameElement = XmlUtils.findElement(mailNode, "SMTPUsername");
		Element smtpPasswordElement = XmlUtils.findElement(mailNode, "SMTPPassword");
		Element sendIntervalElement = XmlUtils.findElement(mailNode, "SendInterval");

		if (smtpDebugElement != null) {
			if (StringUtils.isNotBlank(smtpDebugElement.getTextContent())) {
				config.setSmtpDebug(Boolean.valueOf(smtpDebugElement.getTextContent()));
			}
		}
		if (smtpHostElement != null) {
			if (StringUtils.isNotBlank(smtpHostElement.getTextContent())) {
				config.setSmtpHost(smtpHostElement.getTextContent());
			}
		}
		if (smtpAuthElement != null) {
			if (StringUtils.isNotBlank(smtpAuthElement.getTextContent())) {
				config.setSmtpAuth(Boolean.valueOf(smtpAuthElement.getTextContent()));
			}
		}
		if (smtpUsernameElement != null) {
			if (StringUtils.isNotBlank(smtpUsernameElement.getTextContent())) {
				config.setSmtpUsername(smtpUsernameElement.getTextContent());
			}
		}
		if (smtpPasswordElement != null) {
			if (StringUtils.isNotBlank(smtpPasswordElement.getTextContent())) {
				config.setSmtpPassord(smtpPasswordElement.getTextContent());
			}
		}
		if (sendIntervalElement != null) {
			if (StringUtils.isNotBlank(sendIntervalElement.getTextContent())) {
				config.setSendInterval(Integer.valueOf(sendIntervalElement.getTextContent()));
			}
		}

		System.err.println(config);

		return config;

	}

	private static ConfigurationBean extractPropertiesConfiguration(Properties configuration) throws Exception {
		ConfigurationBean config = new ConfigurationBean();

		// mail.sender.SMTPDebug = true
		// mail.sender.SMTPHost = 10.2.41.45
		// mail.sender.SMTPAuth = true
		// mail.sender.SMTPUsername = test
		// mail.sender.SMTPPassword = test
		// mail.sender.SendInterval = 60
		String smtpDebugElement = configuration.getProperty("mail.sender.SMTPDebug", "true");
		String smtpHostElement = configuration.getProperty("mail.sender.SMTPHost", "");
		String smtpAuthElement = configuration.getProperty("mail.sender.SMTPAuth", "false");
		String smtpUsernameElement = configuration.getProperty("mail.sender.SMTPUsername", "");
		String smtpPasswordElement = configuration.getProperty("mail.sender.SMTPPassword", "");
		String sendIntervalElement = configuration.getProperty("mail.sender.SendInterval", "");

		if (StringUtils.isNotBlank(smtpDebugElement)) {
			config.setSmtpDebug(Boolean.valueOf(smtpDebugElement));
		}
		if (StringUtils.isNotBlank(smtpHostElement)) {
			config.setSmtpHost(smtpHostElement);
		}
		if (StringUtils.isNotBlank(smtpAuthElement)) {
			config.setSmtpAuth(Boolean.valueOf(smtpAuthElement));
		}
		if (StringUtils.isNotBlank(smtpUsernameElement)) {
			config.setSmtpUsername(smtpUsernameElement);
		}
		if (StringUtils.isNotBlank(smtpPasswordElement)) {
			config.setSmtpPassord(smtpPasswordElement);
		}
		if (StringUtils.isNotBlank(sendIntervalElement)) {
			config.setSendInterval(Integer.valueOf(sendIntervalElement));
		}

		System.err.println(config);
		return config;
	}

	public static void main(String[] args) throws Exception {

//		Document document = XmlUtils.loadXmlFromFile("distsrc/config/MailSender.xml");
//		extractConfigurations(document);

		Properties properties = new Properties();
		PropertiesUtils.loadProperties("distsrc/config/MailSender.properties", properties);
		extractConfigurations(properties);
		
		
	}
}
