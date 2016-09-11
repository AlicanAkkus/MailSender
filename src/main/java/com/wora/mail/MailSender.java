package com.wora.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import com.wora.bean.ConfigurationBean;
import com.wora.bean.MailMessageBean;
import com.wora.util.ConfigurationUtils;

public class MailSender {

	ConfigurationBean config = null;

	public MailSender(Document xmlConfiguration) throws Exception {
		this.config = ConfigurationUtils.extractConfigurations(xmlConfiguration);
	}

	public MailSender(Properties propsConfiguration) throws Exception {
		this.config = ConfigurationUtils.extractConfigurations(propsConfiguration);
	}

	public void sendMail(MailMessageBean mailMessage) throws Exception {

		try {
			// if (logger.isTraceEnabled()) {
			// StringBuilder logString = new StringBuilder("\n\n\t .:: Sending mail ::. ");
			// logString.append("\n\t SmtpHost : ").append(smtpHost);
			// logString.append("\n\t From : ").append(mailFrom);
			// logString.append("\n\t FromName : ").append(mailFromName);
			// if (StringUtils.isBlank(message.getTo()) && StringUtils.isBlank(message.getCc())) {
			// logString.append("\n\t To : ").append(toMailList);
			// logString.append("\n\t CC : ").append(ccMailList);
			// }
			// logString.append("\n\t Subject : ").append(message.getSubject());
			// logString.append("\n\t Body : ").append(message.getBody());
			// logger.debug(logString.toString());
			// }
			
			Properties props = new Properties();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			MimeMessage msg = null;
			props.put("mail.smtp.host", config.getSmtpHost());

			Authenticator auth = null;

			if (config.isSmtpAuth()) {
				props.put("mail.smtp.auth", "true");
				auth = new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(config.getSmtpUsername(), config.getSmtpPassord());
					}
				};
			}

			Session session = Session.getInstance(props, auth);
			if (config.isSmtpDebug()) {
				session.setDebug(true);
			}

			msg = new MimeMessage(session);

			if (StringUtils.isNotBlank(mailMessage.getSubject())) {
				msg.setSubject(mailMessage.getSubject(), mailMessage.getEncoding());
			}

			if (mailMessage.getTo().size() > 0) {
				StringBuilder toAddress = new StringBuilder();
				
				for(String to : mailMessage.getTo()){
					toAddress.append(to).append(",");
				}
				toAddress.replace(toAddress.lastIndexOf(","), toAddress.length(), "");
				msg.setRecipients(Message.RecipientType.TO, toAddress.toString());
			}
			
			if (mailMessage.getCc().size() > 0) {
				StringBuilder ccAddress = new StringBuilder();
				
				for(String cc : mailMessage.getCc()){
					ccAddress.append(cc).append(",");
				}
				ccAddress.replace(ccAddress.lastIndexOf(","), ccAddress.length(), "");
				msg.setRecipients(Message.RecipientType.CC, ccAddress.toString());
			}

			if (StringUtils.isNotBlank(mailMessage.getContent())) {
				messageBodyPart.setContent(mailMessage.getContent(), mailMessage.getContentType());
				messageBodyPart.addHeader("Content-Type", mailMessage.getContentType() + "; " +"charset= " + mailMessage.getEncoding());
				multipart.addBodyPart(messageBodyPart);
			}
			msg.setContent(multipart);
			Transport.send(msg);

		} catch (Exception e) {
			throw e;
		}
	}

	public void sendMail(List<MailMessageBean> mailMessages) throws Exception {

		for (MailMessageBean mailMessage : mailMessages) {
			sendMail(mailMessage);
		}

	}

	public void sendMail(List<MailMessageBean> mailMessages, int delay) throws Exception {

		for (MailMessageBean mailMessage : mailMessages) {
			sendMail(mailMessage);
			Thread.sleep(delay);
		}

	}

}
 