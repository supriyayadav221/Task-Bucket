package com.taskBucket.helpers;

public class AlertMessage {
	private String message;
	private String type;
	private String cssClass;

	public AlertMessage(String message, String type, String cssClass) {
		this.message = message;
		this.type = type;
		this.cssClass = cssClass;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;

	}

}
