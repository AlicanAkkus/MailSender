package com.wora.mail.test;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.wora.bean.MailMessageBean;
import com.wora.mail.MailFactory;
import com.wora.mail.MailSender;
import com.wora.util.XmlUtils;

public class MailSenderTest {

	public static void main(String[] args) throws Exception {

		Document document = XmlUtils.loadXmlFromFile("distsrc/config/MailSender.xml");

		MailSender mailSender = MailFactory.mailFactory(document);
		
		MailMessageBean mailMessageBean = new MailMessageBean();
		mailMessageBean.
			setFrom("alican.akkus@32bit.com.tr").
			setFromName("alican akkus").
			setSubject("Test").
			setContent("test data!!!!!").
			setTo("alican.akkus@32bit.com.tr");
		
		ArrayList<MailMessageBean> mailList = new ArrayList<MailMessageBean>();
		mailList.add(mailMessageBean);
		mailList.add(mailMessageBean);
		mailList.add(mailMessageBean);
		mailList.add(mailMessageBean);
		
		mailSender.sendMail(mailList, 1000);

	}

}
