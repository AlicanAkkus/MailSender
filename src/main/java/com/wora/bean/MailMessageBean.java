package com.wora.bean;

import java.util.LinkedList;

public class MailMessageBean {

	private String subject = null;
	private String from = null;
	private String fromName = "MailSender <no-reply>";
	private LinkedList<String> to = new LinkedList<String>();
	private LinkedList<String> cc = new LinkedList<String>();
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

	public String getFrom() {
		return from;
	}

	public MailMessageBean setFrom(String fromAdress) {
		this.from = fromAdress;
		return this;
	}

	public MailMessageBean setFromName(String fromName) {
		this.fromName = fromName;
		return this;
	}

	public String getFromName() {
		return fromName;
	}

	public LinkedList<String> getTo() {
		return to;
	}

	public MailMessageBean setTo(String toAddress) {
		this.to.add(toAddress);
		return this;
	}

	public LinkedList<String> getCc() {
		return cc;
	}

	public MailMessageBean setCc(String ccAddress) {
		this.cc.add(ccAddress);
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
