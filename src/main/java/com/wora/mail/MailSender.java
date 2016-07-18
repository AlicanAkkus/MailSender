package com.wora.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
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
			
			String from = mailMessage.getFrom().substring(0, mailMessage.getFrom().lastIndexOf(","));
			Properties props = new Properties();
			InternetAddress addressFrom = new InternetAddress();
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

//			addressFrom.setAddress(from.toString());
//			addressFrom.setPersonal(mailMessage.getFromName(), mailMessage.getEncoding());
//			msg.setFrom(addressFrom);

			if (StringUtils.isNotBlank(mailMessage.getTo())) {
				String to = mailMessage.getTo().substring(0, mailMessage.getTo().lastIndexOf(","));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			}
			if (StringUtils.isNotBlank(mailMessage.getCc())) {
				String cc = mailMessage.getTo().substring(0, mailMessage.getCc().lastIndexOf(","));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cc));
			}

			if (StringUtils.isNotBlank(mailMessage.getContent())) {
				messageBodyPart.setContent(mailMessage.getContent(), mailMessage.getContentType());
				messageBodyPart.addHeader("Content-Type", "text/html; charset= " + mailMessage.getEncoding());
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
