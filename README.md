# You can send very easily e-mail from Java application with MailSender.

How to use?

For single mail;
```java
    Document document = XmlUtils.loadXmlFromFile("distsrc/config/MailSender.xml");

		MailSender mailSender = MailFactory.mailFactory(document);
		
		MailMessageBean mailMessageBean = new MailMessageBean();
		mailMessageBean.
			setFrom("alican.akkus@32bit.com.tr").
			setFromName("alican akkus").
			setSubject("Test").
			setContent("test data!!!!!").
			setTo("alican.akkus@32bit.com.tr");
			
			mailSender.sendMail(mailMessageBean);
```	

For multiple mails;
```java
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
		
		mailSender.sendMail(mailList);
```	

Send mail with delay;
```	
		mailSender.sendMail(mailList, 1000); send all mails with 1 second delay
```	
