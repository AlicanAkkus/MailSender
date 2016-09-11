package com.wora.mail.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.wora.bean.MailMessageBean;
import com.wora.mail.MailFactory;
import com.wora.mail.MailSender;
import com.wora.util.XmlUtils;

public class MailSenderTest {

	Document config = null;

	@Before
	public void loadConfig() {
		config = XmlUtils.loadXmlFromFile("distsrc/config/MailSender.xml");
	}

	@Test
	public void sendMail() {
		MailSender mailSender;
		try {
			mailSender = MailFactory.mailFactory(config);

			MailMessageBean mailMessageBean = new MailMessageBean();
			mailMessageBean.setFrom("alican.akkus@32bit.com.tr").setFromName("alican akkus").setSubject("Test").setContent("<h1>test data!!!!!</h1>")
					.setTo("alican.akkus@32bit.com.tr");

			ArrayList<MailMessageBean> mailList = new ArrayList<MailMessageBean>();
			mailList.add(mailMessageBean);
			mailList.add(mailMessageBean);
			mailList.add(mailMessageBean);
			mailList.add(mailMessageBean);

			mailSender.sendMail(mailList, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
