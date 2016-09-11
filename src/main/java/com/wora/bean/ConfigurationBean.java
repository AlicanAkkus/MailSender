package com.wora.bean;

public class ConfigurationBean {
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
