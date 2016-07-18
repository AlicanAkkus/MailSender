package com.wora.bean;

public class MailMessageBean {
	// <param name="SMTPDebug" value="true" />
	// <param name="SMTPHost" value="10.2.41.45" />
	// <param name="smtpUsername" value="mail.smartcash" />
	// <param name="smtpPassword" value="K37nemDRGHURQK2XVJ3m8hJ5T" />
	// <param name="Subject" value="PosIntegratorWS Fatal Error (Test)" />
	// <param name="From" value="PosIntegratorWS  &lt;no_reply@32bit.com.tr&gt;" />
	// <!-- <param name="To" value="Ufuk.Tukenmez@toshibagcs.com,Eray.Ece@toshibagcs.com,Celil.Yilmaz@toshibagcs.com" /> -->
	// <!-- <param name="cc" value="Bulent.Demir@toshibagcs.com,vdf.destek@32bit.com.tr /> -->
	// <param name="To" value="alican.akkus@32bit.com.tr,alican.akkus@32bit.com.tr" />
	// <param name="header" value="" />
	// <param name="footer" value="Please don't reply" />
	// <param name="BufferSize" value="30" />
	// <param name="SendInterval" value="600" />
	// <param name="Threshold" value="FATAL" />
	//
	// <layout class="tr.com.pos.genius.fashion.logging.HostPatternLayout">
	// <param name="ConversionPattern" value="%d{HH:mm:ss,SSS} %c %t %-5p: ( %h ) %m%n" />
	// </layout>

	private String subject = null;
	private StringBuilder from = null;
	private String fromName = "MailSender.<no.reply>";
	private StringBuilder to = null;
	private StringBuilder cc = null;
	private String content = null;
	private String encoding = "UTF-8";
	private String contentType = "text/html";
	

	public String getContentType() {
		return contentType;
	}

	public MailMessageBean setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	public MailMessageBean setEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getSubject() {
		return subject;
	}

	public MailMessageBean setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public StringBuilder getFrom() {
		return from;
	}

	public MailMessageBean setFrom(String fromAdress) {

		if (from == null)
			from = new StringBuilder();

		this.from.append(fromAdress).append(",");
		return this;
	}
	
	public MailMessageBean setFromName(String fromName) {
		this.fromName = fromName;
		return this;
	}
	
	public String getFromName() {
		return fromName;
	}

	public StringBuilder getTo() {
		return to;
	}

	public MailMessageBean setTo(String toAdress) {

		if (to == null)
			to = new StringBuilder();

		this.to.append(toAdress).append(",");
		return this;
	}

	public StringBuilder getCc() {
		return cc;
	}

	public MailMessageBean setCc(StringBuilder cc) {

		if (cc == null)
			cc = new StringBuilder();

		this.cc.append(cc).append(",");
		return this;
	}

	public String getContent() {
		return content;
	}

	public MailMessageBean setContent(String content) {
		this.content = content;
		return this;
	}

	@Override
	public String toString() {
		return "MailMessageBean [subject=" + subject + ", from=" + from + ", to=" + to + ", cc=" + cc + ", content=" + content + "]";
	}

}
