package com.wora.bean;

public class ConfigurationBean {
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

	private boolean smtpDebug = true;
	private boolean smtpAuth = false;
	private String smtpHost = null;
	private String smtpUsername = null;
	private String smtpPassord = null;
	private int sendInterval = 0;

	public boolean isSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public boolean isSmtpDebug() {
		return smtpDebug;
	}

	public void setSmtpDebug(boolean smtpDebug) {
		this.smtpDebug = smtpDebug;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassord() {
		return smtpPassord;
	}

	public void setSmtpPassord(String smtpPassord) {
		this.smtpPassord = smtpPassord;
	}

	public int getSendInterval() {
		return sendInterval;
	}

	public void setSendInterval(int sendInterval) {
		this.sendInterval = sendInterval;
	}

	@Override
	public String toString() {
		return "ConfigurationBean [smtpDebug=" + smtpDebug + ", smtpAuth=" + smtpAuth + ", smtpHost=" + smtpHost + ", smtpUsername=" + smtpUsername
				+ ", smtpPassord=" + smtpPassord + ", sendInterval=" + sendInterval + "]";
	}
}
